package com.example.healthapp

import android.app.Application
import androidx.room.Room
import com.example.healthapp.data.AppDatabase
import com.example.healthapp.data.HealthRepository
import com.example.healthapp.data.RoomHealthRepository

class HealthApplication : Application() {
    lateinit var repository: RoomHealthRepository

    override fun onCreate() {
        super.onCreate()

        val database = AppDatabase.getDatabase(this)

        repository = RoomHealthRepository(
            database.healthDao(),
            database.sportDao()  // 必须传入 sportDao
        )
    }
}
