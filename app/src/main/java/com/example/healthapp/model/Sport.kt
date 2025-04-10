package com.example.healthapp.model

/**
 * Sport.kt
 * 运动记录数据模型
 * 用于 Compose 界面层 和 数据库Entity的转换
 */

data class Sport(
    val sportId: Int = 0,             // id 主键 自增
    val sportName: String,            // 运动项目
    val avgHeartRate: Int,            // 平均心率
    val caloriesBurned: Int           // 消耗热量
)