package com.example.healthapp

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DashboardScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun dashboard_shouldShowBasicInfo() {
        // 检查 Step Count 区域存在
        composeTestRule.onNodeWithText("Step Count").assertIsDisplayed()

        // 检查 Hart Rate 区域存在
        composeTestRule.onNodeWithText("Hart Rate").assertIsDisplayed()

        // 检查 Latest diet record 区域存在
        composeTestRule.onNodeWithText("Latest diet record").assertIsDisplayed()

        // 检查 Latest Exercise record 区域存在
        composeTestRule.onNodeWithText("Latest Exercise record").assertIsDisplayed()
    }
}
