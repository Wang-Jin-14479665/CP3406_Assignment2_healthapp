// HealthRepository.kt
package com.example.healthapp.data

import com.example.healthapp.model.Food
import com.example.healthapp.data.MealEntity
import kotlinx.coroutines.flow.Flow

interface HealthRepository {
    fun getAllMeals(): Flow<List<MealEntity>>
    suspend fun getFoodsByMealId(mealId: Int): List<FoodEntity>
    suspend fun insertMeal(meal: MealEntity): Long
    suspend fun insertFood(food: FoodEntity)
    suspend fun deleteMeal(meal: MealEntity)
    suspend fun deleteFood(food: FoodEntity)
}