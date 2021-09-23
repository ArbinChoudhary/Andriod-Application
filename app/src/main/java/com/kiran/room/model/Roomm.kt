package com.kiran.room.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Roomm(
    @PrimaryKey
    val _id: String = "",
    val roomnum: String? = null,
    val roomtype: Int? = null,
    val photo: String? = null
)