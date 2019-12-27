package com.project.xmlparser.repository

import com.project.xmlparser.entity.BlobEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BlodRepository : JpaRepository<BlobEntity, Int>