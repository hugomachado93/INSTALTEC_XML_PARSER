package com.project.xmlparser.services

import com.project.xmlparser.dto.UploadFileResponse
import com.project.xmlparser.handlers.InvoiceHandler
import org.apache.poi.ss.usermodel.FillPatternType
import org.apache.poi.ss.usermodel.IndexedColors
import org.apache.poi.ss.util.CellRangeAddress
import org.apache.poi.xssf.usermodel.XSSFCellStyle
import org.apache.poi.xssf.usermodel.XSSFSheet
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import org.xml.sax.helpers.DefaultHandler
import java.io.ByteArrayOutputStream
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

        log.info("Criando headers para o excel")

        val headers = createHeaders(files, sheet, workbook)

        log.info("Headers criados")

        log.info("iniciando processso de parser do xml")

        files.forEachIndexed{ index, f ->
            saxParser.parse(f.inputStream, invoiceHandler)
            val map = invoiceHandler.getInvoiceMap()
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

    private fun createHeaderStyle(workbook : XSSFWorkbook) : XSSFCellStyle {
        val headerStyle = workbook.createCellStyle()
        headerStyle.fillForegroundColor = IndexedColors.LIGHT_BLUE.getIndex()
        headerStyle.fillPattern = FillPatternType.SOLID_FOREGROUND

        val font = workbook.createFont()
        font.fontName = "Arial"
        font.fontHeightInPoints = 16.toShort()
        font.bold = true
        headerStyle.setFont(font)

        return headerStyle
    }

    private fun createHeaders(files: Array<MultipartFile>, sheet : XSSFSheet, workbook: XSSFWorkbook): List<String?> {

        val headerRow = sheet.createRow(0)

        val headerStyle = createHeaderStyle(workbook)

        log.info("Juntando todos os headers unicos")

        val factory = SAXParserFactory.newInstance()
        factory.isNamespaceAware = true
        val saxParser = factory.newSAXParser()

        files.forEach { f ->
            saxParser.parse(f.inputStream, invoiceHandler)
        }

        val headers = invoiceHandler.getUniqueValues()

        headers.forEachIndexed { index, value ->
            val headerCell = headerRow.createCell(index)
            headerCell.setCellValue(value)
            headerCell.cellStyle = headerStyle
            sheet.setColumnWidth(index, 6000)
        }

        return headers
    }

}