package com.example.healthapp

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.healthapp.ui.screens.DashboardScreen
import com.example.healthapp.ui.screens.meal.MealScreen
import com.example.healthapp.ui.screens.sport.SportScreen
import com.example.healthapp.viewmodel.HealthViewModel

@Composable
fun NavGraph(
    navController: NavHostController,
    viewModel: HealthViewModel,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = "dashboard",
        modifier = modifier
    ) {
        composable("dashboard") { DashboardScreen(viewModel) }
        composable("meal") { MealScreen(viewModel) }
        composable("sport") { SportScreen(viewModel) }
    }
}