plugins {
    id("module-plugin")
}

dependencies {
    implementation(Dependencies.ANDROIDX_CORE_KTX)
    implementation(Dependencies.ANDROIDX_APPCOMPAT)
    implementation(Dependencies.ANDROID_MATERIAL)
    implementation(Dependencies.HILT)
    kapt(Dependencies.HILT_KAPT)
    testImplementation(Dependencies.JUNIT4)
    androidTestImplementation(Dependencies.TEST_EXT)
    androidTestImplementation(Dependencies.ESPRESSO)
}