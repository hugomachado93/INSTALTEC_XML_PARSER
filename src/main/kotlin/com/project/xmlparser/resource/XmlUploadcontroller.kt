package com.project.xmlparser.resource

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


val listaParam = listOf(
        "all"
)

@RestController
class XmlUploadcontroller(@Autowired val xmlParserInvoice: XmlParserInvoice) {

    val log = LoggerFactory.getLogger(XmlUploadcontroller::class.java)

    @PostMapping("/upload")
    fun uploadXmlFile(@RequestParam("files") files: Array<MultipartFile>, @RequestParam("type") type: String): ResponseEntity<UploadFileResponse> {
        var fileResponse : UploadFileResponse? = null

        log.info("Iniciando criacao do arquivo")

        val time = measureTimeMillis {
            fileResponse = xmlParserInvoice.createDocument(listaParam, files)
        }

        println(TimeUnit.MILLISECONDS.toSeconds(time))

        log.info("Processo do arquivo ${fileResponse?.fileName} finalizado")

        return ResponseEntity.status(HttpStatus.OK).body(fileResponse)
    }

}