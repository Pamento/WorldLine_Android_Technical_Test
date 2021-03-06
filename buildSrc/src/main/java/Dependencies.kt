import java.io.File
import java.io.IOException
import java.util.concurrent.TimeUnit

object Versions {

    const val TIMEOUT_MINUTES = 60L

    const val COMPILE_SDK = 31
    const val MIN_SDK = 21
    const val TARGET_SDK = 31
    const val DETEKT = "1.19.0"
    const val CODE = 1
    const val NAME = "1.0"
    const val ID = "com.pawel.worldline_android_technical_test"
    const val TEST_INSTRUMENTATION_RUNNER = "com.pawel.worldline_android_technical_test.CustomTestRunner"
    //const val TEST_INSTRUMENTATION_RUNNER = "androidx.test.runner.AndroidJUnitRunner"

    // Dependencies versions:
    const val ANDROIDX_CORE_KTX = "1.7.0"
    const val ANDROIDX_APPCOMPAT = "1.4.0"
    const val ANDROIDX_CONSTRAIN_LAYOUT = "2.1.2"
    const val ANDROIDX_LIVEDATA = "2.4.0"
    const val ANDROIDX_VIEW_MODEL = "2.4.0"
    const val ANDROIDX_LIFECYCLE_RUNTIME = "2.4.0"
    const val ANDROIDX_RECYCLERVIEW = "1.2.1"
    const val ANDROIDX_FRAGMENT = "1.4.1"
    const val ANDROIDX_LIFECYCLE_EXT = "2.2.0"
    const val LIFECYCLE = "2.4.1"

    const val ANDROID_MATERIAL = "1.4.0"
    const val KOTLIN_STDLIB = "1.5.30"

    const val GLIDE = "4.13.0"
    // kapt
    const val GLIDE_COMPILER = "4.13.0"

    const val GSON = "2.8.6"
    const val RETROFIT2 = "2.9.0"
    const val OKHTTP = "4.10.0-RC1"
    const val OKHTTP3_BOM = "4.9.0"
    const val RX_KOTLIN = "3.0.1"

    const val COROUTINE_ANDROID = "1.5.2"
    const val COROUTINE_ANDROID_CORE = "1.5.2"
    const val COROUTINE_LEGACY_SUPPORT = "1.0.0"

    const val HILT = "2.40"
    const val HILT_TESTING = "2.38.1"

    //    testImplementation
    const val JUNIT4 = "4.13.2"
    //    androidTestImplementation
    const val TEST_EXT = "1.1.3"
    //    androidTestImplementation
    const val ESPRESSO = "3.3.0"
    const val ESPRESSO_CONTRIB = "3.3.0"
    const val ESPRESSO_IDLE = "3.1.1"
    const val ANDROIDX_TEST_CORE = "1.4.0"
    const val ANDROIDX_TEST_CORE_KTX = "1.4.0"
    const val ANDROIDX_TESTING_CORE = "2.1.0"
    const val ANDROIDX_TESTING_FRAGMENT = "1.4.1"
    const val HAMCREST = "1.3"
    const val ROBOLECTRIC = "4.5.1"
    const val KOTLINX_TEST_COROUTINES = "1.5.1"
}

object DependencyType {
    const val IMPLEMENTATION = "implementation"
    const val KAPT = "kapt"
    const val KAPT_ANDROID_TEST = "kaptAndroidTest"
    const val TEST_IMPLEMENTATION = "testImplementation"
    const val ANDROID_TEST_IMPLEMENTATION  = "androidTestImplementation"
}

object Plugins {
    const val HILT = "com.google.dagger:hilt-android-gradle-plugin:${Versions.HILT}"
    const val DETEKT = "io.gitlab.arturbosch.detekt:detekt-gradle-plugin:${Versions.DETEKT}"
    const val DETEKT_KTLINT = "io.gitlab.arturbosch.detekt:detekt-formatting:${Versions.DETEKT}"
}

object Dependencies {
    const val ANDROIDX_CORE_KTX = "androidx.core:core-ktx:${Versions.ANDROIDX_CORE_KTX}"
    const val ANDROIDX_APPCOMPAT = "androidx.appcompat:appcompat:${Versions.ANDROIDX_APPCOMPAT}"
    const val ANDROIDX_CONSTRAIN_LAYOUT = "androidx.constraintlayout:constraintlayout:${Versions.ANDROIDX_CONSTRAIN_LAYOUT}"
    const val ANDROIDX_LIVEDATA = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.ANDROIDX_LIVEDATA}"
    const val ANDROIDX_VIEW_MODEL = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.ANDROIDX_VIEW_MODEL}"
    const val ANDROIDX_LIFECYCLE_RUNTIME = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.ANDROIDX_LIFECYCLE_RUNTIME}"
    const val ANDROIDX_RECYCLERVIEW = "androidx.recyclerview:recyclerview:${Versions.ANDROIDX_RECYCLERVIEW}"
    const val ANDROIDX_FRAGMENT = "androidx.fragment:fragment-ktx:${Versions.ANDROIDX_FRAGMENT}"
    //const val ANDROIDX_FRAGMENT_TESTING = "androidx.fragment:fragment-testing:${Versions.ANDROIDX_FRAGMENT}"
    const val ANDROIDX_LIFECYCLE_EXT = "androidx.lifecycle:lifecycle-extensions:${Versions.ANDROIDX_LIFECYCLE_EXT}"
    const val ANDROIDX_KAPT = "androidx.lifecycle:lifecycle-compiler:${Versions.LIFECYCLE}"

    const val ANDROID_MATERIAL = "com.google.android.material:material:${Versions.ANDROID_MATERIAL}"
    const val KOTLIN_STDLIB = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.KOTLIN_STDLIB}"

    const val GLIDE = "com.github.bumptech.glide:glide:${Versions.GLIDE}"
    const val GLIDE_KAPT = "com.github.bumptech.glide:compiler:${Versions.GLIDE_COMPILER}"


    // NETWORK ----------------------------------------------------------------------------------------
    const val COROUTINE_ANDROID_CORE = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.COROUTINE_ANDROID_CORE}"
    const val GSON = "com.google.code.gson:gson:${Versions.GSON}"
    const val RETROFIT2 = "com.squareup.retrofit2:retrofit:${Versions.RETROFIT2}"
    const val RETROFIT2_GSON = "com.squareup.retrofit2:converter-gson:${Versions.RETROFIT2}"
    const val OKHTTP3_INTERCEPTOR = "com.squareup.okhttp3:logging-interceptor"
    const val OKHTTP3_BOM = "com.squareup.okhttp3:okhttp-bom:${Versions.OKHTTP3_BOM}"
    const val RX_KOTLIN = "io.reactivex.rxjava3:rxkotlin:${Versions.RX_KOTLIN}"
    const val COROUTINE_ANDROID = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.COROUTINE_ANDROID}"

    const val COROUTINE_LEGACY_SUPPORT = "androidx.legacy:legacy-support-v4:${Versions.COROUTINE_LEGACY_SUPPORT}"

    // TESTS ----------------------------------------------------------------------------------------
    const val MOCKWS = "com.squareup.okhttp3:mockwebserver:${Versions.OKHTTP}"

    const val HILT = "com.google.dagger:hilt-android:${Versions.HILT}"
    const val HILT_KAPT = "com.google.dagger:hilt-android-compiler:${Versions.HILT}"
    const val HILT_TESTING = "com.google.dagger:hilt-android-testing:${Versions.HILT_TESTING}"

    const val JUNIT4 = "junit:junit:${Versions.JUNIT4}" // testImplementation
    const val ANDROIDX_TEST_EXT = "androidx.test.ext:junit:${Versions.TEST_EXT}" // androidTestImplementation & testImplementation
    const val ESPRESSO = "androidx.test.espresso:espresso-core:${Versions.ESPRESSO}" // androidTestImplementation
    const val ESPRESSO_CONTRIB = "androidx.test.espresso:espresso-contrib:${Versions.ESPRESSO_CONTRIB}"
    const val ESPRESSO_IDLE = "androidx.test.espresso:espresso-idling-resource:${Versions.ESPRESSO_IDLE}"
    const val ANDROIDX_TEST_CORE = "androidx.test:core:${Versions.ANDROIDX_TEST_CORE}" // implementation
    const val ANDROIDX_TEST_CORE_KTX = "androidx.test:core-ktx:${Versions.ANDROIDX_TEST_CORE_KTX}" // testImplementation
    const val ANDROIDX_TESTING_CORE = "androidx.arch.core:core-testing:${Versions.ANDROIDX_TESTING_CORE}" // testImplementation
    const val ANDROIDX_TESTING_FRAGMENT = "androidx.fragment:fragment-testing:${Versions.ANDROIDX_TESTING_FRAGMENT}" // debugImplementation
    const val HAMCREST = "org.hamcrest:hamcrest-all:${Versions.HAMCREST}" // testImplementation
    const val ROBOLECTRIC  = "org.robolectric:robolectric:${Versions.ROBOLECTRIC}" // testImplementation
    const val KOTLINX_TEST_COROUTINES = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.KOTLINX_TEST_COROUTINES}" // androidTestImplementation
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