import java.io.File
import java.io.IOException
import java.util.concurrent.TimeUnit

object Versions {

    const val TIMEOUT_MINUTES = 60L

    const val COMPILE_SDK = 31
    const val MIN_SDK = 21
    const val TARGET_SDK = 31
    const val DETEKT = "1.17.1"
    const val CODE = 1
    const val NAME = "1.0"
    const val ID = "com.pawel.worldline_android_technical_test"
    const val TEST_INSTRUMENTATION_RUNNER = "androidx.test.runner.AndroidJUnitRunner"

    // Dependencies versions:
    const val ANDROIDX_KTX = "1.7.0"
    const val ANDROIDX_APPCOMPAT = "1.4.0"
    const val ANDROIDX_CONSTRAIN_LAYOUT = "2.1.2"
    const val ANDROIDX_LIVEDATA = "2.4.0"
    const val ANDROIDX_VIEW_MODEL = "2.4.0"
    const val ANDROIDX_LIFECYCLE_RUNTIME = "2.4.0"
    const val ANDROIDX_RECYCLERVIEW = "1.2.1"
    const val ANDROIDX_CORE = "1.7.0"
    const val ANDROIDX_FRAGMENT = "1.4.1"
    const val ANDROIDX_LIFECYCLE_EXT = "2.2.0"

    const val ANDROID_MATERIAL = "1.4.0"
    const val ANDROID_LIFECYCLE_EXT = "1.1.1"
    const val KOTLIN_STDLIB = "1.5.30"

    const val GLIDE = "4.13.0"
    // kapt
    const val GLIDE_COMPILER = "4.13.0"

    const val RETROFIT2 = "2.9.0"
    const val RETROFIT2_MOSHI = "2.6.2"
    const val RETROFIT2_MOSHI_KOTLIN = "1.12.0"
    const val OKHTTP3_BOM = "4.9.0"
    const val RX_KOTLIN = "3.0.1"

    const val COROUTINE_ANDROID = "1.5.2"
    const val COROUTINE_ANDROID_CORE = "1.5.2"
    const val COROUTINE_LEGACY_SUPPORT = "1.0.0"

    const val HILT = "2.40"

    //    testImplementation
    const val JUNIT4 = "4.13.2"
    //    androidTestImplementation
    const val TEST_EXT = "1.1.3"
    //    androidTestImplementation
    const val ESPRESSO = "3.4.0"
}

object DependencyType {
    const val IMPLEMENTATION = "implementation"
    const val KAPT = "kapt"
    const val TEST_IMPLEMENTATION = "testImplementation"
    const val ANDROID_TEST_IMPLEMENTATION  = "androidTestImplementation"
}

object Plugins {
    const val HILT = "com.google.dagger:hilt-android-gradle-plugin:2.40"
    const val DETEKT = "io.gitlab.arturbosch.detekt:detekt-gradle-plugin:1.17.1"
}

object Dependencies {
    const val ANDROIDX_KTX = "androidx.core:core-ktx:${Versions.ANDROIDX_KTX}"
    const val ANDROIDX_APPCOMPAT = "androidx.appcompat:appcompat:${Versions.ANDROIDX_APPCOMPAT}"
    const val ANDROIDX_CONSTRAIN_LAYOUT = "androidx.constraintlayout:constraintlayout:${Versions.ANDROIDX_CONSTRAIN_LAYOUT}"
    const val ANDROIDX_LIVEDATA = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.ANDROIDX_LIVEDATA}"
    const val ANDROIDX_VIEW_MODEL = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.ANDROIDX_VIEW_MODEL}"
    const val ANDROIDX_LIFECYCLE_RUNTIME = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.ANDROIDX_LIFECYCLE_RUNTIME}"
    const val ANDROIDX_RECYCLERVIEW = "androidx.recyclerview:recyclerview:${Versions.ANDROIDX_RECYCLERVIEW}"
    const val ANDROIDX_CORE = "androidx.core:core-ktx:${Versions.ANDROIDX_CORE}"
    const val ANDROIDX_FRAGMENT = "androidx.fragment:fragment-ktx:${Versions.ANDROIDX_FRAGMENT}"
    const val ANDROIDX_LIFECYCLE_EXT = "androidx.lifecycle:lifecycle-extensions:${Versions.ANDROIDX_LIFECYCLE_EXT}"

    const val ANDROID_MATERIAL = "com.google.android.material:material:${Versions.ANDROID_MATERIAL}"
    const val ANDROID_LIFECYCLE_EXT = "android.arch.lifecycle:extensions:${Versions.ANDROID_LIFECYCLE_EXT}"
    const val KOTLIN_STDLIB = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.KOTLIN_STDLIB}"

    const val GLIDE = "com.github.bumptech.glide:glide:${Versions.GLIDE}"
    const val GLIDE_KAPT = "com.github.bumptech.glide:compiler:${Versions.GLIDE_COMPILER}"

    const val RETROFIT2 = "com.squareup.retrofit2:retrofit:${Versions.RETROFIT2}"
    const val RETROFIT2_MOSHI = "com.squareup.retrofit2:converter-moshi:${Versions.RETROFIT2_MOSHI}"
    const val RETROFIT2_MOSHI_KOTLIN = "com.squareup.moshi:moshi-kotlin:${Versions.RETROFIT2_MOSHI_KOTLIN}"
    const val OKHTTP3_INTERCEPTOR = "com.squareup.okhttp3:logging-interceptor"
    const val OKHTTP3_BOM = "com.squareup.okhttp3:okhttp-bom:${Versions.OKHTTP3_BOM}"
    const val RX_KOTLIN = "io.reactivex.rxjava3:rxkotlin:${Versions.RX_KOTLIN}"

    const val COROUTINE_ANDROID = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.COROUTINE_ANDROID}"
    const val COROUTINE_ANDROID_CORE = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.COROUTINE_ANDROID_CORE}"
    const val COROUTINE_LEGACY_SUPPORT = "androidx.legacy:legacy-support-v4:${Versions.COROUTINE_LEGACY_SUPPORT}"

    const val HILT = "com.google.dagger:hilt-android:${Versions.HILT}"
    const val HILT_KAPT = "com.google.dagger:hilt-android-compiler:${Versions.HILT}"

    //    testImplementation
    const val JUNIT4 = "junit:junit:${Versions.JUNIT4}"
    //    androidTestImplementation
    const val TEST_EXT = "androidx.test.ext:junit:${Versions.TEST_EXT}"
    //    androidTestImplementation
    const val ESPRESSO = "androidx.test.espresso:espresso-core:${Versions.ESPRESSO}"
}

fun String.execute(workingDir: File = File("./")): String? =
    try {
        val parts = this.split("\\s".toRegex())
        val proc = ProcessBuilder(parts)
            .directory(workingDir)
            .redirectOutput(ProcessBuilder.Redirect.PIPE)
            .redirectError(ProcessBuilder.Redirect.PIPE)
            .start()

        proc.waitFor(Versions.TIMEOUT_MINUTES, TimeUnit.MINUTES)
        proc.inputStream.bufferedReader().readText()
    } catch (e: IOException) {
        e.printStackTrace()
        null
    }