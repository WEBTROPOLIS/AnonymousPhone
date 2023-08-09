package com.example.anonymousphone.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "phone_details_data")
data class PhoneDetailsData(
    @PrimaryKey
    val id : Int,
    val name : String,
    val price : Long,
    val image : String,
    val description : String,
    val lastPrice : Long,
    val credit : Boolean

)