package com.example.anonymousphone.model.local

import androidx.lifecycle.LiveData

class PhoneRepository(private val phoneDao: PhoneDao) {

    suspend fun insertPhone(phone : PhoneData){
        phoneDao.insertPhone(phone)
    }

    suspend fun insertDetails (phone : PhoneDetailsData){
        phoneDao.insertDetails(phone)
    }

    fun getAllPhone() : LiveData<List<PhoneData>>{
        return phoneDao.getAllPhone()
    }

    suspend fun getDetailsPhone(id : Int) : PhoneDetailsData? {
        return phoneDao.getDetailsPhone(id)
    }
}