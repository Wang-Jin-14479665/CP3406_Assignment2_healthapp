// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.hilt) apply false
}

//plugins {
//    id("com.android.application") version "8.1.1" apply false
//    id("org.jetbrains.kotlin.android") version "1.9.20" apply false
//    id("com.google.dagger.hilt.android") version "2.48" apply false
//}