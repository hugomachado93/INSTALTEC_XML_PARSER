package com.project.xmlparser.services

import com.project.xmlparser.exception.ServiceNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class FactoryXmlPaser(
        @Autowired
        val danfeService: DanfeService
) {
    fun getXmlParser(parser: String): XmlParserInterface {
        when (parser) {
            "danfe" -> return danfeService
            else -> throw ServiceNotFoundException("XmlParser not Found")
        }
    }
}