package com.example.healthapp.model

import com.example.healthapp.data.FoodEntity
import com.example.healthapp.data.MealEntity

data class MealWithFoods(
    val meal: MealEntity,
    val foods: List<Food>
)