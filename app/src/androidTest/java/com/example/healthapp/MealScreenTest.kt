package com.example.healthapp

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MealScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun testAddMealFlow() {
        // 点击 Add Meal 按钮
        composeTestRule.onNodeWithText("Add a Meal").performClick()

        // 检查弹出输入框
        composeTestRule.onNodeWithText("Meal name").assertIsDisplayed()

        // 输入 Meal 名称
        composeTestRule.onNodeWithText("Meal name").performTextInput("午餐")

        // 点击确认
        composeTestRule.onNodeWithText("Confirm").performClick()

        // 检查 "午餐:" 是否显示在页面上
        composeTestRule.onNodeWithText("午餐:").assertIsDisplayed()
    }
}
