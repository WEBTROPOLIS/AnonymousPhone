package com.example.anonymousphone.model.remote.viewModelRetrofit

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.anonymousphone.model.remote.PhoneRepositoryRetrofit
import kotlinx.coroutines.launch



class RetrofitViewModel(application: Application, private val phoneRepositoryRetrofit: PhoneRepositoryRetrofit) : AndroidViewModel(application) {

    fun fetchAndInsertData() {
        viewModelScope.launch {
            phoneRepositoryRetrofit.fetchPhoneDataAndInsert()
        }
    }
}
