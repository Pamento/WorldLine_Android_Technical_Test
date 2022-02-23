plugins {
    id("module-plugin")
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":common"))

    implementation(Dependencies.RETROFIT2)
    implementation(Dependencies.RETROFIT2_MOSHI)
    implementation(Dependencies.RETROFIT2_MOSHI_KOTLIN)
    // OkHttp3 bom for all dependencies of the platform
    implementation(Dependencies.OKHTTP3_BOM)
    implementation(Dependencies.OKHTTP3_INTERCEPTOR)

    kapt (Dependencies.ANDROIDX_KAPT)
}