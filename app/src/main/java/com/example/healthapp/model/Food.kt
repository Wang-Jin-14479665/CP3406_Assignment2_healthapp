package com.example.healthapp.model

import com.example.healthapp.data.FoodEntity


data class Food(
    val id: Int = 0,           // food 的 id
    val foodName: String,
    val calories: Int
)

fun Food.toEntity(mealId: Int): FoodEntity {
    return FoodEntity(
        foodId = this.id,
        foodName = this.foodName,
        calories = this.calories,
        mealId = mealId // 传入 mealId 外键 Pass in the mealId foreign key
    )
}

fun FoodEntity.toModel(): Food {
    return Food(
        id = this.foodId,
        foodName = this.foodName,
        calories = this.calories
    )
}