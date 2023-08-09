package com.example.anonymousphone

import com.example.anonymousphone.model.local.PhoneData
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
class TestPhoneData {

    private lateinit var phoneData: PhoneData

    @Before
    fun setUp(){
        phoneData = PhoneData(
            id = 1,
            name = "Prueba",
            price = 3000L,
            image = "Url"

        )
    }

    @After
    fun tearDown(){

    }

    @Test
    fun testConstructor(){
        assert(phoneData.id == 1)
        assert(phoneData.name =="Prueba")
        assert(phoneData.price == 3000L)
        assert(phoneData.image == "Url")

    }
}