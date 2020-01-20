package com.project.xmlparser.services

import com.google.cloud.storage.*
import com.project.xmlparser.dto.UploadFileResponse
import org.springframework.stereotype.Service
import java.util.*
import java.util.concurrent.TimeUnit

@Service
class CloudStorage {

    fun saveToDownload(byteArray: ByteArray): UploadFileResponse {

        val storage: Storage = StorageOptions.getDefaultInstance().service

        val bucket = storage.get("instaltec_store")

        val name = UUID.randomUUID()

        val blob = bucket.create("$name.xlsx", byteArray)

        val url = blob.signUrl(5, TimeUnit.MINUTES, Storage.SignUrlOption.withV4Signature())

        return UploadFileResponse(name.toString(), url)

    }

}