import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("io.gitlab.arturbosch.detekt")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}
//https://stackoverflow.com/questions/60474010/read-value-from-local-properties-via-kotlin-dsl
val localProperties: String = gradleLocalProperties(rootDir).getProperty("API_KEY")


//def localProperties = new Properties()
//localProperties.load(new FileInputStream(rootProject.file("local.properties")))

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

    buildTypes {
//        debug {
//            buildConfigField("String", "API_KEY", "\"" + localProperties['API_KEY'] + "\"")
//        }
        getByName("release") {
            isMinifyEnabled = false
        }
        getByName("debug") {
            buildConfigField("String", "API_KEY", localProperties)
        }
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
//    buildFeatures {
//        viewBinding true
//    }
    namespace = "com.pawel.worldline_android_technical_test"
}


dependencies {
    implementation(project(":domain"))
    implementation(project(":common"))
    implementation(project(":data"))
    implementation(project(":presentation"))

    implementation(Dependencies.ANDROIDX_KTX)
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

    // Glide
    implementation(Dependencies.GLIDE)
    kapt(Dependencies.GLIDE_KAPT)

    // Retrofit
    implementation(Dependencies.RETROFIT2)
    implementation(Dependencies.RETROFIT2_MOSHI)
    implementation(Dependencies.RETROFIT2_MOSHI_KOTLIN)
    implementation(platform(Dependencies.OKHTTP3_BOM))
    implementation(Dependencies.OKHTTP3_INTERCEPTOR)
    implementation(Dependencies.RX_KOTLIN)

    // Coroutine
    implementation(Dependencies.COROUTINE_ANDROID)
    implementation(Dependencies.COROUTINE_ANDROID_CORE)
    implementation(Dependencies.COROUTINE_LEGACY_SUPPORT)

    // DI Hilt
    implementation(Dependencies.HILT)
    kapt(Dependencies.HILT_KAPT)

    // Test
    testImplementation(Dependencies.JUNIT4)
    androidTestImplementation(Dependencies.TEST_EXT)
    androidTestImplementation(Dependencies.ESPRESSO)
}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}