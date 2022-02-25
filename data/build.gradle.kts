import java.io.File
import java.io.FileInputStream
import java.util.*

plugins {
    id("module-plugin")
}

val localProperties = Properties().apply {
    load(FileInputStream(File(rootProject.rootDir, "local.properties")))
}

android {
    buildTypes {
        getByName("debug") {
            buildConfigField("String", "API_KEY", "\"${localProperties["API_KEY"]}\"")
            resValue("string", "API_KEY", "${localProperties["API_KEY"]}")
        }
    }
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":common"))


    implementation(Dependencies.GSON)
    implementation(Dependencies.RETROFIT2)
    implementation(Dependencies.RETROFIT2_GSON)

    // OkHttp3 bom for all dependencies of the platform
    implementation(Dependencies.OKHTTP3_BOM)
    implementation(Dependencies.OKHTTP3_INTERCEPTOR)

    kapt (Dependencies.ANDROIDX_KAPT)
}