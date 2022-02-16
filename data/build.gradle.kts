plugins {
    id(Plugins.androidLibrary)
    id(Plugins.kotlinAndroid)
    id(Plugins.kotlinKapt)
    id(Plugins.ktlintPlugin)
}

android {
    compileSdk = Config.compileSdkVersion

    defaultConfig {
        minSdk = Config.minSdkVersion
        targetSdk = Config.targetSdkVersion
    }
    buildFeatures {
        dataBinding = true
        viewBinding = true
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }
}

dependencies {
    implementation(CoreLibraries.kotlin)
    implementation(Libraries.daggerHilt)
    kapt(Libraries.daggerAnnotations)
    // Common
    implementation(TestLibraries.coreKtx)
    implementation(ArcComponentsLibs.appCompat)

    // Test
    implementation(Libraries.materialDesign)
    testImplementation(TestLibraries.jUnit)
    androidTestImplementation(TestLibraries.androidTestImplementation)

    // Retrofit
    implementation(Libraries.retrofit)

    // Gson
    implementation(Libraries.gson)
    implementation(Libraries.gsonCon)

    // OkHttp
    implementation(Libraries.okHttp)
    implementation(Libraries.logInterceptor)

    // Dagger Hilt
    implementation(Libraries.daggerHilt)
    implementation(Libraries.daggerAnnotations)

    // DataStore
    implementation(Libraries.dataStore)

    // Paging
    implementation(Libraries.paging)
    implementation(project(Modules.domain))
}
