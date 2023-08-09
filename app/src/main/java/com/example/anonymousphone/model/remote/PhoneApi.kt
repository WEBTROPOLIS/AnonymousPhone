package com.example.anonymousphone.model.remote

import com.example.anonymousphone.model.local.PhoneData
import com.example.anonymousphone.model.local.PhoneDetailsData
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.Response


interface PhoneApi {

    @GET("products/")
    suspend fun fetchPhoneData(): Response<List<PhoneData>>

    @GET("details/{id}")
    suspend fun fetchPhoneDetails(@Path("id") id: Int): Response<PhoneDetailsData>
}