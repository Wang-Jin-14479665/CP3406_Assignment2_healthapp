package com.example.healthapp.data

import com.example.healthapp.data.FoodEntity
import com.example.healthapp.data.MealEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RoomHealthRepository(
    private val dao: HealthDao,
    private val sportDao: SportDao  // 新增 SportDao
) : HealthRepository {

    // ================= Meal 相关 ==================

    // 查询所有 Meal（返回 Flow）
    override fun getAllMeals(): Flow<List<MealEntity>> = flow {
        emit(dao.getAllMeals())
    }

    // 根据 mealId 查询 foods
    override suspend fun getFoodsByMealId(mealId: Int): List<FoodEntity> {
        return dao.getFoodsByMealId(mealId)
    }

    // 插入 Meal
    override suspend fun insertMeal(meal: MealEntity): Long {
        return dao.insertMeal(meal)
    }

    // 删除 Meal（先删 Food 再删 Meal）
    override suspend fun deleteMeal(meal: MealEntity) {
        dao.deleteFoodsByMealId(meal.mealId) // 先删 foods
        dao.deleteMeal(meal)                // 再删 meal
    }

    // 插入 Food
    override suspend fun insertFood(food: FoodEntity) {
        dao.insertFood(food)
    }

    // 删除 Food
    override suspend fun deleteFood(food: FoodEntity) {
        dao.deleteFood(food)
    }

    // ================= Sport 相关 ==================

    override fun getAllSports(): Flow<List<SportEntity>> = sportDao.getAllSports()

    override suspend fun insertSport(sport: SportEntity) {
        sportDao.insertSport(sport)
    }

    override suspend fun deleteSport(sport: SportEntity) {
        sportDao.deleteSport(sport)
    }

}
