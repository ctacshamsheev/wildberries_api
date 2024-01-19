package com.shamsheev.wildberries.api.statistics.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "note_table")
data class Note(
    @Id
    @GeneratedValue
    var id: Long? = null,

    var name: String,
    var address: String,
    var phone: String,
)