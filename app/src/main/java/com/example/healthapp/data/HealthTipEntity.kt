package com.example.healthapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "health_tip")
data class HealthTipEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,         // 小提示标题
    val description: String    // 小提示内容
)
