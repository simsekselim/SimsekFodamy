/**
 * Core Libraries
 */
object CoreLibraries {
   const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlinVersion}"
}

/**
 * Support Libraries
 */
object ArcComponentsLibs {
   const val appCompat = "androidx.appcompat:appcompat:${Versions.xVersion}"
   const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayoutVersion}"
   const val fragmentKtx = "androidx.fragment:fragment-ktx:${Versions.fragmentVersion}"

}

/**
 * Test Libraries
 */
object TestLibraries {
   const val jUnit = "junit:junit:${Versions.jUnitVersion}"
   const val androidTestImplementation = "androidx.test.ext:junit:${Versions.testImplementationVersion}"
   const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espressoCoreVersion}"
   const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
   const val legacy = "androidx.legacy:legacy-support-v4:${Versions.legacy}"
}

/**
 * Common Libraries
 */
object Libraries {
   const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofitVersion}"
   const val logInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttpLoggingInterceptorVersion}"
   const val gsonCon = "com.squareup.retrofit2:converter-gson:${Versions.gsonVersion}"
   const val gson = "com.google.code.gson:gson:${Versions.gson}"
   const val okHttp = "com.squareup.okhttp3:okhttp:${Versions.okHttpVersion}"
   const val navFragment = "androidx.navigation:navigation-fragment-ktx:${Versions.navVersion}"
   const val navUi = "androidx.navigation:navigation-ui-ktx:${Versions.navVersion}"
   const val picasso = "com.squareup.picasso:picasso:${Versions.picassoVersion}"
   const val circularImageView = "de.hdodenhof:circleimageview:${Versions.circularImageViewVersion}"
   const val materialDesign = "com.google.android.material:material:${Versions.supportDesignVersion}"
   const val paging = "androidx.paging:paging-runtime:${Versions.pagingVersion}"
   const val daggerHilt = "com.google.dagger:hilt-android:${Versions.daggerHiltVersion}"
   const val daggerAnnotations = "com.google.dagger:hilt-compiler:${Versions.daggerHiltVersion}"
   const val lottie = "com.airbnb.android:lottie:${Versions.lottieVersion}"
   const val viewPager = "androidx.viewpager2:viewpager2:${Versions.pagerVersion}"
   const val indicator = "com.tbuonomo:dotsindicator:${Versions.indicatorVersion}"
   const val dataStore = "androidx.datastore:datastore-preferences:${Versions.dataStoreVersion}"
   const val firebaseBom = "com.google.firebase:firebase-bom:${Versions.firebaseBom}"
   const val firebaseKtx = "com.google.firebase:firebase-analytics-ktx"
   const val firebaseCrash = "com.google.firebase:firebase-crashlytics-ktx"
   const val room = "androidx.room:room-runtime:${Versions.room_version}"
   const val roomExtension = "androidx.room:room-ktx:${Versions.room_version}"
   const val roomAnnotation = "androidx.room:room-compiler:${Versions.room_version}"
   const val roomPaging = "androidx.room:room-paging:${Versions.room_version}"



}