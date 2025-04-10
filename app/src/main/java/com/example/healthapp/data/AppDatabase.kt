package com.example.healthapp.data

import androidx.room.Database
import androidx.room.RoomDatabase



@Database(entities = [MealEntity::class, FoodEntity::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun healthDao(): HealthDao
}
