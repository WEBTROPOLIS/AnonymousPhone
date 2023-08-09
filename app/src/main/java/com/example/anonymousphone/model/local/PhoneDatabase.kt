package com.example.anonymousphone.model.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [PhoneData::class, PhoneDetailsData::class],version = 1, exportSchema = false)
abstract class PhoneDatabase : RoomDatabase() {

    abstract fun phoneDataDao(): PhoneDao

    companion object{
        @Volatile
        private var INSTANCE: PhoneDatabase? = null

        fun getDatabase(context: Context):PhoneDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PhoneDatabase::class.java,
                    "PhoneDatabase"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }


}