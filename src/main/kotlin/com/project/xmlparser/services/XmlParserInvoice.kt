package com.project.xmlparser.services

import com.project.xmlparser.entity.UploadFileResponse
import com.project.xmlparser.handlers.InvoiceHandler
import org.apache.poi.ss.usermodel.FillPatternType
import org.apache.poi.ss.usermodel.IndexedColors
import org.apache.poi.ss.util.CellRangeAddress
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import org.xml.sax.helpers.DefaultHandler
import java.io.ByteArrayOutputStream
import java.net.URL
import javax.xml.parsers.SAXParserFactory


@Service
class XmlParserInvoice(@Autowired val invoiceHandler: InvoiceHandler, @Autowired val cloudStorage: CloudStorage) : DefaultHandler() {

    val log = LoggerFactory.getLogger(XmlParserInvoice::class.java)

    fun createDocument(lista: List<String>, files: Array<MultipartFile>) : UploadFileResponse {

        val factory = SAXParserFactory.newInstance()
        factory.isNamespaceAware = true
        val saxParser = factory.newSAXParser()

        val workbook = XSSFWorkbook()
        val sheet = workbook.createSheet("DanfeSheet")

        val headerRow = sheet.createRow(0)
        val headerStyle = workbook.createCellStyle()
        headerStyle.fillForegroundColor = IndexedColors.LIGHT_BLUE.getIndex()
        headerStyle.fillPattern = FillPatternType.SOLID_FOREGROUND

        val font = workbook.createFont()
        font.fontName = "Arial"
        font.fontHeightInPoints = 16.toShort()
        font.bold = true
        headerStyle.setFont(font)

        val headers = getHeaders(files)

        log.info("Criando headers para o excel")

        headers.forEachIndexed { index, value ->
            val headerCell = headerRow.createCell(index)
            headerCell.setCellValue(value)
            headerCell.cellStyle = headerStyle
            sheet.setColumnWidth(index, 6000)
        }

        log.info("Headers criados")

        log.info("iniciando processso de parser o xml")

        files.forEachIndexed{ index, f ->
            saxParser.parse(f.inputStream, invoiceHandler)
            val map = invoiceHandler.getMapped()
            val row = sheet.createRow(index.plus(1))

            map.forEach {map ->
                val index = headers.indexOf(map.key)
                val cell = row.createCell(index)
                cell.setCellValue(map.value)
                log.info("Cria celula no indice $index com valor: ${map.value}")
            }

            invoiceHandler.clearInvoiceMap()
            invoiceHandler.clearUniqueValues()

            map.clear()

        }

        log.info("Processo de parser finalizado")

        sheet.setAutoFilter(CellRangeAddress(sheet.firstRowNum, sheet.lastRowNum, 0, sheet.getRow(0).physicalNumberOfCells))
        val byteArrayOutputStream = ByteArrayOutputStream()
        workbook.write(byteArrayOutputStream)
        workbook.close()

        log.info("Salvando arquivo temporario no cloud storage")

        return cloudStorage.saveToDownload(byteArrayOutputStream.toByteArray())

    }

    private fun getHeaders(files: Array<MultipartFile>): List<String?> {

        log.info("Juntando todos os headers unicos")

        val factory = SAXParserFactory.newInstance()
        factory.isNamespaceAware = true
        val saxParser = factory.newSAXParser()

        files.forEach { f ->
            saxParser.parse(f.inputStream, invoiceHandler)
        }

        return invoiceHandler.getUniqueValues()
    }

}