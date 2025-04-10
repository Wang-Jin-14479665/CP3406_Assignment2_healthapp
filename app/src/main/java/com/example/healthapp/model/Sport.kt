package com.example.healthapp.model

/**
 * Sport.kt
 * 运动记录数据模型 Motion recording data model
 * 用于 Compose 界面层 和 数据库Entity的转换
 * For the conversion of the Compose interface layer and the database Entity
 */

data class Sport(
    val sportId: Int = 0,             // id 主键 自增
    val sportName: String,            // 运动项目
    val avgHeartRate: Int,            // 平均心率
    val caloriesBurned: Int           // 消耗热量
)