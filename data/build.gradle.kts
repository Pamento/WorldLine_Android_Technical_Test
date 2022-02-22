plugins {
    id("module-plugin")
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":common"))

    implementation(Dependencies.RETROFIT2)
    implementation(Dependencies.RETROFIT2_MOSHI)
    implementation(Dependencies.RETROFIT2_MOSHI_KOTLIN)
}