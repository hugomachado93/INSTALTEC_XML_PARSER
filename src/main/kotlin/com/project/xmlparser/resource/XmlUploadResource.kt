package com.project.xmlparser.resource

import com.project.xmlparser.dto.MappedParams
import com.project.xmlparser.dto.UploadFileResponse
import com.project.xmlparser.services.XmlParserInvoice
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.util.concurrent.TimeUnit
import kotlin.system.measureTimeMillis


@RestController
class XmlUploadResource(@Autowired val xmlParserInvoice: XmlParserInvoice) {

    val log = LoggerFactory.getLogger(XmlUploadResource::class.java)

    @PostMapping("/upload")
    fun uploadXmlFile(@RequestPart("files") files: Array<MultipartFile>,@RequestPart("mappedParams") mappedParams: MappedParams, @RequestParam("type") type: String): ResponseEntity<UploadFileResponse> {
        var fileResponse : UploadFileResponse? = null

        log.info("Iniciando criacao do arquivo")

        fileResponse = xmlParserInvoice.createDocument(mappedParams.listParams, files)

        log.info("Processo do arquivo ${fileResponse.fileName} finalizado")

        return ResponseEntity.status(HttpStatus.OK).body(fileResponse)
    }

    @GetMapping("/ping")
    fun teste() : String{
        return "pong";
    }

}