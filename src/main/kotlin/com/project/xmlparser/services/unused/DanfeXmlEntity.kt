package com.project.xmlparser.services.unused

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import java.util.ArrayList


@JacksonXmlRootElement(localName = "nfeProc")
class NfeProc (
    // Getter Methods 

    // Setter Methods 

        var nFe: NFe? = null,
        var protNFe: ProtNFe?,
        var _xmlns: String? = null,
        var _versao: String? = null
)

class ProtNFe (
    // Getter Methods 

    // Setter Methods 

        var infProt: InfProt?,
        var _versao: String? = null
)

class InfProt (
    // Getter Methods 

    // Setter Methods 

    var tpAmb: String? = null,
    var verAplic: String? = null,
    var chNFe: String? = null,
    var dhRecbto: String? = null,
    var nProt: String? = null,
    var digVal: String? = null,
    var cStat: String? = null,
    var xMotivo: String? = null,
    var _Id: String? = null
)

class NFe (
    // Getter Methods 

    // Setter Methods 

        var infNFe: InfNFe? = null,
        var signature: Signature?,
        var _xmlns: String? = null
)

class Signature (
    // Getter Methods 

    // Setter Methods 

        var signedInfo: SignedInfo?,
        var signatureValue: String? = null,
        var keyInfo: KeyInfo?,
        var _xmlns: String? = null
)

class KeyInfo (
    // Getter Methods 

    // Setter Methods 

    var x509Data: X509Data?
)

class X509Data (
    // Getter Methods 

    // Setter Methods 

    var x509Certificate: String? = null
)

class SignedInfo (
    // Getter Methods 

    // Setter Methods 

        var canonicalizationMethod: CanonicalizationMethod?,
        var signatureMethod: SignatureMethod?,
        var reference: Reference?
)

class Reference (
    // Getter Methods 

    // Setter Methods 

//    var transforms: Transforms,
        var digestMethod: DigestMethod?,
        var digestValue: String? = null,
        var _URI: String? = null
)

class DigestMethod (
    // Getter Methods 

    // Setter Methods 

    var _Algorithm: String? = null
)

//class Transforms (
//    internal var Transform = ArrayList<Any>()
//
//
//    // Getter Methods
//
//
//    // Setter Methods
//
//
//)

class SignatureMethod (
    // Getter Methods 

    // Setter Methods 

    var _Algorithm: String? = null
)

class CanonicalizationMethod (
    // Getter Methods 

    // Setter Methods 

    var _Algorithm: String? = null
)

class InfNFe (
    // Getter Methods 

    // Setter Methods 

        var ide: Ide?,
        var emit: Emit?,
        var dest: Dest?,
        var entrega: Entrega?,
        @JacksonXmlElementWrapper(useWrapping = false)
    var det: ArrayList<Det?>,
        var total: Total?,
        var transp: Transp?,
        var pag: Pag?,
        var infAdic: InfAdic?,
        var _Id: String? = null,
        var _versao: String? = null
)

class InfAdic (
    // Getter Methods 

    // Setter Methods 

    var infCpl: String? = null
)

class Pag (
    // Getter Methods 

    // Setter Methods 

    var detPag: DetPag?
)

class DetPag (
    // Getter Methods 

    // Setter Methods 

    var tPag: String? = null,
    var vPag: String? = null
)

class Transp (
    // Getter Methods 

    // Setter Methods 

    var modFrete: String? = null,
    var vol: Vol?
)

class Vol (
    // Getter Methods 

    // Setter Methods 

    var qVol: String? = null,
    var esp: String? = null,
    var marca: String? = null,
    var nVol: String? = null,
    var pesoL: String? = null,
    var pesoB: String? = null
)

class Total (
    // Getter Methods 

    // Setter Methods 

    var icmsTot: ICMSTot?
)

class ICMSTot (
    // Getter Methods 

    // Setter Methods 

    var vbc: String? = null,
    var vicms: String? = null,
    var vicmsDeson: String? = null,
    var vfcp: String? = null,
    var vbcst: String? = null,
    var vst: String? = null,
    var vfcpst: String? = null,
    var vfcpstRet: String? = null,
    var vProd: String? = null,
    var vFrete: String? = null,
    var vSeg: String? = null,
    var vDesc: String? = null,
    var vii: String? = null,
    var vipi: String? = null,
    var vipiDevol: String? = null,
    var vpis: String? = null,
    var vcofins: String? = null,
    var vOutro: String? = null,
    var vnf: String? = null
)

class Det (
    // Getter Methods 

    // Setter Methods 

        var prod: Prod?,
        var imposto: Imposto?,
        var infAdProd: String? = null,
        var _nItem: String? = null
)

class Imposto (
    // Getter Methods 

    // Setter Methods 

        var icms: ICMS?,
        var pis: PIS?,
        var cofins: COFINS?
)

class COFINS (
    // Getter Methods 

    // Setter Methods 

    var cofinsnt: COFINSNT?
)

class COFINSNT (
    // Getter Methods 

    // Setter Methods 

    var cst: String? = null
)

class PIS (
    // Getter Methods 

    // Setter Methods 

    var pisnt: PISNT?
)

class PISNT (
    // Getter Methods 

    // Setter Methods 

    var cst: String? = null
)

class ICMS (
    // Getter Methods 

    // Setter Methods 

    var icmS40: ICMS40?
)

class ICMS40 (
    // Getter Methods 

    // Setter Methods 

    var orig: String? = null,
    var cst: String? = null
)

class Prod (
    // Getter Methods 

    // Setter Methods 

    var cProd: String? = null,
    var cean: String? = null,
    var xProd: String? = null,
    var ncm: String? = null,
    var cest: String? = null,
    var indEscala: String? = null,
    var cfop: String? = null,
    var uCom: String? = null,
    var qCom: String? = null,
    var vUnCom: String? = null,
    var vProd: String? = null,
    var ceanTrib: String? = null,
    var uTrib: String? = null,
    var qTrib: String? = null,
    var vUnTrib: String? = null,
    var indTot: String? = null,
    var comb: Comb?
)

class Comb (
    // Getter Methods 

    // Setter Methods 

    var cProdANP: String? = null,
    var descANP: String? = null,
    var ufCons: String? = null
)

class Entrega (
    // Getter Methods 

    // Setter Methods 

    var cnpj: String? = null,
    var xLgr: String? = null,
    var nro: String? = null,
    var xBairro: String? = null,
    var cMun: String? = null,
    var xMun: String? = null,
    var uf: String? = null
)

class Dest (
    // Getter Methods 

    // Setter Methods 

        var cnpj: String? = null,
        var xNome: String? = null,
        var enderDest: EnderDest?,
        var indIEDest: String? = null,
        var ie: String? = null,
        var email: String? = null
)

class EnderDest (
    // Getter Methods 

    // Setter Methods 

    var xLgr: String? = null,
    var nro: String? = null,
    var xBairro: String? = null,
    var cMun: String? = null,
    var xMun: String? = null,
    var uf: String? = null,
    var cep: String? = null,
    var cPais: String? = null,
    var xPais: String? = null,
    var fone: String? = null
)

class Emit (
    // Getter Methods 

    // Setter Methods 

        var cnpj: String? = null,
        var xNome: String? = null,
        var enderEmit: EnderEmit?,
        var ie: String? = null,
        var iest: String? = null,
        var crt: String? = null
)

class EnderEmit (
    // Getter Methods 

    // Setter Methods 

    var xLgr: String? = null,
    var nro: String? = null,
    var xBairro: String? = null,
    var cMun: String? = null,
    var xMun: String? = null,
    var uf: String? = null,
    var cep: String? = null,
    var cPais: String? = null,
    var xPais: String? = null,
    var fone: String? = null
)

class Ide (
    // Getter Methods 

    // Setter Methods 

    var cuf: String? = null,
    var cnf: String? = null,
    var natOp: String? = null,
    var mod: String? = null,
    var serie: String? = null,
    var nnf: String? = null,
    var dhEmi: String? = null,
    var dhSaiEnt: String? = null,
    var tpNF: String? = null,
    var idDest: String? = null,
    var cMunFG: String? = null,
    var tpImp: String? = null,
    var tpEmis: String? = null,
    var cdv: String? = null,
    var tpAmb: String? = null,
    var finNFe: String? = null,
    var indFinal: String? = null,
    var indPres: String? = null,
    var procEmi: String? = null,
    var verProc: String? = null
)