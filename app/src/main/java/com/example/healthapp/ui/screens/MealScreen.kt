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
            Text("Add a Meal")
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(meals) { mealWithFoods ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    shape = RoundedCornerShape(12.dp),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text("${mealWithFoods.meal.mealName}：", style = MaterialTheme.typography.titleMedium)

                            Button(onClick = { viewModel.deleteMeal(mealWithFoods.meal) }) {
                                Text("Delete Meal")
                            }
                        }

                        mealWithFoods.foods.forEach {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text("- ${it.foodName}: ${it.calories} kilocalorie")
                                Button(onClick = { viewModel.deleteFood(it) }) {
                                    Text("delete")
                                }
                            }
                        }

                        Text("Total Heat：${mealWithFoods.foods.sumOf { it.calories }} kilocalorie", fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(4.dp))

                        Button(onClick = {
                            selectedMealId = mealWithFoods.meal.mealId
                            showFoodDialog = true
                        }, modifier = Modifier.align(Alignment.End)) {
                            Text("Add a Food")
                        }
                    }
                }
            }
        }
    }

    // Meal 添加弹窗
    if (showMealDialog) {
        AlertDialog(
            onDismissRequest = { showMealDialog = false },
            title = { Text("Adding a Meal") },
            text = {
                OutlinedTextField(
                    value = inputMealName,
                    onValueChange = { inputMealName = it },
                    label = { Text("Meal name") }
                )
            },
            confirmButton = {
                Button(onClick = {
                    if (inputMealName.isNotBlank()) {
                        viewModel.addMeal(inputMealName)
                        showMealDialog = false
                        inputMealName = ""
                    }
                }) {
                    Text("Confirm")
                }
            },
            dismissButton = {
                Button(onClick = { showMealDialog = false }) {
                    Text("Cancel")
                }
            }
        )
    }

    // Food 添加弹窗
    if (showFoodDialog) {
        AlertDialog(
            onDismissRequest = { showFoodDialog = false },
            title = { Text("Add a Food") },
            text = {
                Column {
                    OutlinedTextField(
                        value = inputFoodName,
                        onValueChange = { inputFoodName = it },
                        label = { Text("Item") }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = inputCalories,
                        onValueChange = { inputCalories = it },
                        label = { Text("Heat (kcal)") }
                    )
                }
            },
            confirmButton = {
                Button(onClick = {
                    if (inputFoodName.isNotBlank() && inputCalories.isNotBlank()) {
                        val calories = inputCalories.toIntOrNull() ?: 0
                        viewModel.addFood(selectedMealId, Food(0, inputFoodName, calories))
                        showFoodDialog = false
                        inputFoodName = ""
                        inputCalories = ""
                    }
                }) {
                    Text("Confirm")
                }
            },
            dismissButton = {
                Button(onClick = { showFoodDialog = false }) {
                    Text("Cancel")
                }
            }
        )
    }
}
