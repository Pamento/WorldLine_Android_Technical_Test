plugins {
    id("module-plugin")
}

dependencies {
    implementation(project(":common"))

    // Glide
    implementation(Dependencies.GLIDE)
    kapt(Dependencies.GLIDE_KAPT)
    // Retrofit
    implementation(Dependencies.RETROFIT2_MOSHI)
    implementation(Dependencies.RETROFIT2_MOSHI_KOTLIN)
}