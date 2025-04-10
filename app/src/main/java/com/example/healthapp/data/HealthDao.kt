package com.example.healthapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow



@Dao
interface HealthDao {
    // 插入 Meal
    @Insert
    suspend fun insertMeal(meal: MealEntity): Long

    // 插入 Food
    @Insert
    suspend fun insertFood(food: FoodEntity)

    // 查询所有 Meal
    @Query("SELECT * FROM meals")
    suspend fun getAllMeals(): List<MealEntity>

    // 查询某个 Meal 下所有 Food
    @Query("SELECT * FROM foods WHERE mealId = :mealId")
    suspend fun getFoodsByMealId(mealId: Int): List<FoodEntity>

    // 删除 Meal
    @Delete
    suspend fun deleteMeal(meal: MealEntity)

    // 删除 Food
    @Delete
    suspend fun deleteFood(food: FoodEntity)

    // 删除指定 mealId 的所有 Food
    @Query("DELETE FROM foods WHERE mealId = :mealId")
    suspend fun deleteFoodsByMealId(mealId: Int)

}