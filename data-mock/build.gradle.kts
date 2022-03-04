plugins {
    id("module-plugin")
}

dependencies {
    implementation(project(":common"))
    api(Dependencies.MOCKWS)
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