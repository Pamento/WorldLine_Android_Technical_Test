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
}

kapt {
    correctErrorTypes = true
}

hilt {
    enableAggregatingTask = true
}
