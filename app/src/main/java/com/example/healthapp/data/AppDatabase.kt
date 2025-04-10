package com.example.healthapp.data

import android.content.Context
import androidx.room.Room
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.healthapp.data.SportEntity



@Database(entities = [MealEntity::class, FoodEntity::class, SportEntity::class],
    version = 3,
    exportSchema = false)

abstract class AppDatabase : RoomDatabase() {
    abstract fun healthDao(): HealthDao
    abstract fun sportDao(): SportDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "health_database"
                )
                    .fallbackToDestructiveMigration()  // 升级时自动清空数据库（测试时常用）
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }

}
