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
    val healthTips by viewModel.healthTips.collectAsState()

    // 生成随机步数和心率（假数据）
    val step = remember { viewModel.generateRandomStep() }
    val heartRate = remember { viewModel.generateRandomHeartRate() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // 步数
        DashboardCard(title = "Step Count", content = "$step steps")
        // 心率
        DashboardCard(title = "Hart Rate", content = "$heartRate beats/m")

        // 最近饮食
        DashboardCard(
            title = "Latest diet record",
            content = latestMeal?.meal?.mealName ?: "no record found"
        )

        // 最近运动
        DashboardCard(
            title = "Latest Exercise record",
            content = latestSport?.sportName ?: "no record found"
        )

        // 健康小提示（随机）
        Card(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = healthTips.randomOrNull()?.title ?: "暂无健康小提示",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(16.dp)
            )
        }

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
