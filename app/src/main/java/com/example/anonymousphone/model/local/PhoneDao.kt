package com.example.anonymousphone.model.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PhoneDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPhone(phone : PhoneData)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDetails(details : PhoneDetailsData)

    //si recibimos un precio nulo, lo sanitizamos antes de guardar como -1 por ende no deberia mostrarse
    @Query("SELECT * FROM PHONE_DATA WHERE price != -1 ORDER BY name")
    fun getAllPhone():LiveData<List<PhoneData>>
    // aunque el producto no deberia estar en el recycler, por seguridad hacemos lo mismo en detalle
    @Query("SELECT * FROM PHONE_DETAILS_DATA WHERE price != -1 AND  id = :id")
    suspend fun getDetailsPhone(id:Int) : PhoneDetailsData ?
}