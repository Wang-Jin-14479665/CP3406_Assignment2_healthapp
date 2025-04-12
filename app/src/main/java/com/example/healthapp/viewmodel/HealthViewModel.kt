package com.example.healthapp.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.healthapp.HealthApplication
import com.example.healthapp.data.HealthRepository
import com.example.healthapp.data.MealEntity
import com.example.healthapp.data.SportEntity
import com.example.healthapp.model.Food
import com.example.healthapp.model.MealWithFoods
import com.example.healthapp.model.toEntity
import com.example.healthapp.model.toModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY


import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import com.example.healthapp.data.HealthTipEntity



class HealthViewModel(private val repository: HealthRepository) : ViewModel() {

    // HealthTip 数据流
    val healthTips: StateFlow<List<HealthTipEntity>> =
        repository.getAllHealthTips()
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(5000),
                emptyList()
            )

    // 健康小提示 刷新函数
    fun refreshHealthTips() = viewModelScope.launch {
        val tips = repository.fetchHealthTips()  // 返回 List<HealthTipEntity>
        repository.insertHealthTips(tips)  // 插入数据库
    }

    // 初始化的时候调用刷新
    init {
        Log.d("HealthViewModel", "Init ViewModel 执行了")
        refreshHealthTips()
    }

    // Meal 数据
    private val _meals = MutableStateFlow<List<MealWithFoods>>(emptyList())
    val meals: StateFlow<List<MealWithFoods>> = _meals.asStateFlow()

    // Sport 数据
    private val _sports = MutableStateFlow<List<SportEntity>>(emptyList())
    val sports: StateFlow<List<SportEntity>> = _sports.asStateFlow()

    // Dashboard 专用 - 获取最新 Meal 和 Sport 数据
    val latestMeal: StateFlow<MealWithFoods?> = meals.map { it.lastOrNull() }.stateIn(
        viewModelScope, SharingStarted.WhileSubscribed(5000), null
    )
    val latestSport: StateFlow<SportEntity?> = sports.map { it.lastOrNull() }.stateIn(
        viewModelScope, SharingStarted.WhileSubscribed(5000), null
    )

    // 初始化 Meal 和 Sport 数据
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
        initializeSport() // 启动时同步初始化 Sport
    }

    // Sport 初始化
    fun initializeSport() {
        viewModelScope.launch {
            repository.getAllSports().collect { sportList ->
                _sports.value = sportList
            }
        }
    }

    // Meal 相关操作
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
            initializeDB()
        }
    }

    fun deleteFood(food: Food) {
        viewModelScope.launch {
            repository.deleteFood(food.toEntity(mealId = 0))
            initializeDB()
        }
    }

    // Sport 相关操作
    fun addSport(sportName: String, avgHeartRate: Int, caloriesBurned: Int) {
        viewModelScope.launch {
            val newSport = SportEntity(
                sportName = sportName,
                avgHeartRate = avgHeartRate,
                caloriesBurned = caloriesBurned
            )
            repository.insertSport(newSport)
        }
    }

    fun deleteSport(sport: SportEntity) {
        viewModelScope.launch {
            repository.deleteSport(sport)
        }
    }

    // Dashboard 用 - 随机数据
    fun generateRandomStep(): Int = (2000..8000).random()
    fun generateRandomHeartRate(): Int = (60..120).random()

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val app = (this[APPLICATION_KEY] as HealthApplication)
                HealthViewModel(app.repository)
            }
        }
    }
}
