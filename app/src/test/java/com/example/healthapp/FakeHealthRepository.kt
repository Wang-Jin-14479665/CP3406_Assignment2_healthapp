package com.example.healthapp

import com.example.healthapp.data.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class FakeHealthRepository : HealthRepository {

    // Meal 部分
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
        return meal.mealId.toLong()
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

    // Sport 部分
    private val sportList = mutableListOf<SportEntity>()
    private val _sports = MutableStateFlow<List<SportEntity>>(emptyList())
    override fun getAllSports(): Flow<List<SportEntity>> = _sports.asStateFlow()

    override suspend fun insertSport(sport: SportEntity) {
        sportList.add(sport)
        _sports.value = sportList.toList()
    }

    override suspend fun deleteSport(sport: SportEntity) {
        sportList.remove(sport)
        _sports.value = sportList.toList()
    }

    // HealthTip 部分
    private val healthTipList = mutableListOf(
        HealthTipEntity(title = "Test Tip 1", description = "Description for Tip 1"),
        HealthTipEntity(title = "Test Tip 2", description = "Description for Tip 2"),
    )

    private val _healthTips = MutableStateFlow<List<HealthTipEntity>>(healthTipList.toList())
    override fun getAllHealthTips(): Flow<List<HealthTipEntity>> = _healthTips.asStateFlow()

    override suspend fun insertHealthTips(tips: List<HealthTipEntity>) {
        healthTipList.addAll(tips)
        _healthTips.value = healthTipList.toList()
    }

    override suspend fun fetchHealthTips(): List<HealthTipEntity> {
        return healthTipList
    }

    override suspend fun refreshHealthTips() {
        // 测试环境什么都不做（或者 log 一下）
    }

}
