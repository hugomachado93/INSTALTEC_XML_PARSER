package com.project.xmlparser.controler

import com.project.xmlparser.services.FactoryXmlPaser
import com.project.xmlparser.services.XmlParserInterface
import com.project.xmlparser.services.XmlParserInvoice
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import java.util.concurrent.TimeUnit
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

@RestController("/v1")
class XmlUploadcontroller(@Autowired val xmlParserInvoice: XmlParserInvoice) {

    @PostMapping("/upload")
    fun uploadXmlFile(@RequestParam("file") files: Array<MultipartFile>, @RequestParam("type") type: String): ResponseEntity<Any> {
        val time = measureTimeMillis {
            xmlParserInvoice.createDocument(listaParam, files)
        }
        println(TimeUnit.MILLISECONDS.toSeconds(time))
        return ResponseEntity.ok().build()
    }

}