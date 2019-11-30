package com.project.xmlparser.repository.CloudStorage

import com.google.cloud.storage.*
import com.project.xmlparser.entity.BlobEntity
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import java.nio.ByteBuffer
import java.nio.channels.WritableByteChannel

@Service
class CloudStorage(@Autowired val blobRepository: BlodRepository) {

    fun saveToDownload(byteArray: ByteArray): String {

        val storage: Storage = StorageOptions.getDefaultInstance().service

        val bucket = storage.get("instaltec_store")

        val name = System.currentTimeMillis()

        val blob = bucket.create("$name.xlsx", byteArray)

        val blobEntity = BlobEntity(null, blob.blobId.bucket, blob.blobId.name, blob.blobId.generation)

        val blobdb = blobRepository.save(blobEntity)

        return blob.blobId.name
    }

}