package com.example.healthapp.data

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import com.example.healthapp.remote.HealthTipRemoteDataSource

class RoomHealthRepository(
    private val dao: HealthDao,
    private val sportDao: SportDao,
    private val healthTipDao: HealthTipDao,
    private val remoteDataSource: HealthTipRemoteDataSource
) : HealthRepository {

    // Meal 相关
    override fun getAllMeals(): Flow<List<MealEntity>> = flow {
        emit(dao.getAllMeals())
    }

    override suspend fun getFoodsByMealId(mealId: Int): List<FoodEntity> {
        return dao.getFoodsByMealId(mealId)
    }

    override suspend fun insertMeal(meal: MealEntity): Long {
        return dao.insertMeal(meal)
    }

    override suspend fun deleteMeal(meal: MealEntity) {
        dao.deleteFoodsByMealId(meal.mealId)
        dao.deleteMeal(meal)
    }

    override suspend fun insertFood(food: FoodEntity) {
        dao.insertFood(food)
    }

    override suspend fun deleteFood(food: FoodEntity) {
        dao.deleteFood(food)
    }

    // Sport 相关
    override fun getAllSports(): Flow<List<SportEntity>> = sportDao.getAllSports()

    override suspend fun insertSport(sport: SportEntity) {
        sportDao.insertSport(sport)
    }

    override suspend fun deleteSport(sport: SportEntity) {
        sportDao.deleteSport(sport)
    }

    // HealthTip 相关
    override fun getAllHealthTips(): Flow<List<HealthTipEntity>> = healthTipDao.getAllTips()

    override suspend fun insertHealthTips(tips: List<HealthTipEntity>) {
        healthTipDao.insertAll(tips)
    }

    // 刷新 HealthTip 数据
    override suspend fun refreshHealthTips() {
        val tips = remoteDataSource.fetchHealthTips()
        Log.d("HealthRepository", "Fetched tips size: ${tips.size}")

        // 插入数据库（覆盖式刷新）
        insertHealthTips(tips)
    }

    // 仅获取数据（一般用于测试或特殊场景）
    override suspend fun fetchHealthTips(): List<HealthTipEntity> {
        val tips = remoteDataSource.fetchHealthTips()
        Log.d("HealthRepository", "Fetched tips size: ${tips.size}")
        return tips
    }
}
