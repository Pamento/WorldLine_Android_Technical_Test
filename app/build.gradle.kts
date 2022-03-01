import java.io.File
import java.io.FileInputStream
import java.util.*

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("io.gitlab.arturbosch.detekt")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}
//https://stackoverflow.com/questions/60474010/read-value-from-local-properties-via-kotlin-dsl not work
//val localProperties: String = gradleLocalProperties(rootDir).getProperty("API_KEY")
val localProperties = Properties().apply {
    load(FileInputStream(File(rootProject.rootDir, "local.properties")))
}

android {
    compileSdkVersion(Versions.COMPILE_SDK)

    defaultConfig {
        applicationId = Versions.ID
        minSdkVersion(Versions.MIN_SDK)
        targetSdkVersion(Versions.TARGET_SDK)
        versionCode = Versions.CODE
        versionName = Versions.NAME

        testInstrumentationRunner = Versions.TEST_INSTRUMENTATION_RUNNER

        proguardFiles(
            getDefaultProguardFile("proguard-android-optimize.txt"),
            "/proguard-rules.pro"
        )

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_1_8
            targetCompatibility = JavaVersion.VERSION_1_8
        }
    }

    buildFeatures.viewBinding = true

    kotlinOptions {
        jvmTarget = "1.8"
    }
    namespace = "com.pawel.worldline_android_technical_test"

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
        getByName("debug") {
            buildConfigField("String", "API_KEY", "\"${localProperties["API_KEY"]}\"")
            //resValue("string", "API_KEY", "${localProperties["API_KEY"]}")
        }
    }

    flavorDimensions.add("env")
    productFlavors {
        create("prod") {
            dimension = "env"
            applicationIdSuffix = ".prod"
        }
        create("mock") {
            dimension = "env"
            applicationIdSuffix = ".mock"
        }
    }
}

val mockImplementation by configurations

dependencies {
    implementation(project(":data"))
    implementation(project(":domain"))
    implementation(project(":presentation"))
    implementation(project(":common"))
    mockImplementation(project(":data-mock"))

    implementation(Dependencies.ANDROIDX_CORE_KTX)
    implementation(Dependencies.ANDROIDX_APPCOMPAT)
    implementation(Dependencies.ANDROIDX_FRAGMENT)
    implementation(Dependencies.ANDROID_MATERIAL)
    implementation(Dependencies.ANDROIDX_CONSTRAIN_LAYOUT)
    implementation(Dependencies.ANDROIDX_LIVEDATA)
    implementation(Dependencies.ANDROIDX_VIEW_MODEL)
    implementation(Dependencies.KOTLIN_STDLIB)
    implementation(Dependencies.ANDROIDX_LIFECYCLE_RUNTIME)
    implementation(Dependencies.ANDROIDX_RECYCLERVIEW)
    implementation(Dependencies.ANDROIDX_LIFECYCLE_EXT)

    // DI Hilt
    implementation(Dependencies.HILT)
    kapt(Dependencies.HILT_KAPT)

    // Glide dependencies for GlideEntryPoint -> FilmAppGlideModule
    implementation(Dependencies.GLIDE)
    kapt(Dependencies.GLIDE_KAPT)

    // Test
    testImplementation(Dependencies.JUNIT4)
    androidTestImplementation(Dependencies.TEST_EXT)
    androidTestImplementation(Dependencies.ESPRESSO)
}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}