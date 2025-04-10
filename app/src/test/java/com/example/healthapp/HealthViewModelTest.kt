package com.example.healthapp

import com.example.healthapp.viewmodel.HealthViewModel
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

    @Test
    fun addMeal_shouldInsertMealCorrectly() {
        viewModel.addMeal("早餐")
        val meals = viewModel.meals.value
        assert(meals.any { it.meal.mealName == "早餐" })
    }
}
