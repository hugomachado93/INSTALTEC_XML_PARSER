package com.project.xmlparser

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(proxyBeanMethods = false)
class XmlparserApplication

fun main(args: Array<String>) {
    runApplication<XmlparserApplication>(*args)
}
