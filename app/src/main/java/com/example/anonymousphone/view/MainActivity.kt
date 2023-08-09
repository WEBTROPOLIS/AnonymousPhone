package com.example.anonymousphone.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.anonymousphone.databinding.ActivityMainBinding
import com.example.anonymousphone.model.local.PhoneDatabase
import com.example.anonymousphone.model.local.PhoneRepository
import com.example.anonymousphone.model.remote.PhoneRepositoryRetrofit
import com.example.anonymousphone.model.remote.viewModelRetrofit.RetrofitViewModel
import com.example.anonymousphone.model.remote.viewModelRetrofit.RetrofitViewModelFactory


class MainActivity : AppCompatActivity() {

    private lateinit var phoneRepositoryRetrofit : PhoneRepositoryRetrofit
    private lateinit var mBinding : ActivityMainBinding
    private lateinit var phoneViewModel: RetrofitViewModel




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        val phoneDao = PhoneDatabase.getDatabase(application).phoneDataDao()
        val phoneRepository = PhoneRepository(phoneDao)

        phoneRepositoryRetrofit = PhoneRepositoryRetrofit(phoneRepository)


        phoneViewModel =
            ViewModelProvider(this,
                RetrofitViewModelFactory(
                    application,
                    phoneRepositoryRetrofit
                )
            )[RetrofitViewModel::class.java]


        phoneViewModel.fetchAndInsertData()

        launchFragment()


    }

    private fun launchFragment() {
      supportFragmentManager.beginTransaction()
          .replace(mBinding.frame.id,PhoneFragment())
          .addToBackStack(null)
          .commit()
    }


}