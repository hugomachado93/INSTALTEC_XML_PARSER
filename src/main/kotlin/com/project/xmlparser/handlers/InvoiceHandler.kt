package com.project.xmlparser.handlers

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.context.annotation.RequestScope
import org.xml.sax.Attributes
import org.xml.sax.ContentHandler
import org.xml.sax.Locator
import org.xml.sax.helpers.DefaultHandler

@Component
class InvoiceHandler : InvoiceParser(){

    val log = LoggerFactory.getLogger(InvoiceHandler::class.java)

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

        if (prodName) {
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
}