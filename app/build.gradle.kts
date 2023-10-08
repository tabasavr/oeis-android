import org.jetbrains.kotlin.gradle.plugin.extraProperties

plugins {
    alias(libs.plugins.agp.app)
    alias(libs.plugins.kotlin)
    alias(libs.plugins.ksp)
    alias(libs.plugins.spotless)
    alias(libs.plugins.detekt)
}

android {
    namespace = "kozelko.me.oeisandroid"
    compileSdk = 33
    defaultConfig {
        applicationId = "kozelko.me.oeisandroid"
        minSdk = 21
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
        multiDexEnabled = true
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }
    buildFeatures {
        viewBinding = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }
}

// workaround to set kapt jvm target
// https://stackoverflow.com/questions/75109742/how-to-configure-kapt-to-generate-java17-java-stubs-in-android-gradle-build-file
tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    kotlinOptions.jvmTarget = JavaVersion.VERSION_11.toString()
}

dependencies {
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.cardview)
    implementation(libs.androidx.fragment)
    implementation(libs.androidx.lifecycle.livedata)
    implementation(libs.androidx.lifecycle.viewmodel)
    implementation(libs.androidx.paging)
    implementation(libs.androidx.recyclerview)
    implementation(libs.androidx.transition)
    implementation(libs.material)
    implementation(libs.moshi.core)
    implementation(libs.moshi.adapters)
    implementation(libs.retrofit.core)
    implementation(libs.retrofit.converterMoshi)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.runner)
    androidTestImplementation(libs.androidx.test.espresso)
    ksp(libs.moshi.ksp)
}

spotless {
    ratchetFrom = "origin/master"

    kotlin {
        target("src/*/java/**/*.kt")
        ktlint()
            .editorConfigOverride(mapOf("ktlint_experimental" to "enable"))
            .userData(mapOf("android" to "true"))
    }
}
