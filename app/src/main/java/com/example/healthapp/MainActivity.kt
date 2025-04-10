package com.example.healthapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import androidx.compose.ui.Modifier
import com.example.healthapp.data.AppDatabase
import com.example.healthapp.data.RoomHealthRepository
import com.example.healthapp.ui.screens.meal.MealScreen
import com.example.healthapp.ui.screens.sport.SportScreen
import com.example.healthapp.viewmodel.HealthViewModel
import com.example.healthapp.viewmodel.HealthViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val database = AppDatabase.getDatabase(this)
        val repository = RoomHealthRepository(
            database.healthDao(),
            database.sportDao()
        )

        setContent {
            val viewModel: HealthViewModel = viewModel(
                factory = HealthViewModelFactory(repository)
            )

            val navController = rememberNavController()

            Scaffold(
                bottomBar = {
                    BottomNavigationBar(navController)
                }
            ) { innerPadding ->
                NavHost(
                    navController = navController,
                    startDestination = "meal",
                    modifier = Modifier.padding(innerPadding)
                ) {
                    composable("meal") { MealScreen(viewModel) }
                    composable("sport") { SportScreen(viewModel) }
                }
            }
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    NavigationBar {
        NavigationBarItem(
            selected = navController.currentDestination?.route == "meal",
            onClick = { navController.navigate("meal") },
            label = { Text("饮食") },
            icon = {}
        )
        NavigationBarItem(
            selected = navController.currentDestination?.route == "sport",
            onClick = { navController.navigate("sport") },
            label = { Text("运动") },
            icon = {}
        )
    }
}
