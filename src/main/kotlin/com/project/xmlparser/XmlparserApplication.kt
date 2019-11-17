package com.project.xmlparser

import com.project.xmlparser.entity.NfeProc
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import springfox.documentation.swagger2.annotations.EnableSwagger2
import kotlin.reflect.full.memberProperties

@EnableSwagger2
@SpringBootApplication
class XmlparserApplication : CommandLineRunner {
    override fun run(vararg args: String?) {
    }
}

fun main(args: Array<String>) {
    runApplication<XmlparserApplication>(*args)
}
