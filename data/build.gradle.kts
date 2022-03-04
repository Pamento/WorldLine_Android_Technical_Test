plugins {
    id("module-plugin")
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

    implementation(Dependencies.HILT)
    kapt(Dependencies.HILT_KAPT)

    kapt (Dependencies.ANDROIDX_KAPT)
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