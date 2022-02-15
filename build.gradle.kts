// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(Paths.gradleClasspath)
        classpath(Paths.kotlinGradlePluginClasspath)
        classpath(Paths.daggerHiltPluginPath)
        classpath(Paths.navigationPluginPath)
        classpath(Paths.ktlintPluginPath)
        classpath(Paths.fireBaseCrashAnalytics)
        classpath(Paths.googleServicePath)
    }
}

plugins {
    id(Plugins.ktlintPlugin) version Versions.ktlint
}

tasks.register<Delete>("clean") {
    delete(rootProject.buildDir)
}
