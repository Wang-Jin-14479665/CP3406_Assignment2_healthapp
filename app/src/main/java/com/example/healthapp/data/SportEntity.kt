package com.example.healthapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * SportEntity.kt
 * 用于 Room 数据库 的 Entity 数据结构
 * The Entity data structure for the Room database
 */

@Entity(tableName = "sports")
data class SportEntity(
    @PrimaryKey(autoGenerate = true)
    val sportId: Int = 0,           // 主键 id 自增 The primary key id increases automatically
    val sportName: String,          // Sports name
    val avgHeartRate: Int,          // average heart rate
    val caloriesBurned: Int         // calorie consumption
)
