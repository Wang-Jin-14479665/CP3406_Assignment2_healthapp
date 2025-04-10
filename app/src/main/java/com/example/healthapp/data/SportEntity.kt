package com.example.healthapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * SportEntity.kt
 * 用于 Room 数据库 的 Entity 数据结构
 */

@Entity(tableName = "sports")
data class SportEntity(
    @PrimaryKey(autoGenerate = true)
    val sportId: Int = 0,           // 主键 id 自增
    val sportName: String,          // 运动名称
    val avgHeartRate: Int,          // 平均心率
    val caloriesBurned: Int         // 消耗热量
)
