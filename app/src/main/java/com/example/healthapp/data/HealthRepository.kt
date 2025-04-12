// HealthRepository.kt
package com.example.healthapp.data

import com.example.healthapp.model.Food
import com.example.healthapp.data.MealEntity
import kotlinx.coroutines.flow.Flow

interface HealthRepository {
    // Meal 相关
    fun getAllMeals(): Flow<List<MealEntity>>
    suspend fun getFoodsByMealId(mealId: Int): List<FoodEntity>
    suspend fun insertMeal(meal: MealEntity): Long
    suspend fun insertFood(food: FoodEntity)
    suspend fun deleteMeal(meal: MealEntity)
    suspend fun deleteFood(food: FoodEntity)

    // Sport 相关
    fun getAllSports(): Flow<List<SportEntity>>
    suspend fun insertSport(sport: SportEntity)
    suspend fun deleteSport(sport: SportEntity)

    // HealthTip 相关
    fun getAllHealthTips(): Flow<List<HealthTipEntity>>
    suspend fun insertHealthTips(tips: List<HealthTipEntity>)
    suspend fun refreshHealthTips()
    suspend fun fetchHealthTips(): List<HealthTipEntity>

}