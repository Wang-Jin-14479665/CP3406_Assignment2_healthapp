package com.example.healthapp

import android.app.Application
import androidx.room.Room
import com.example.healthapp.data.AppDatabase
import com.example.healthapp.data.HealthRepository
import com.example.healthapp.data.RoomHealthRepository
import com.example.healthapp.remote.HealthTipApiModule
import com.example.healthapp.remote.HealthTipRemoteDataSource
import dagger.hilt.android.HiltAndroidApp

class HealthApplication : Application() {
    lateinit var roomHealthRepository: HealthRepository
        private set

    val repository: HealthRepository
        get() = roomHealthRepository

    override fun onCreate() {
        super.onCreate()
        val database = AppDatabase.getDatabase(this)

        roomHealthRepository = RoomHealthRepository(
            database.healthDao(),
            database.sportDao(),
            database.healthTipDao(),
            HealthTipRemoteDataSource(HealthTipApiModule.provideApiService())
        )
    }
}
