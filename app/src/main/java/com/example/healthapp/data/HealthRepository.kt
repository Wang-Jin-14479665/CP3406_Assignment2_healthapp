package com.example.healthapp.data

import com.example.healthapp.model.Food
import com.example.healthapp.data.FoodEntity
import com.example.healthapp.data.HealthDao
import kotlinx.coroutines.flow.Flow



class HealthRepository(private val dao: HealthDao) {

    suspend fun getAllMeals(): List<MealEntity> = dao.getAllMeals()

    suspend fun getFoodsByMealId(mealId: Int): List<FoodEntity> = dao.getFoodsByMealId(mealId)

    suspend fun insertMeal(meal: MealEntity): Long = dao.insertMeal(meal)

    suspend fun insertFood(food: FoodEntity) = dao.insertFood(food)

    suspend fun deleteMeal(meal: MealEntity) {
        dao.deleteFoodsByMealId(meal.mealId)  // 先删 foods
        dao.deleteMeal(meal)                  // 再删 meal
    }

    suspend fun deleteFood(food: FoodEntity) {
        dao.deleteFood(food)
    }

}