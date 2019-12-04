package com.project.xmlparser.services.unused

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.MapperFeature
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.springframework.web.multipart.MultipartFile
import java.io.FileOutputStream
import org.apache.poi.ss.usermodel.FillPatternType
import org.apache.poi.ss.usermodel.IndexedColors
import org.springframework.stereotype.Component


//Not used anymore
@Component
class DanfeService : XmlParserInterface {

        private val mapper = XmlMapper().registerModule(KotlinModule()).configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true)
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES).disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES).enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT)


        override fun deserializeXml(lista: List<String>, files: Array<MultipartFile>) {

                val workbook = XSSFWorkbook()
                val sheet = workbook.createSheet("DanfeSheet")

                val headerRow = sheet.createRow(0)
                val headerStyle = workbook.createCellStyle()
                headerStyle.fillForegroundColor = IndexedColors.LIGHT_BLUE.getIndex()
                headerStyle.fillPattern = FillPatternType.SOLID_FOREGROUND

                val font = workbook.createFont()
                font.fontName = "Arial"
                font.fontHeightInPoints = 16.toShort()
                font.bold = true
                headerStyle.setFont(font)

                lista.forEachIndexed { index, l ->
                        val headerCell = headerRow.createCell(index)
                        headerCell.setCellValue(l)
                        headerCell.cellStyle = headerStyle
                        sheet.setColumnWidth(index, 6000);
                }


                var rowTemp = 0
                files.forEach{ f ->
                        val values = mapper.readValue(f.bytes, NfeProc::class.java)

                        val map = mapValues(values)

                        val row = sheet.createRow(++rowTemp)

                        var indexTemp = 0

                        lista.forEachIndexed { index2, l ->
                                indexTemp = index2
                                var cell = row.createCell(index2)
                                cell.setCellValue(map[l])
                        }

                        values.nFe?.infNFe?.det?.forEachIndexed { index, it ->
                                val cellHead1 = headerRow.createCell(++indexTemp)
                                cellHead1.setCellValue("cProd$index")
                                var cell = row.createCell(indexTemp)
                                cell.setCellValue(map["cProd$index"])
                                var cell2 = row.createCell(++indexTemp)
                                cell2.setCellValue(map["cean$index"])
                                var cell3 = row.createCell(++indexTemp)
                                cell3.setCellValue(map["ceanTrib$index"])
                                var cell4 = row.createCell(indexTemp++)
                                cell4.setCellValue(map["cest$index"])
                        }

                }

                val fileStream = FileOutputStream("/home/vabows/Documents/temp.xlsx")
                workbook.write(fileStream)
                fileStream.close()
                workbook.close()

        }

    fun mapValues(values : NfeProc) : Map<String, String?> {

        val map = mutableMapOf(
                "Id" to values?.nFe?.infNFe?._Id,
                "cnpj" to values?.nFe?.infNFe?.dest?.cnpj,
                "email" to values?.nFe?.infNFe?.dest?.email,
                "ie" to values?.nFe?.infNFe?.dest?.ie,
                "indIEDest" to values.nFe?.infNFe?.dest?.indIEDest,
                "xNome" to values.nFe?.infNFe?.dest?.xNome,
                "enderDest" to values.nFe?.infNFe?.dest?.enderDest?.cMun,
                "cPais" to values.nFe?.infNFe?.dest?.enderDest?.cPais,
                "cep" to values.nFe?.infNFe?.dest?.enderDest?.cep,
                "fone" to values.nFe?.infNFe?.dest?.enderDest?.fone,
                "nro" to values.nFe?.infNFe?.dest?.enderDest?.nro,
                "uf" to values.nFe?.infNFe?.dest?.enderDest?.uf,
                "xBairro" to values.nFe?.infNFe?.dest?.enderDest?.xBairro,
                "xLgr" to values.nFe?.infNFe?.dest?.enderDest?.xLgr,
                "xMun" to values.nFe?.infNFe?.dest?.enderDest?.xMun,
                "xPais" to values.nFe?.infNFe?.dest?.enderDest?.xPais,
                "emitCnpj" to values?.nFe?.infNFe?.emit?.cnpj,
                "crt" to values?.nFe?.infNFe?.emit?.crt,
                "enderEmit" to values?.nFe?.infNFe?.emit?.enderEmit?.cMun,
                "cPais" to values?.nFe?.infNFe?.emit?.enderEmit?.cPais,
                "cep" to values?.nFe?.infNFe?.emit?.enderEmit?.cep,
                "fone" to values?.nFe?.infNFe?.emit?.enderEmit?.fone,
                "nro" to values?.nFe?.infNFe?.emit?.enderEmit?.nro,
                "uf" to values?.nFe?.infNFe?.emit?.enderEmit?.uf,
                "xBairro" to values?.nFe?.infNFe?.emit?.enderEmit?.xBairro,
                "xLgr" to values?.nFe?.infNFe?.emit?.enderEmit?.xLgr,
                "xMun" to values?.nFe?.infNFe?.emit?.enderEmit?.xMun,
                "xPais" to values?.nFe?.infNFe?.emit?.enderEmit?.xPais,
                "emitIe" to values?.nFe?.infNFe?.emit?.ie,
                "iest" to values?.nFe?.infNFe?.emit?.iest,
                "emitXNome" to values?.nFe?.infNFe?.emit?.xNome,
                "cMun" to values?.nFe?.infNFe?.entrega?.cMun,
                "entregaCnpj" to values?.nFe?.infNFe?.entrega?.cnpj,
                "nro" to values?.nFe?.infNFe?.entrega?.nro,
                "uf" to values?.nFe?.infNFe?.entrega?.uf,
                "xBairro" to values?.nFe?.infNFe?.entrega?.xBairro,
                "xLgr" to values?.nFe?.infNFe?.entrega?.xLgr,
                "xMun" to values?.nFe?.infNFe?.entrega?.xMun,
                "cMunFG" to values?.nFe?.infNFe?.ide?.cMunFG,
                "cdv" to values?.nFe?.infNFe?.ide?.cdv,
                "cnf" to values?.nFe?.infNFe?.ide?.cnf,
                "cuf" to values?.nFe?.infNFe?.ide?.cuf,
                "dhEmi" to values?.nFe?.infNFe?.ide?.dhEmi,
                "dhSaiEnt" to values?.nFe?.infNFe?.ide?.dhSaiEnt,
                "finNFe" to values?.nFe?.infNFe?.ide?.finNFe,
                "idDest" to values?.nFe?.infNFe?.ide?.idDest,
                "indFinal" to values?.nFe?.infNFe?.ide?.indFinal,
                "indPres" to values?.nFe?.infNFe?.ide?.indPres,
                "mod" to values?.nFe?.infNFe?.ide?.mod,
                "natOp" to values?.nFe?.infNFe?.ide?.natOp,
                "nnf" to values?.nFe?.infNFe?.ide?.nnf,
                "procEmi" to values?.nFe?.infNFe?.ide?.procEmi,
                "serie" to values?.nFe?.infNFe?.ide?.serie,
                "tpAmb" to values?.nFe?.infNFe?.ide?.tpAmb,
                "tpEmis" to values?.nFe?.infNFe?.ide?.tpEmis,
                "tpImp" to values?.nFe?.infNFe?.ide?.tpImp,
                "tpNF" to values?.nFe?.infNFe?.ide?.tpNF,
                "verProc" to values?.nFe?.infNFe?.ide?.verProc,
                "infCpl" to values?.nFe?.infNFe?.infAdic?.infCpl,
                "tPag" to values?.nFe?.infNFe?.pag?.detPag?.tPag,
                "vPag" to values?.nFe?.infNFe?.pag?.detPag?.vPag,
                "vDesc" to values?.nFe?.infNFe?.total?.icmsTot?.vDesc,
                "vFrete" to values?.nFe?.infNFe?.total?.icmsTot?.vFrete,
                "vOutro" to values?.nFe?.infNFe?.total?.icmsTot?.vOutro,
                "vProd" to values?.nFe?.infNFe?.total?.icmsTot?.vProd,
                "vSeg" to values?.nFe?.infNFe?.total?.icmsTot?.vSeg,
                "vbc" to values?.nFe?.infNFe?.total?.icmsTot?.vbc,
                "vbcst" to values?.nFe?.infNFe?.total?.icmsTot?.vbcst,
                "vcofins" to values?.nFe?.infNFe?.total?.icmsTot?.vcofins,
                "vfcp" to values?.nFe?.infNFe?.total?.icmsTot?.vfcp,
                "vfcpst" to values?.nFe?.infNFe?.total?.icmsTot?.vfcpst,
                "vfcpstRet" to values?.nFe?.infNFe?.total?.icmsTot?.vfcpstRet,
                "vicms" to values?.nFe?.infNFe?.total?.icmsTot?.vicms,
                "vicmsDeson" to values?.nFe?.infNFe?.total?.icmsTot?.vicmsDeson,
                "vii" to values?.nFe?.infNFe?.total?.icmsTot?.vii,
                "vipi" to values?.nFe?.infNFe?.total?.icmsTot?.vipi,
                "vipiDevol" to values?.nFe?.infNFe?.total?.icmsTot?.vipiDevol,
                "vnf" to values?.nFe?.infNFe?.total?.icmsTot?.vnf,
                "vpis" to values?.nFe?.infNFe?.total?.icmsTot?.vpis,
                "vst" to values?.nFe?.infNFe?.total?.icmsTot?.vst,
                "modFrete" to values?.nFe?.infNFe?.transp?.modFrete,
                "signatureValue" to values?.nFe?.signature?.signatureValue,
                "x509Certificate" to values?.nFe?.signature?.keyInfo?.x509Data?.x509Certificate,
                "_Algorithm" to values?.nFe?.signature?.signedInfo?.canonicalizationMethod?._Algorithm,
                "_URI" to values?.nFe?.signature?.signedInfo?.reference?._URI,
                "digestValue" to values?.nFe?.signature?.signedInfo?.reference?.digestValue,
                "_Algorithm" to values?.nFe?.signature?.signedInfo?.reference?.digestMethod?._Algorithm,
                "signatureMethodAlgorithm" to values?.nFe?.signature?.signedInfo?.signatureMethod?._Algorithm,
                "infProtId" to values?.protNFe?.infProt?._Id,
                "cStat" to values?.protNFe?.infProt?.cStat,
                "chNFe" to values?.protNFe?.infProt?.chNFe,
                "dhRecbto" to values.protNFe?.infProt?.dhRecbto,
                "digVal" to values?.protNFe?.infProt?.digVal,
                "nProt" to values?.protNFe?.infProt?.nProt,
                "tpAmb" to values?.protNFe?.infProt?.tpAmb,
                "verAplic" to values?.protNFe?.infProt?.verAplic,
                "xMotivo" to values?.protNFe?.infProt?.xMotivo
        )

            values.nFe?.infNFe?.det?.forEachIndexed{ index, it ->
                    map["nItem$index"] = it?._nItem
                    map["infAdProd$index"]  = it?.infAdProd
                    map["cst$index"] = it?.imposto?.cofins?.cofinsnt?.cst
                    map["orig$index"] = it?.imposto?.icms?.icmS40?.orig
                    map["cst$index"] = it?.imposto?.icms?.icmS40?.cst
                    map["infAdProd$index"] = it?.imposto?.pis?.pisnt?.cst
                    map["cProd$index"] = it?.prod?.cProd
                    map["cean$index"] = it?.prod?.cean
                    map["ceanTrib$index"] = it?.prod?.ceanTrib
                    map["cest$index"] = it?.prod?.cest
                    map["cfop$index"] = it?.prod?.cfop
                    map["cfop$index"] = it?.prod?.cfop
                    map["indTot$index"] = it?.prod?.indTot
                    map["ncm$index"] = it?.prod?.ncm
                    map["qCom$index"] = it?.prod?.qCom
                    map["qTrib$index"] = it?.prod?.qTrib
                    map["uCom$index"] = it?.prod?.uCom
                    map["uCom$index"] = it?.prod?.uCom
                    map["vProd$index"] = it?.prod?.vProd
                    map["vUnCom$index"] = it?.prod?.vUnCom
                    map["vUnTrib$index"] = it?.prod?.vUnTrib
                    map["xProd$index"] = it?.prod?.xProd
            }

            return map
    }

}