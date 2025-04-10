package com.example.healthapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.healthapp.model.Food



@Entity(tableName = "foods")
data class FoodEntity(
    @PrimaryKey(autoGenerate = true) val foodId: Int = 0,
    val foodName: String,   // Food Name
    val calories: Int,      // Calories 热量
    val mealId: Int         // 外键 mealId，关联 Meal 表  Foreign key mealId, associated with the Meal table
)