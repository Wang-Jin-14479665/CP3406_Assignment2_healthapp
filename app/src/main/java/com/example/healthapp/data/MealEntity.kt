package com.example.healthapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "meals")
data class MealEntity(
    @PrimaryKey(autoGenerate = true) val mealId: Int = 0,
    val mealName: String  // 一顿饭的名字，如 早餐/午餐/晚餐
)
