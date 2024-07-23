import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    kotlin("kapt")
    kotlin("plugin.serialization") version "1.8.21"
}

android {
    namespace = "com.mary.alcyoneplus"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.mary.alcyoneplus"
        minSdk = 29
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"


        val localProperties = Properties()
        localProperties.load(project.rootProject.file("local.properties").inputStream())

        buildConfigField("String", "API_KEY", localProperties.getProperty("apiKey"))
        buildConfigField("String", "URL", localProperties.getProperty("baseUrl"))


        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        buildConfig = true
        compose = true


    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}



dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.navigation.compose)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    implementation ("androidx.navigation:navigation-compose:2.7.7")

    implementation("com.google.dagger:hilt-android:2.48")
    debugImplementation("androidx.compose.ui:ui-tooling:1.6.8")
    kapt("com.google.dagger:hilt-android-compiler:2.48")

    implementation("io.github.jan-tennert.supabase:postgrest-kt:2.5.2")
    implementation("io.ktor:ktor-client-cio:2.3.12")
    implementation("androidx.multidex:multidex:2.0.1")
    implementation("io.github.jan-tennert.supabase:gotrue-kt:2.5.2")
    implementation(platform("io.github.jan-tennert.supabase:bom:2.5.2"))
    implementation("io.ktor:ktor-client-android:2.3.12")




    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.3")
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")


    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.3")

    //доп имплы для внедрения авторизации
//    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0")
//    implementation ("androidx.activity:activity-compose:1.8.2")
//    implementation("io.github.jan-tennert.supabase:gotrue-kt:2.2.3")
//    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.7.0")
}

