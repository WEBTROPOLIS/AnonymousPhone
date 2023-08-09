package com.example.anonymousphone.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "phone_data")
data class PhoneData (
    @PrimaryKey
    val id:Int,
    val name: String,
    val price: Long,
    val image: String
)

