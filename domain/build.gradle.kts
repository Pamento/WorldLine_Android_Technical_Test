plugins {
    id("module-plugin")
}

dependencies {
    implementation(project(":common"))

}

hilt {
    enableExperimentalClasspathAggregation = true
}

kapt {
    correctErrorTypes = true
}

hilt {
    enableAggregatingTask = true
}