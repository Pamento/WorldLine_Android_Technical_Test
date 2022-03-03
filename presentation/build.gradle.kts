plugins {
    id("module-plugin")
}
android {
    sourceSets {
        getByName("debug") {
            java {
                srcDirs("src\\debug\\java", "src\\debug\\java")
            }
        }
        getByName("release") {
            java {
                srcDirs("src\\release\\java", "src\\release\\java")
            }
        }
    }
}

dependencies {
    implementation(project(":common"))
    implementation(project(":domain"))

    implementation(Dependencies.GLIDE)
    implementation(Dependencies.GLIDE_KAPT)
    implementation(Dependencies.ANDROIDX_FRAGMENT)
    implementation(Dependencies.ANDROIDX_RECYCLERVIEW)



    implementation(Dependencies.HILT)
    kapt(Dependencies.HILT_KAPT)

    // TEST
    androidTestImplementation(Dependencies.TEST_ANDROIDX_RUNNER)
    androidTestImplementation(Dependencies.TEST_EXT_TRUTH)
    androidTestImplementation(Dependencies.TEST_EXT_JUNIT)
    androidTestImplementation(Dependencies.TEST_ANDROIDX_RULES)
    androidTestImplementation(Dependencies.TEST_ANDROIDX_CONTRIB)
    androidTestImplementation(Dependencies.TEST_ANDROID_HILT)
    kaptAndroidTest(Dependencies.HILT_KAPT)
    kaptTest(Dependencies.HILT_KAPT)
    debugImplementation(Dependencies.ANDROIDX_FRAGMENT_TESTING)
}

kapt {
    correctErrorTypes = true
}

hilt {
    enableAggregatingTask = true
}
