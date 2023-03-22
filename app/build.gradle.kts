import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp") version ("1.8.10-1.0.9")
    id("kotlin-kapt")
    id("com.google.gms.google-services")
    id("com.google.dagger.hilt.android")
    id("kotlin-parcelize")
}

android {
    namespace = "com.network.newsly"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.network.newsly"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables.useSupportLibrary = true
    }

    val apiKey = gradleLocalProperties(rootDir).getProperty("apiKey")

    buildTypes {
        getByName("debug") {
            buildConfigField("String", "NEWS_API_KEY", apiKey)
            buildConfigField("String", "NEWS_URL", "\"https://newsapi.org/v2/\"")
        }
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("String", "NEWS_API_KEY", apiKey)
            buildConfigField("String", "NEWS_URL", "\"https://newsapi.org/v2/\"")
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
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.2"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    ksp {
        arg("compose-destinations.generateNavGraphs", "false")
    }
    kapt {
        correctErrorTypes = true
    }
}

dependencies {

    val compose_version = "1.4.0-rc01"
    val compose_destination = "1.8.33-beta"
    val moshi = "1.14.0"
    val retrofit = "2.9.0"
    val okhttp = "5.0.0-alpha.3"
    val hilt_ = "2.44.2"

    implementation("com.google.accompanist:accompanist-systemuicontroller:0.29.1-alpha")

    implementation("io.github.raamcosta.compose-destinations:core:$compose_destination")
    implementation("com.google.android.gms:play-services-tasks:18.0.2")
    implementation("com.google.android.play:core-ktx:1.8.1")
    implementation("com.google.firebase:firebase-auth-ktx:21.1.0")
    ksp("io.github.raamcosta.compose-destinations:ksp:$compose_destination")

    implementation("androidx.paging:paging-compose:1.0.0-alpha18")

    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.6.0")

    implementation("com.squareup.okhttp3:okhttp:$okhttp")
    implementation("com.squareup.okhttp3:logging-interceptor:$okhttp")

    implementation("com.squareup.retrofit2:retrofit:$retrofit")
    implementation("com.squareup.retrofit2:converter-moshi:$retrofit")

    implementation("androidx.hilt:hilt-navigation-compose:1.1.0-alpha01")

    implementation("com.google.accompanist:accompanist-swiperefresh:0.23.1")

    implementation("io.coil-kt:coil-compose:2.2.2")

    implementation("com.google.accompanist:accompanist-placeholder-material:0.28.0")

    implementation("com.google.dagger:hilt-android:$hilt_")
    kapt("com.google.dagger:hilt-compiler:$hilt_")

    implementation("androidx.core:core-splashscreen:1.0.0")

    implementation("com.squareup.moshi:moshi-adapters:$moshi")
    ksp("com.squareup.moshi:moshi-kotlin-codegen:$moshi")

    implementation(platform("com.google.firebase:firebase-bom:31.2.3"))

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.0")
    implementation("androidx.activity:activity-compose:1.6.1")
    implementation("androidx.compose.ui:ui:$compose_version")
    implementation("androidx.compose.ui:ui-tooling-preview:$compose_version")
    implementation("androidx.compose.material3:material3:1.1.0-alpha08")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:$compose_version")
    debugImplementation("androidx.compose.ui:ui-tooling:$compose_version")
    debugImplementation("androidx.compose.ui:ui-test-manifest:$compose_version")
}