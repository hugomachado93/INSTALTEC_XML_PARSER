package com.project.xmlparser.entity

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement

@JacksonXmlRootElement(localName = "nfeProc")
class NfeProc (
        var nFe: NFe,
        var protNFe: ProtNFe
)

class ProtNFe (
        var infProt: InfProt
)

class InfProt (

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
        var infNFe: InfNFe,
        var signature: Signature
        
)

class Signature (

        var signedInfo: SignedInfo,
        var signatureValue: String? = null,
        var keyInfo: KeyInfo
        
)

class KeyInfo (

        var x509Data: X509Data
)

class X509Data (

        var x509Certificate: String? = null
)

class SignedInfo (

        var canonicalizationMethod: CanonicalizationMethod,
        var signatureMethod: SignatureMethod,
        var reference: Reference
)

class Reference (

        var transforms: List<Any>,
        var digestMethod: DigestMethod,
        var digestValue: String? = null,
        var _URI: String? = null
)

class DigestMethod (

        var _Algorithm: String? = null
)

class SignatureMethod (
        var _Algorithm: String? = null
)

class CanonicalizationMethod (

        var _Algorithm: String? = null
)

class InfNFe (

        var ide: Ide,
        var emit: Emit,
        var dest: Dest,
        var entrega: Entrega,
        var det: Det,
        var total: Total,
        var transp: Transp,
        var pag: Pag,
        var infAdic: InfAdic,
        var _Id: String? = null
        
)

class InfAdic (
        var infCpl: String? = null
)

class Pag (
        var detPag: DetPag
)

class DetPag (
        // Getter Methods 

        // Setter Methods 

        var tPag: String? = null,
        var vPag: String? = null
)

class Transp (

        var modFrete: String? = null,
        var vol: Vol
)

class Vol (

        var qVol: String? = null,
        var esp: String? = null,
        var marca: String? = null,
        var nVol: String? = null,
        var pesoL: String? = null,
        var pesoB: String? = null
)

class Total (
        var icmsTot: ICMSTot
)

class ICMSTot (

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

        var prod: Prod,
        var imposto: Imposto,
        var _nItem: String? = null
)

class Imposto (

        var icms: ICMS,
        var pis: PIS,
        var cofins: COFINS
)

class COFINS (

        var cofinsnt: COFINSNT
)

class COFINSNT (
        var cst: String? = null
)

class PIS (

        var pisnt: PISNT
)

class PISNT (

        var cst: String? = null
)

class ICMS (

        var icmS40: ICMS40
)

class ICMS40 (
        var orig: String? = null,
        var cst: String? = null
)

class Prod (

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
        var comb: Comb
)

class Comb (
        var cProdANP: String? = null,
        var descANP: String? = null,
        var ufCons: String? = null
)

class Entrega (

        var cnpj: String? = null,
        var xLgr: String? = null,
        var nro: String? = null,
        var xBairro: String? = null,
        var cMun: String? = null,
        var xMun: String? = null,
        var uf: String? = null
)

class Dest (

        var cnpj: String? = null,
        var xNome: String? = null,
        var enderDest: EnderDest,
        var indIEDest: String? = null,
        var ie: String? = null,
        var email: String? = null
)

class EnderDest (

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

        var cnpj: String? = null,
        var xNome: String? = null,
        var enderEmit: EnderEmit,
        var ie: String? = null,
        var iest: String? = null,
        var crt: String? = null
)

class EnderEmit (

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