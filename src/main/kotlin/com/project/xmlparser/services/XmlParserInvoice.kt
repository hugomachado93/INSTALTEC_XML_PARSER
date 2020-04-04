package com.project.xmlparser.services

import com.project.xmlparser.dto.UploadFileResponse
import com.project.xmlparser.handlers.InvoiceHandler
import com.project.xmlparser.repository.CloudStorage
import org.apache.poi.ss.usermodel.FillPatternType
import org.apache.poi.ss.usermodel.IndexedColors
import org.apache.poi.ss.util.CellRangeAddress
import org.apache.poi.xssf.usermodel.XSSFCellStyle
import org.apache.poi.xssf.usermodel.XSSFSheet
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.context.annotation.RequestScope
import org.springframework.web.multipart.MultipartFile
import org.xml.sax.helpers.DefaultHandler
import java.io.ByteArrayOutputStream
import javax.xml.parsers.SAXParserFactory

@RequestScope
@Service
class XmlParserInvoice(@Autowired val cloudStorage: CloudStorage) : DefaultHandler() {

    val log = LoggerFactory.getLogger(XmlParserInvoice::class.java)

    val invoiceHandler = InvoiceHandler()

    var filesMap = mutableListOf<MutableMap<String?, String>>()

    fun createDocument(listOfAllowedParams: List<String?>, files: Array<MultipartFile>) : UploadFileResponse {
        val workbook = XSSFWorkbook()
        val sheetAllowedheader = workbook.createSheet("Danfe Sheet Filtered Headers")
        val sheetFullHeader = workbook.createSheet("Danfe Sheet All Headers")

        val factory = SAXParserFactory.newInstance()
        factory.isNamespaceAware = true
        val saxParser = factory.newSAXParser()

        files.forEach { f ->
            saxParser.parse(f.inputStream, invoiceHandler)
            filesMap.add(invoiceHandler.getInvoiceMap().toMutableMap())
            invoiceHandler.clearInvoiceMap()
        }

        log.info("Criando headers para o excel")

        val allowedHeaders = createHeaders(workbook,sheetAllowedheader,listOfAllowedParams)
        log.info("iniciando processso de parser do xml")
        populateFile(allowedHeaders, sheetAllowedheader, filesMap)
        log.info("Processo de parser finalizado")

        if(!listOfAllowedParams.isNullOrEmpty()) {
            val fullHeader = createHeaders(workbook, sheetFullHeader, emptyList())
            log.info("iniciando processso de parser do xml")
            populateFile(fullHeader, sheetFullHeader, filesMap)
            log.info("Processo de parser finalizado")
        }

        invoiceHandler.clearUniqueValues()
        invoiceHandler.clearInvoiceMap()
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

    private fun createHeaders( workbook: XSSFWorkbook, sheet : XSSFSheet, listOfAllowedParams: List<String?>): List<String?> {

        val headerRow = sheet.createRow(0)

        val headerStyle = createHeaderStyle(workbook)

        log.info("Juntando todos os headers unicos")

        val headers = invoiceHandler.getUniqueValues(listOfAllowedParams)

        headers.forEachIndexed { index, value ->
            val headerCell = headerRow.createCell(index)
            headerCell.setCellValue(value)
            headerCell.cellStyle = headerStyle
            sheet.setColumnWidth(index, 6000)
        }

        sheet.setAutoFilter(CellRangeAddress(sheet.firstRowNum, sheet.lastRowNum, 0, sheet.getRow(0).physicalNumberOfCells))

        return headers
    }

    private fun populateFile(headers: List<String?>, sheet: XSSFSheet, filesMap: MutableList<MutableMap<String?, String>>) {

        filesMap.forEachIndexed{ index, f ->
            val row = sheet.createRow(index.plus(1))

            f.forEach {map ->
                val index = headers.indexOf(map.key)

                if(index != -1) {
                    val cell = row.createCell(index)
                    cell.setCellValue(map.value)
                }
            }
        }
    }

}