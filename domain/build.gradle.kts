plugins {
    id("module-plugin")
}

dependencies {
    implementation(project(":common"))

    // Glide
    implementation(Dependencies.GLIDE)
    kapt(Dependencies.GLIDE_KAPT)
}