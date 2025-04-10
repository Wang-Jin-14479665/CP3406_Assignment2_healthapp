package com.example.healthapp

import android.app.Application
import androidx.room.Room
import com.example.healthapp.data.AppDatabase
import com.example.healthapp.data.HealthRepository
import com.example.healthapp.data.RoomHealthRepository

class HealthApplication : Application() {

    lateinit var db: AppDatabase
    lateinit var repository: HealthRepository

    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "health-db"
        ).build()

        repository = RoomHealthRepository(db.healthDao())
    }
}
