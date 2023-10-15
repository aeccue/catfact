@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.serialization)

    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.android.hilt)
}

android {
    namespace = "jp.speakbuddy.catfact"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "jp.speakbuddy.catfact"
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
        versionCode = 1
        versionName = "0.0.1"

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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        compileOptions {
            allWarningsAsErrors = true
            jvmTarget = JavaVersion.VERSION_11.toString()
        }
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.composeCompiler.get()
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(":ui"))

    implementation(libs.kotlin.stdlib)

    implementation(libs.android.hilt)
    kapt(libs.android.hilt.compiler)

    implementation(libs.androidx.activity.compose)
}

kapt {
    correctErrorTypes = true
}
