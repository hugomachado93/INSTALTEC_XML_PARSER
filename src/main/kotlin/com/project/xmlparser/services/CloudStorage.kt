package com.project.xmlparser.services

import com.google.cloud.storage.*
import org.springframework.stereotype.Service
import java.net.URL
import java.util.*
import java.util.concurrent.TimeUnit

@Service
class CloudStorage {

    fun saveToDownload(byteArray: ByteArray): URL {

        val storage: Storage = StorageOptions.getDefaultInstance().service

        val bucket = storage.get("instaltec_store")

        val name = UUID.randomUUID()

        val blob = bucket.create("$name.xlsx", byteArray)

        return blob.signUrl(5, TimeUnit.MINUTES, Storage.SignUrlOption.withV4Signature())
    }

}