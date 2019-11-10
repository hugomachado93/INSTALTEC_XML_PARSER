package com.project.xmlparser

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import springfox.documentation.swagger2.annotations.EnableSwagger2

@EnableSwagger2
@SpringBootApplication
class XmlparserApplication

fun main(args: Array<String>) {
    runApplication<XmlparserApplication>(*args)
}
