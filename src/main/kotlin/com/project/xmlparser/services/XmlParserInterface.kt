package com.project.xmlparser.services

import org.springframework.web.multipart.MultipartFile

interface XmlParserInterface {

    fun deserializeXml(lista: List<String>, files: Array<MultipartFile>)

}