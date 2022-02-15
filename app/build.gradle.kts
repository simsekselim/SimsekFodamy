plugins {
    id(Plugins.androidApplication)
    id(Plugins.kotlinAndroid)
    id(Plugins.kotlinKapt)
    id(Plugins.hiltPlugin)
    id(Plugins.navSafeArgsPlugin)
    id(Plugins.parcelizePlugin)
    id(Plugins.ktlintPlugin)
    id(Plugins.googleService)
    id(Plugins.firebasePlugin)
}

android {
    compileSdk = Config.compileSdkVersion

    defaultConfig {
        applicationId = Config.applicationId
        minSdk = Config.minSdkVersion
        targetSdk = Config.targetSdkVersion
        versionCode = Config.versionCode
        versionName = Config.versionName
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
    packagingOptions {
        resources.excludes.add("META-INF/**/*")
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
    // Architecture Components
    implementation(ArcComponentsLibs.appCompat)
    implementation(ArcComponentsLibs.constraintLayout)
    implementation(ArcComponentsLibs.fragmentKtx)

    // Material Design
    implementation(Libraries.materialDesign)

    // Firebase
    implementation(platform(Libraries.firebaseBom))
    implementation(Libraries.firebaseKtx)
    implementation(Libraries.firebaseCrash)

    // Testing
    testImplementation(TestLibraries.jUnit)
    androidTestImplementation(TestLibraries.androidTestImplementation)
    androidTestImplementation(TestLibraries.espressoCore)
    androidTestImplementation(TestLibraries.coreKtx)
    androidTestImplementation(TestLibraries.legacy)

    // Navigation
    implementation(Libraries.navFragment)
    implementation(Libraries.navUi)

    // ViewPager2
    implementation(Libraries.viewPager)

    // Indicator
    implementation(Libraries.indicator)

    // Lottie
    implementation(Libraries.lottie)

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

    // Circle ImageView
    implementation(Libraries.circularImageView)

    // Paging 3
    implementation(Libraries.paging)

    // Picasso
    implementation(Libraries.picasso)

    // DataStore
    implementation(Libraries.dataStore)

    // Module
    implementation(project(Modules.data))
    implementation(project(Modules.domain))
}
