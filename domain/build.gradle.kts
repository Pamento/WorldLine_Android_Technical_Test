plugins {
    id("module-plugin")
}

dependencies {
    implementation(project(":common"))

}

kapt {
    correctErrorTypes = true
}

hilt {
    enableExperimentalClasspathAggregation = true
    enableAggregatingTask = true
}