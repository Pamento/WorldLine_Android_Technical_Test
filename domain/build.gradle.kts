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
    enableAggregatingTask = true
}