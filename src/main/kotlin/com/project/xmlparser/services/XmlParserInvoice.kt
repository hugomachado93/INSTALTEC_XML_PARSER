package com.project.xmlparser.services

import com.project.xmlparser.handlers.InvoiceHandler
import org.apache.poi.ss.usermodel.FillPatternType
import org.apache.poi.ss.usermodel.IndexedColors
import org.apache.poi.xssf.usermodel.XSSFRow
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import org.xml.sax.Attributes
import org.xml.sax.helpers.DefaultHandler
import java.io.FileOutputStream
import javax.xml.parsers.SAXParserFactory



@Service
class XmlParserInvoice(@Autowired val invoiceHandler: InvoiceHandler) : DefaultHandler() {

    private var canFind = false

    fun createDocument(lista: List<String>, files: Array<MultipartFile>){

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

        headers.forEachIndexed { index, value ->
            val headerCell = headerRow.createCell(index)
            headerCell.setCellValue(value)
            headerCell.cellStyle = headerStyle
            sheet.setColumnWidth(index, 6000)
        }

        files.forEachIndexed{ index, f ->
            saxParser.parse(f.inputStream, invoiceHandler)
            val map = invoiceHandler.getMapped()
            val row = sheet.createRow(index.plus(1))

            map.forEach {map ->
                val index = headers.indexOf(map.key)
                val cell = row.createCell(index)
                cell.setCellValue(map.value)
            }

            invoiceHandler.clearInvoiceMap()
            invoiceHandler.clearUniqueValues()

            map.clear()

        }

        val fileStream = FileOutputStream("/home/vabows/Documents/temp.xlsx")
        workbook.write(fileStream)
        fileStream.close()
        workbook.close()

    }

    private fun getHeaders(files: Array<MultipartFile>): List<String?> {

        val factory = SAXParserFactory.newInstance()
        factory.isNamespaceAware = true
        val saxParser = factory.newSAXParser()

        files.forEach { f ->
            saxParser.parse(f.inputStream, invoiceHandler)
        }

        return invoiceHandler.getUniqueValues()
    }

}