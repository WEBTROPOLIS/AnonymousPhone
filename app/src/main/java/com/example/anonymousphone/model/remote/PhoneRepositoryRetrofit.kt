package com.example.anonymousphone.model.remote

import android.util.Log
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import com.example.anonymousphone.model.local.PhoneDao

import com.example.anonymousphone.model.local.PhoneData
import com.example.anonymousphone.model.local.PhoneDetailsData
import com.example.anonymousphone.model.local.PhoneRepository


class PhoneRepositoryRetrofit(private val phoneRepo: PhoneRepository) {

    private val apiService = RetrofitClient.getRetrofit()

    suspend fun fetchPhoneDataAndInsert() {
        try {
            val response = apiService.fetchPhoneData()

            if (response.isSuccessful) {
                val phoneList = response.body()

                if (phoneList != null) {
                    for (phone in phoneList) {
                        insertPhone(phone)
                        fetchAndInsertDetails(phone.id)
                    }
                } else {
                   // Log.d("Error", "No se encontraron datos")
                }
            } else {
               // Log.d("Error", "Error en la respuesta del servidor: ${response.code()}")
            }
        } catch (e: Exception) {
           // Log.d("Error", "Error en la conexión: ${e.message}")
        }
    }



    private suspend fun fetchAndInsertDetails(id: Int) {
        try {
            val response = apiService.fetchPhoneDetails(id)
            if (response.isSuccessful) {
                val details = response.body()
                details?.let {
                    insertDetails(it)
                }
            }
        } catch (_: Exception) {

        }
    }

    suspend fun insertPhone(phone: PhoneData) {
        val sanitizedPhone = controlNullPhoneData(phone)
        phoneRepo.insertPhone(sanitizedPhone)
    }

    suspend fun insertDetails(details: PhoneDetailsData) {
        val sanitizedDetailsPhone = controlNullPhoneDetails(details)
        phoneRepo.insertDetails(sanitizedDetailsPhone)
    }

    private fun controlNullPhoneData(phone:PhoneData):PhoneData{
        return phone.copy(
            name = phone.name ?: "Sin Nombre",
            price = phone.price ?: -1,
            image = phone.image ?: "Sin Imagen"


        )
    }

    private fun controlNullPhoneDetails(phone:PhoneDetailsData):PhoneDetailsData{
        return phone.copy(
            name = phone.name ?: "Sin Nombre",
            price = phone.price ?: -1,
            image = phone.image ?: "Sin Imagen",
            description = phone.description ?: "Sin Descripción",
            lastPrice = phone.lastPrice ?: -1,
            credit = phone.credit ?: false
        )
    }



}

