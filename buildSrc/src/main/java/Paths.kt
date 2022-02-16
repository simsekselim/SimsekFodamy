/**
 * Common Paths
 */
object Paths {
    const val gradleClasspath = "com.android.tools.build:gradle:${Versions.gradleVersion}"
    const val kotlinGradlePluginClasspath = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlinVersion}"
    const val daggerHiltPluginPath = "com.google.dagger:hilt-android-gradle-plugin:${Versions.daggerHiltVersion}"
    const val navigationPluginPath = "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navVersion}"
    const val ktlintPluginPath = "org.jlleitschuh.gradle:ktlint-gradle:${Versions.ktlint}"
    const val fireBaseCrashAnalytics = "com.google.firebase:firebase-crashlytics-gradle:${Versions.fireBaseCrashAnalytics}"
    const val googleServicePath = "com.google.gms:google-services:${Versions.googleService}"

}