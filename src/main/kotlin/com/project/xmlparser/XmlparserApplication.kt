package com.project.xmlparser

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.xml.sax.helpers.DefaultHandler
import springfox.documentation.swagger2.annotations.EnableSwagger2
import java.io.File
import javax.xml.parsers.SAXParserFactory

@EnableSwagger2
@SpringBootApplication
@EnableDiscoveryClient
class XmlparserApplication : CommandLineRunner {
    override fun run(vararg args: String?) {
    }
}

fun main(args: Array<String>) {
    runApplication<XmlparserApplication>(*args)
}
