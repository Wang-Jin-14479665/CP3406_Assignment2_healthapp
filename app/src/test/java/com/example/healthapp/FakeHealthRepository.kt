package com.example.healthapp

import com.example.healthapp.data.HealthRepository
import com.example.healthapp.data.FoodEntity
import com.example.healthapp.data.MealEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class FakeHealthRepository : HealthRepository {

    private val mealList = mutableListOf<MealEntity>()
    private val foodList = mutableListOf<FoodEntity>()

    private val _meals = MutableStateFlow<List<MealEntity>>(emptyList())
    override fun getAllMeals(): Flow<List<MealEntity>> = _meals.asStateFlow()

    override suspend fun getFoodsByMealId(mealId: Int): List<FoodEntity> {
        return foodList.filter { it.mealId == mealId }
    }

    override suspend fun insertMeal(meal: MealEntity): Long {
        mealList.add(meal)
        _meals.value = mealList.toList()
        return meal.mealId.toLong() // 随便返回一个 Long，不影响测试
    }

    override suspend fun deleteMeal(meal: MealEntity) {
        mealList.remove(meal)
        _meals.value = mealList.toList()
    }

    override suspend fun insertFood(food: FoodEntity) {
        foodList.add(food)
    }

    override suspend fun deleteFood(food: FoodEntity) {
        foodList.remove(food)
    }
}
