// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    alias(libs.plugins.compose.compiler) apply false

    // KOTLIN SYMBOL PROCESSING
    alias(libs.plugins.kotlin.ksp) apply false

    // DAGGER HILT
    alias(libs.plugins.dagger.hilt) apply false
}