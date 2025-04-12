package com.example.healthapp.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable

@Composable
fun HealthAppTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = LightColorScheme, // Light 模式配色
        typography = Typography, // 字体配置
        shapes = Shapes, // 圆角配置
        content = content
    )
}
