package com.example.healthapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Alignment
import com.example.healthapp.viewmodel.HealthViewModel

@Composable
fun DashboardScreen(viewModel: HealthViewModel) {
    // 获取最新Meal和Sport数据
    val latestMeal by viewModel.latestMeal.collectAsState()
    val latestSport by viewModel.latestSport.collectAsState()

    // 生成随机步数和心率（假数据）
    val step = remember { viewModel.generateRandomStep() }
    val heartRate = remember { viewModel.generateRandomHeartRate() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        DashboardCard(title = "Step Count", content = "$step steps")
        DashboardCard(title = "Hart Rate", content = "$heartRate beats/m")

        DashboardCard(
            title = "Latest diet record",
            content = latestMeal?.meal?.mealName ?: "no record found"
        )

        DashboardCard(
            title = "Latest Exercise record",
            content = latestSport?.sportName ?: "no record found"
        )
    }
}

@Composable
fun DashboardCard(title: String, content: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(text = title, style = MaterialTheme.typography.titleMedium)
            Text(text = content, style = MaterialTheme.typography.bodyLarge)
        }
    }
}
