package com.project.xmlparser.repository.CloudStorage

import com.google.cloud.storage.Acl
import com.google.cloud.storage.BucketInfo
import com.google.cloud.storage.Storage
import com.google.cloud.storage.StorageOptions
import com.project.xmlparser.entity.BlobEntity
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service

@Service
class CloudStorage(@Autowired val blobRepository: BlodRepository) {

    fun saveToDownload(byteArray: ByteArray): Int? {

        val storage: Storage = StorageOptions.getDefaultInstance().service

        val bucket = storage.get("instaltec_store")

        val blob = bucket.create("temp.xlsx", byteArray)

        blob.updateAcl(Acl.of(Acl.User.ofAllUsers(), Acl.Role.READER))

        val blobEntity = BlobEntity(null, blob.blobId.bucket, blob.blobId.name, blob.blobId.generation)

        val blobdb = blobRepository.save(blobEntity)

        return blobdb.id
    }

}