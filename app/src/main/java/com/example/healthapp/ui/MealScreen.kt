// MealScreen.kt
package com.example.healthapp.ui.screens.meal

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.text.font.FontWeight
import com.example.healthapp.model.Food
import com.example.healthapp.viewmodel.HealthViewModel

@Composable
fun MealScreen(viewModel: HealthViewModel) {
    val meals by viewModel.meals.collectAsState()
    LaunchedEffect(Unit) { viewModel.initializeDB() }

    var showMealDialog by remember { mutableStateOf(false) }
    var inputMealName by remember { mutableStateOf("") }

    var showFoodDialog by remember { mutableStateOf(false) }
    var inputFoodName by remember { mutableStateOf("") }
    var inputCalories by remember { mutableStateOf("") }
    var selectedMealId by remember { mutableStateOf(-1) }

    Column(modifier = Modifier.padding(16.dp)) {
        Button(onClick = { showMealDialog = true }, modifier = Modifier.align(Alignment.CenterHorizontally)) {
            Text("添加 Meal")
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(meals) { mealWithFoods ->
                Card(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                    shape = RoundedCornerShape(12.dp),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text("${mealWithFoods.meal.mealName}：", style = MaterialTheme.typography.titleMedium)

                        Button(onClick = { viewModel.deleteMeal(mealWithFoods.meal) }) {
                            Text("删除 Meal")
                        }

                        mealWithFoods.foods.forEach {
                            Text("- ${it.foodName}: ${it.calories} 千卡")
                            Button(onClick = { viewModel.deleteFood(it) }) {
                                Text("删除")
                            }
                        }

                        Text("总热量：${mealWithFoods.foods.sumOf { it.calories }} 千卡", fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(4.dp))
                        Button(onClick = {
                            selectedMealId = mealWithFoods.meal.mealId
                            showFoodDialog = true
                        }, modifier = Modifier.align(Alignment.End)) {
                            Text("添加食物")
                        }
                    }
                }
            }
        }
    }

    // Dialog 保留不变（复制你之前的逻辑）
    // showMealDialog 和 showFoodDialog
}
