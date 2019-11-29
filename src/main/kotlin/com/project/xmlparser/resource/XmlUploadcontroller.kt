package com.project.xmlparser.resource

import com.google.cloud.storage.BlobId
import com.google.cloud.storage.Storage
import com.project.xmlparser.entity.UploadFileResponse
import com.project.xmlparser.services.XmlParserInvoice
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
import java.util.concurrent.TimeUnit
import javax.servlet.http.HttpServletRequest
import kotlin.system.measureTimeMillis


val listaParam = listOf(
        "Id",
        "cnpj",
        "email",
        "ie",
        "indIEDest",
        "xNome",
        "enderDest",
        "cPais",
        "cep",
        "fone",
        "nro",
        "uf",
        "xBairro",
        "xLgr",
        "xMun",
        "xPais",
        "nItem",
        "imposto",
        "cst",
        "orig",
        "cst",
        "cProd",
        "emitCnpj",
        "crt",
        "enderEmit",
        "cPais",
        "cep",
        "fone",
        "nro",
        "uf",
        "xBairro",
        "xLgr",
        "xMun",
        "xPais",
        "emitIe",
        "iest",
        "emitXNome",
        "cMun",
        "entregaCnpj",
        "nro",
        "uf",
        "xBairro",
        "xLgr",
        "xMun",
        "cMunFG",
        "cdv",
        "cnf",
        "cuf",
        "dhEmi",
        "dhSaiEnt",
        "finNFe",
        "idDest",
        "indFinal",
        "indPres",
        "mod",
        "natOp",
        "nnf",
        "procEmi",
        "serie",
        "tpAmb",
        "tpEmis",
        "tpImp",
        "tpNF",
        "verProc",
        "infCpl",
        "tPag",
        "vPag",
        "vDesc",
        "vFrete",
        "vOutro",
        "vProd",
        "vSeg",
        "vbc",
        "vbcst",
        "vcofins",
        "vfcp",
        "vfcpst",
        "vfcpstRet",
        "vicms",
        "vicmsDeson",
        "vii",
        "vipi",
        "vipiDevol",
        "vnf",
        "vpis",
        "vst",
        "modFrete",
        "signatureValue",
        "x509Certificate",
        "_Algorithm",
        "_URI",
        "digestValue",
        "_Algorithm",
        "signatureMethodAlgorithm",
        "infProtId",
        "cStat",
        "chNFe",
        "dhRecbto",
        "digVal",
        "nProt",
        "tpAmb",
        "verAplic",
        "xMotivo"
)

@RestController
class XmlUploadcontroller(@Autowired val xmlParserInvoice: XmlParserInvoice) {

    @CrossOrigin(origins = ["http://localhost:8081"])
    @PostMapping("/upload")
    fun uploadXmlFile(@RequestParam("files") files: Array<MultipartFile>, @RequestParam("type") type: String): ResponseEntity<Any> {
        var blobid : Int? = null

        val time = measureTimeMillis {
            blobid = xmlParserInvoice.createDocument(listaParam, files)
        }

        println(TimeUnit.MILLISECONDS.toSeconds(time))

        val uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/upload/").path("$blobid").toUriString()

        val fileResponse = UploadFileResponse("temp.xlsx", uri)

        return ResponseEntity.status(HttpStatus.OK).body(fileResponse)
    }

}