package com.project.xmlparser.services

import com.project.xmlparser.exception.ServiceNotFoundException
import com.project.xmlparser.services.DanfeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

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