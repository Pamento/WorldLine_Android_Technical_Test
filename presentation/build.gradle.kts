plugins {
    id("module-plugin")
}

dependencies {
    implementation(project(":common"))
    implementation(project(":domain"))

    implementation(Dependencies.GLIDE)
    implementation(Dependencies.GLIDE_KAPT)
    implementation(Dependencies.ANDROIDX_FRAGMENT)
    implementation(Dependencies.ANDROIDX_RECYCLERVIEW)
}

kapt {
    correctErrorTypes = true
}

hilt {
    enableAggregatingTask = true
}
