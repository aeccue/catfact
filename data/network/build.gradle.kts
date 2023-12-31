@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)

    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.android.hilt)
}

android {
    namespace = "jp.speakbuddy.catfact.network"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("String", "CAT_FACT_ENDPOINT", "\"https://catfact.ninja\"")
        }

        debug {
            // can swap this with debug or staging endpoint
            buildConfigField("String", "CAT_FACT_ENDPOINT", "\"https://catfact.ninja\"")
        }
    }

    buildFeatures {
        buildConfig = true
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
}

dependencies {
    implementation(libs.kotlin.stdlib)

    implementation(libs.android.hilt)
    kapt(libs.android.hilt.compiler)

    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.content.negotiation)
    implementation(libs.ktor.client.cio)
    implementation(libs.ktor.serialization.json)
}

kapt {
    correctErrorTypes = true
}
