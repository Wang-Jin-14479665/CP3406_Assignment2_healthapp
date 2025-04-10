package com.example.healthapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.healthapp.ui.screens.meal.MealScreen
import com.example.healthapp.viewmodel.HealthViewModel
import androidx.compose.material3.Surface
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel: HealthViewModel = viewModel(factory = HealthViewModel.Factory)

            Surface(modifier = Modifier.fillMaxSize()) {
                MealScreen(viewModel)
            }
        }
    }
}
