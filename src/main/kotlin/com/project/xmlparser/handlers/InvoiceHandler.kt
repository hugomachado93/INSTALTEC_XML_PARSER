package com.project.xmlparser.handlers

import org.springframework.stereotype.Component
import org.xml.sax.Attributes
import org.xml.sax.helpers.DefaultHandler

@Component
class InvoiceHandler : DefaultHandler() {

    private var currentName: String? = null
    private var map = mutableMapOf<String?, String>()
    private var prodName = false
    private var newName = false
    private var value = ""
    private var uniqueValue = mutableListOf<String?>()
    private var uniqueProdValues = mutableListOf<String?>()
    private var count = 0

    override fun startElement(uri: String?, localName: String?, qName: String?, atribute: Attributes?) {

        currentName = qName

        when(currentName) {
            "ide" -> value = "ide"
            "emit" -> value = "emit"
            "enderEmit" -> value = "enderEmit"
            "dest" -> value = "dest"
            "enderDest" -> value = "enderDest"
            "total" -> value = "total"
            "transp" -> value = "transp"
            "transportadora" -> value = "transportadora"
            "veicTransp" -> value = "veicTransp"
            "vol" -> value = "vol"
            "cobr" -> value = "cobr"
            "pag" -> value = "pag"
            "protNFe" -> value = "protNfe"
        }

        if(atribute?.getQName(0) == "nItem") {
            value = atribute.getValue(0)
            prodName = true
        }

        currentName = "$value$qName"

        if(prodName) {
            currentName = "prod$qName|$value"
        }

    }


    override fun characters(ch: CharArray, start: Int, length: Int) {
        val string = String(ch, start, length)

        string.takeIf { it.isNotEmpty() }?.let { map[currentName] = it }

        if(prodName) {
            uniqueProdValues.add(currentName)
        } else {
            uniqueValue.add(currentName)
        }
    }

    override fun endElement(uri: String?, localName: String?, qName: String?) {

        when(qName){
            "det" -> {
                value = ""
                prodName = false
            }
        }

    }

    fun getMapped() : MutableMap<String?, String> {
        return map
    }

    fun clearInvoiceMap() {
        map.clear()
    }

    fun getUniqueValues() : List<String?> {

        val uniqueValues = uniqueProdValues.distinct().sortedBy { it?.substringAfter("|") }

        return uniqueValue.distinct() + uniqueValues
    }

    fun clearUniqueValues() {
        uniqueProdValues.clear()
        uniqueValue.clear()
    }

}