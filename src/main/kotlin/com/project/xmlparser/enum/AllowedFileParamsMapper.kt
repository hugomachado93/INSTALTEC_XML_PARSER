package com.project.xmlparser.enum

enum class AllowedFileParamsMapper(val param: String) {

    SILVER("silver"),
    GOLD("gold"),
    PLATINUM("platinum");

    companion object {
        fun getypeByName(name: String) = valueOf(name.toUpperCase())
    }

}