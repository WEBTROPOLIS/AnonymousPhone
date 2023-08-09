package com.example.anonymousphone

import android.content.Context

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.anonymousphone.model.local.PhoneDao
import com.example.anonymousphone.model.local.PhoneData
import com.example.anonymousphone.model.local.PhoneDatabase
import com.example.anonymousphone.model.local.PhoneDetailsData
import kotlinx.coroutines.runBlocking

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class TestRoom {
    private lateinit var mDao : PhoneDao
    private lateinit var db : PhoneDatabase

    @Before
    fun setUp(){
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context,PhoneDatabase::class.java).build()
        mDao = db.phoneDataDao()
    }

    @After
    fun shutDown(){
        db.close()
    }

    @Test
    fun insertPhoneTest() = runBlocking {
      val newPhone =  PhoneData(1,"prueba",3000L,"img")
        mDao.insertPhone(newPhone)
        val mPhone = mDao.getAllPhone()
        val mPhoneList : List<PhoneData> = mPhone.value?: emptyList()

        assert(!mPhoneList.isEmpty())
        assert(mPhoneList.size == 1)
        assert(mPhoneList[0].id==1)

    }

    @Test
    fun insertDetailsTest() = runBlocking {
        val phoneDetails = PhoneDetailsData(
            12,
            "PruebaDetalle",
            3500L,
            "url.jpg",
            "testing",
            5000L,
             true
        )

        mDao.insertDetails(phoneDetails)
        val mPhoneDetails = mDao.getDetailsPhone(12)
        assert(mPhoneDetails?.id == 12)
        assert(mPhoneDetails?.price == 3500L)
    }
}