package com.example.anonymousphone.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.anonymousphone.model.local.PhoneData
import com.example.anonymousphone.model.local.PhoneDatabase
import com.example.anonymousphone.model.local.PhoneDetailsData
import com.example.anonymousphone.model.local.PhoneRepository

import kotlinx.coroutines.launch

class PhoneViewModel (application: Application ) : AndroidViewModel(application) {
    private val phoneRepository : PhoneRepository = PhoneRepository(PhoneDatabase.getDatabase(application).phoneDataDao())

    fun insertPhone(phone: PhoneData) { viewModelScope.launch { phoneRepository.insertPhone(phone) }}

    fun insertDetails(phone: PhoneDetailsData) { viewModelScope.launch { phoneRepository.insertDetails(phone) }}

    fun getAllPhone():LiveData<List<PhoneData>>{
        return phoneRepository.getAllPhone()
    }

    suspend fun getOnePhone(id : Int): PhoneDetailsData?{
        return phoneRepository.getDetailsPhone(id)
    }



}