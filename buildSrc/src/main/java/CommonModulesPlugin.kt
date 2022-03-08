import org.gradle.api.Plugin
import org.gradle.api.Project
import com.android.build.gradle.BaseExtension
import com.android.build.gradle.AppExtension
import com.android.build.gradle.LibraryExtension
import org.gradle.api.JavaVersion
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

class CommonModulesPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.plugins.apply("com.android.library")
        project.plugins.apply("kotlin-android")
        project.plugins.apply("kotlin-kapt")
        project.plugins.apply("dagger.hilt.android.plugin")

        val androidExtensions = project.extensions.getByName("android")
        if (androidExtensions is BaseExtension) {
            androidExtensions.apply {
                compileSdkVersion(Versions.COMPILE_SDK)

                compileOptions {
                    sourceCompatibility = JavaVersion.VERSION_1_8
                    targetCompatibility = JavaVersion.VERSION_1_8
                }

                project.tasks.withType(KotlinCompile::class.java).configureEach {
                    kotlinOptions {
                        jvmTarget = "1.8"
                    }
                }

                defaultConfig {
                    minSdk = Versions.MIN_SDK
                    targetSdk = Versions.TARGET_SDK
                    versionCode = 1
                    versionName = "1.0"

                    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                }

                buildFeatures.viewBinding = true
                // declare buildConfig for have access to possibility to pass variable by there
                buildFeatures.buildConfig = true

                /**
                 * Configure the pro-guard rules for the R8 compiler.
                 * We need to check for LibraryExtension(as they use consumer-rules.pro)
                 * and AppExtension(as they normally use proguar-rules.pro) within the android block.
                 */
                when (this) {
                    is LibraryExtension -> {
                        defaultConfig {
                            // apply the pro guard rules for library
                            consumerProguardFiles("consumer-rules.pro")
                        }
                    }

                    is AppExtension -> {
                        buildTypes {
                            getByName("release") {
                                isMinifyEnabled = false
                                proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
                            }
                        }
                    }
                }
            }
        }

        project.dependencies {
            add(DependencyType.IMPLEMENTATION,Dependencies.ANDROIDX_CORE_KTX)
            add(DependencyType.IMPLEMENTATION, Dependencies.ANDROIDX_APPCOMPAT)
            add(DependencyType.IMPLEMENTATION, Dependencies.ANDROID_MATERIAL)

            add(DependencyType.IMPLEMENTATION, Dependencies.HILT)
            add(DependencyType.KAPT, Dependencies.HILT_KAPT)
            add(DependencyType.ANDROID_TEST_IMPLEMENTATION, Dependencies.HILT_TESTING)

            add(DependencyType.IMPLEMENTATION, Dependencies.COROUTINE_ANDROID)
            add(DependencyType.IMPLEMENTATION, Dependencies.COROUTINE_ANDROID_CORE)
            add(DependencyType.IMPLEMENTATION, Dependencies.COROUTINE_LEGACY_SUPPORT)
            add(DependencyType.IMPLEMENTATION, Dependencies.RX_KOTLIN)

            add(DependencyType.TEST_IMPLEMENTATION, Dependencies.JUNIT4)
            add(DependencyType.ANDROID_TEST_IMPLEMENTATION, Dependencies.ANDROIDX_TEST_EXT)
            add(DependencyType.ANDROID_TEST_IMPLEMENTATION, Dependencies.ESPRESSO)
            add(DependencyType.ANDROID_TEST_IMPLEMENTATION, Dependencies.ESPRESSO_CONTRIB)
            add(DependencyType.ANDROID_TEST_IMPLEMENTATION, Dependencies.KOTLINX_TEST_COROUTINES)
            add(DependencyType.IMPLEMENTATION, Dependencies.ANDROIDX_TEST_CORE)
            add(DependencyType.TEST_IMPLEMENTATION, Dependencies.ANDROIDX_TEST_CORE_KTX)
            add(DependencyType.TEST_IMPLEMENTATION, Dependencies.ANDROIDX_TESTING_CORE)
            add(DependencyType.TEST_IMPLEMENTATION, Dependencies.HAMCREST)
            add(DependencyType.TEST_IMPLEMENTATION, Dependencies.ROBOLECTRIC)
        }
    }
}