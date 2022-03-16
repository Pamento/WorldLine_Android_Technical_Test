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
}

kapt {
    correctErrorTypes = true
}

hilt {
    enableExperimentalClasspathAggregation = true
    enableAggregatingTask = true
}