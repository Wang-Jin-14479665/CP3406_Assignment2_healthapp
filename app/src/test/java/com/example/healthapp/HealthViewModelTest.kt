package com.example.healthapp

import com.example.healthapp.viewmodel.HealthViewModel
import com.example.healthapp.data.MealEntity
import com.example.healthapp.model.Food
import com.example.healthapp.data.SportEntity
import com.example.healthapp.model.toModel
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.Assert.*
import org.junit.Before

class HealthViewModelTest {

    private lateinit var viewModel: HealthViewModel
    private lateinit var fakeRepository: FakeHealthRepository

    @Before
    fun setup() {
        fakeRepository = FakeHealthRepository()
        viewModel = HealthViewModel(fakeRepository)
    }

    // 测试添加 Meal
    @Test
    fun addMeal_shouldInsertMealCorrectly() {
        viewModel.addMeal("早餐")
        val meals = viewModel.meals.value
        assertTrue("Meal not found", meals.any { it.meal.mealName == "早餐" })
    }

    // 测试删除 Meal
    @Test
    fun deleteMeal_shouldDeleteMealCorrectly() {
        viewModel.addMeal("早餐")
        val meal = viewModel.meals.value.find { it.meal.mealName == "早餐" }?.meal
        assertNotNull(meal)

        meal?.let {
            viewModel.deleteMeal(it)
            val mealsAfterDelete = viewModel.meals.value
            assertFalse("Meal was not deleted", mealsAfterDelete.any { m -> m.meal.mealName == "早餐" })
        }
    }

    // 测试添加 Food
    @Test
    fun addFood_shouldInsertFoodCorrectly() {
        runBlocking {
            viewModel.addMeal("午餐")
            val meal = viewModel.meals.value.find { it.meal.mealName == "午餐" }?.meal
            assertNotNull(meal)

            meal?.let {
                val food = Food(foodName = "鸡胸肉", calories = 200)
                viewModel.addFood(it.mealId, food)

                val foods = fakeRepository.getFoodsByMealId(it.mealId)
                assertTrue("Food not found", foods.any { f -> f.foodName == "鸡胸肉" })
            }
        }
    }

    // 测试删除 Food
    @Test
    fun deleteFood_shouldDeleteFoodCorrectly() {
        runBlocking {
            viewModel.addMeal("午餐")
            val meal = viewModel.meals.value.find { it.meal.mealName == "午餐" }?.meal
            assertNotNull(meal)

            meal?.let {
                val food = Food(foodName = "鸡胸肉", calories = 200)
                viewModel.addFood(it.mealId, food)

                val foods = fakeRepository.getFoodsByMealId(it.mealId)
                val targetFood = foods.find { f -> f.foodName == "鸡胸肉" }
                assertNotNull(targetFood)

                targetFood?.let { f ->
                    viewModel.deleteFood(f.toModel())

                    val foodsAfterDelete = fakeRepository.getFoodsByMealId(it.mealId)
                    assertFalse("Food was not deleted", foodsAfterDelete.any { x -> x.foodName == "鸡胸肉" })
                }
            }
        }
    }

    // 测试添加 Sport
    @Test
    fun addSport_shouldInsertSportCorrectly() {
        viewModel.addSport("跑步", 120, 300)
        val sports = viewModel.sports.value
        assertTrue("Sport not found", sports.any { it.sportName == "跑步" })
    }

    // 测试删除 Sport
    @Test
    fun deleteSport_shouldDeleteSportCorrectly() {
        viewModel.addSport("跑步", 120, 300)
        val sport = viewModel.sports.value.find { it.sportName == "跑步" }
        assertNotNull(sport)

        sport?.let {
            viewModel.deleteSport(it)
            val sportsAfterDelete = viewModel.sports.value
            assertFalse("Sport was not deleted", sportsAfterDelete.any { s -> s.sportName == "跑步" })
        }
    }
}
