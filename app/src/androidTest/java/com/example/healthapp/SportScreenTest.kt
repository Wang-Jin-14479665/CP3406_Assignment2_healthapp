package com.example.healthapp

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SportScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun testAddSportFlow() {
        // 点击 Add an exercise 按钮 Click the Add an exercise button
        composeTestRule.onNodeWithText("Add an exercise").performClick()

        // 检查弹出输入框 Check the input box that is displayed
        composeTestRule.onNodeWithText("sport event").assertIsDisplayed()

        // 输入运动项目名称 Enter the sport name
        composeTestRule.onNodeWithText("sport event").performTextInput("跑步")

        // 输入心率 Input heart rate
        composeTestRule.onNodeWithText("average heart rate\n").performTextInput("120")

        // 输入消耗热量 Input heat consumption
        composeTestRule.onNodeWithText("heat consumption").performTextInput("200")

        // 点击确认 click confirm
        composeTestRule.onNodeWithText("Confirm").performClick()

        // 检查是否展示 Check for display
        composeTestRule.onNodeWithText("sport event：跑步").assertIsDisplayed()
    }
}
