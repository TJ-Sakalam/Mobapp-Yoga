plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.google.gms.google.services)
}

android {
    namespace = "com.example.yogaapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.yogaapp"
        minSdk = 21  // Changed from 35 to support your Infinix X695 (API 30)
        targetSdk = 35  // Keep target as 35 for latest features
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }


}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.firebase.messaging)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    implementation("pl.droidsonroids.gif:android-gif-drawable:1.2.19")
    implementation("com.google.android.material:material:1.12.0")
    implementation ("com.google.firebase:firebase-core:21.1.1")
    implementation ("com.google.firebase:firebase-messaging:23.2.1")








}
