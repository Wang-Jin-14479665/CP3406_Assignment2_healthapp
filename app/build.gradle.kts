plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.example.healthapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.healthapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.11" // It is recommended to match the Compose version
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation("androidx.activity:activity-compose:1.8.2")

    // ✅ Compose core
    implementation("androidx.compose.ui:ui:1.6.5")
    implementation("androidx.compose.material3:material3:1.2.0")
    implementation("androidx.compose.foundation:foundation:1.6.5")
    implementation("androidx.compose.foundation:foundation-layout:1.6.5")
    implementation("androidx.compose.ui:ui-tooling-preview:1.6.5")
    implementation(libs.androidx.monitor)
    implementation(libs.androidx.junit.ktx)
    implementation(libs.androidx.ui.test.junit4.android)
    implementation(libs.play.services.basement)
    testImplementation(libs.junit.junit)
    androidTestImplementation(libs.junit.junit)
    debugImplementation("androidx.compose.ui:ui-tooling:1.6.5")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0")

    // ✅ Room
    implementation("androidx.room:room-runtime:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")
    kapt("androidx.room:room-compiler:2.6.1")

    // Material Design
    implementation("com.google.android.material:material:1.12.0")

    // Unit test library
    testImplementation("org.mockito:mockito-core:4.0.0")
    testImplementation("org.mockito.kotlin:mockito-kotlin:4.0.0")

    // Bottom navigation bar
    implementation("androidx.navigation:navigation-compose:2.7.1")

    implementation("androidx.compose.material:material-icons-extended")

    // Retrofit 核心库
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    // Gson 转换器（将 Json 转 Kotlin 对象）
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    // OkHttp 日志拦截（可选，用于调试）
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.3")

    // Hilt DI
    implementation ("com.google.dagger:hilt-android:2.48")
    kapt ("com.google.dagger:hilt-compiler:2.48")
}
