plugins {
    id("module-plugin")
}

dependencies {
    implementation(project(":common"))

    implementation(Dependencies.GLIDE)
    implementation(Dependencies.GLIDE_KAPT)
}

kapt {
    correctErrorTypes = true
}

hilt {
    enableExperimentalClasspathAggregation = true
    enableAggregatingTask = true
}