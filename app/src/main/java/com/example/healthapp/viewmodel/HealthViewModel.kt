package com.example.healthapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import com.example.healthapp.HealthApplication
import com.example.healthapp.model.Food
import com.example.healthapp.model.MealWithFoods
import com.example.healthapp.model.toEntity
import com.example.healthapp.model.toModel
import com.example.healthapp.data.HealthRepository
import com.example.healthapp.data.MealEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import androidx.lifecycle.viewModelScope

class HealthViewModel(private val repository: HealthRepository) : ViewModel() {

    private val _meals = MutableStateFlow<List<MealWithFoods>>(emptyList())
    val meals: StateFlow<List<MealWithFoods>> = _meals

    fun initializeDB() {
        viewModelScope.launch {
            repository.getAllMeals().collect { mealsList ->
                val result = mealsList.map { meal ->
                    val foods = repository.getFoodsByMealId(meal.mealId).map { it.toModel() }
                    MealWithFoods(meal, foods)
                }
                _meals.value = result
            }
        }
    }

    fun addMeal(mealName: String) {
        viewModelScope.launch {
            repository.insertMeal(MealEntity(mealName = mealName))
            initializeDB()
        }
    }

    fun addFood(mealId: Int, food: Food) {
        viewModelScope.launch {
            repository.insertFood(food.toEntity(mealId = mealId))
            initializeDB()
        }
    }

    fun deleteMeal(meal: MealEntity) {
        viewModelScope.launch {
            repository.deleteMeal(meal)
            initializeDB() // 刷新数据
        }
    }

    fun deleteFood(food: Food) {
        viewModelScope.launch {
            repository.deleteFood(food.toEntity(mealId = 0)) // mealId 不影响 delete
            initializeDB()
        }
    }


    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val app = (this[APPLICATION_KEY] as HealthApplication)
                HealthViewModel(app.repository)
            }
        }
    }
}
