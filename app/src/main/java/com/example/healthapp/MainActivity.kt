package com.example.healthapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.healthapp.ui.screens.BottomNavigationBar
import com.example.healthapp.ui.theme.HealthAppTheme
import com.example.healthapp.viewmodel.HealthViewModel
import com.example.healthapp.viewmodel.HealthViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            HealthAppTheme {
                val navController = rememberNavController()

                // 使用 ViewModelFactory 正确注入 repository
                val viewModel: HealthViewModel = viewModel(
                    factory = HealthViewModelFactory(
                        (application as HealthApplication).repository
                    )
                )

                Scaffold(
                    bottomBar = {
                        BottomNavigationBar(navController)
                    }
                ) { innerPadding ->
                    NavGraph(navController, viewModel, Modifier.padding(innerPadding))
                }
            }
        }
    }
}

