plugins {
    id("module-plugin")
}

dependencies {
    implementation(project(":common"))
    api(Dependencies.MOCKWS)
}

kapt {
    correctErrorTypes = true
}

hilt {
    enableAggregatingTask = true
}