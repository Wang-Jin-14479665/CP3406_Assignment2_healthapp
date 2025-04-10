package com.example.healthapp

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.healthapp.ui.screens.meal.MealScreen
import com.example.healthapp.viewmodel.HealthViewModel
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MealScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun testAddMealFlow() {
        // 点击 添加 Meal 按钮
        composeTestRule.onNodeWithText("添加 Meal").performClick()

        // 检查弹出输入框
        composeTestRule.onNodeWithText("Meal 名称").assertIsDisplayed()

        // 输入 Meal 名称
        composeTestRule.onNodeWithText("Meal 名称").performTextInput("午餐")

        // 点击确认
        composeTestRule.onNodeWithText("确认").performClick()

        // 检查 "午餐" 是否显示在页面上
        composeTestRule.onNodeWithText("午餐：").assertIsDisplayed()
    }
}
