package com.project.xmlparser.handlers

import org.xml.sax.helpers.DefaultHandler

open class InvoiceParser : DefaultHandler() {

    var currentName: String? = null
    var map = mutableMapOf<String?, String>()
    var prodName = false
    var value = ""
    var uniqueValue = mutableListOf<String?>()
    var uniqueProdValues = mutableListOf<String?>()

    fun getInvoiceMap() : MutableMap<String?, String> {
        return map
    }

    fun clearInvoiceMap() {
        map.clear()
    }

    fun getUniqueValues() : List<String?> {

        val uniqueValues = uniqueProdValues.distinct().sortedBy { it?.substringAfter("|")?.toInt() }

        return uniqueValue.distinct() + uniqueValues
    }

    fun clearUniqueValues() {
        uniqueProdValues.clear()
        uniqueValue.clear()
    }

}