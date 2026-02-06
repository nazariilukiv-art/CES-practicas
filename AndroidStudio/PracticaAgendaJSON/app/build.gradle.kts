plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.example.practicaagendajson"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.practicaagendajson"
        minSdk = 26
        targetSdk = 36
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
    kotlinOptions {
        jvmTarget = "11"
    }
    viewBinding{
        enable = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    // AÑADE ESTAS TRES AL FINAL DEL BLOQUE DEPENDENCIES:

    // 1. Glide (Para las fotos)
    implementation("com.github.bumptech.glide:glide:4.16.0")

    // 2. Gson (Para entender el idioma JSON)
    implementation("com.google.code.gson:gson:2.10.1")

    // 3. Volley (Para conectarnos a internet)
    implementation("com.android.volley:volley:1.2.1")
}