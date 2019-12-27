package com.project.xmlparser.resource

import com.google.cloud.storage.BlobId
import com.google.cloud.storage.Storage
import com.project.xmlparser.entity.UploadFileResponse
import com.project.xmlparser.services.XmlParserInvoice
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.InputStreamResource
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.io.File
import java.io.FileInputStream
import java.io.IOException
import java.net.URL
import java.util.concurrent.TimeUnit
import javax.servlet.http.HttpServletRequest
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