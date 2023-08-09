package com.example.anonymousphone.model.remote.viewModelRetrofit

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.anonymousphone.model.remote.PhoneRepositoryRetrofit

class RetrofitViewModelFactory(private val application: Application, private val phoneRepositoryRetrofit: PhoneRepositoryRetrofit) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RetrofitViewModel::class.java)) {
            return RetrofitViewModel(application, phoneRepositoryRetrofit) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
