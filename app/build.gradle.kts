plugins {
    alias(libs.plugins.androidApplication)
}

android {
    namespace = "com.example.expiry_tracker"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.expiry_tracker"
        minSdk = 24
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(files("lib\\mysql-connector-j-8.0.31.jar"))
    implementation(files("lib\\hamcrest-core-1.3.jar"))
    implementation(files("lib\\protobuf-java-3.19.4.jar"))
    implementation(files("lib\\commons-lang3-3.12.0.jar"))
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}