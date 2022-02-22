import java.io.File
import java.io.IOException
import java.util.concurrent.TimeUnit

object Versions {

    const val TIMEOUT_MINUTES = 60L

    const val COMPILE_SDK = 31
    const val MIN_SDK = 21
    const val TARGET_SDK = 31
    const val HILT = "2.40"
    const val DETEKT = "1.17.1"
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
    const val RETROFIT_CORE = ""
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