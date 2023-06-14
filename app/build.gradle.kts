plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    id("com.diffplug.spotless")
    id("io.gitlab.arturbosch.detekt")
}

android {
    namespace = "kozelko.me.oeisandroid"
    compileSdk = 33
    defaultConfig {
        applicationId = "kozelko.me.oeisandroid"
        minSdk = 19
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
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
    val moshiVersion = "1.15.0"
    val pagingVersion = "3.1.1"
    val appcompat = "1.6.1"
    val lifecycle = "2.6.1"
    val fragment_version = "1.6.0"
    val retrofit_version = "2.9.0"
    val material_version = "1.7.0"
    val constraint_version = "2.1.4"

    implementation("com.google.android.material:material:$material_version")
    implementation("androidx.appcompat:appcompat:$appcompat")
    implementation("androidx.fragment:fragment-ktx:$fragment_version")
    implementation("androidx.constraintlayout:constraintlayout:$constraint_version")
    implementation("androidx.recyclerview:recyclerview:1.2.1")
    implementation("androidx.cardview:cardview:1.0.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle")
    implementation("androidx.paging:paging-runtime-ktx:$pagingVersion")
    implementation("androidx.transition:transition:1.4.1")
    implementation("com.squareup.retrofit2:retrofit:$retrofit_version")
    implementation("com.squareup.retrofit2:converter-moshi:$retrofit_version")
    implementation("com.squareup.moshi:moshi:$moshiVersion")
    implementation("com.squareup.moshi:moshi-adapters:$moshiVersion")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test:runner:1.5.2")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    ksp("com.squareup.moshi:moshi-kotlin-codegen:$moshiVersion")
}

spotless {
    ratchetFrom = "origin/master"

    kotlin {
        target("src/*/java/**/*.kt")
        ktlint()
                .setUseExperimental(true)
                .userData(mapOf("android" to "true"))
    }
}
