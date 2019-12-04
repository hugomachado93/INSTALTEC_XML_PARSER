package com.project.xmlparser.repository.storage

import com.google.cloud.storage.*
import com.project.xmlparser.entity.BlobEntity
import com.project.xmlparser.repository.BlodRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class CloudStorage {

    fun saveToDownload(byteArray: ByteArray): String {

        val storage: Storage = StorageOptions.getDefaultInstance().service

        val bucket = storage.get("instaltec_store")

        val name = UUID.randomUUID()

        val blob = bucket.create("$name.xlsx", byteArray)

        return blob.blobId.name
    }

}