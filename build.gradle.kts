import io.gitlab.arturbosch.detekt.CONFIGURATION_DETEKT_PLUGINS
import io.gitlab.arturbosch.detekt.Detekt

buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(Plugins.DETEKT)
        classpath(Plugins.HILT)
    }
}

subprojects {
    apply(plugin = "io.gitlab.arturbosch.detekt")
    // The directories where detekt looks for source files.
    // Defaults to `files("src/main/java", "src/test/java", "src/main/kotlin", "src/test/kotlin")`.
    //sources = files("${project.rootDir}/app/src/main/java", "${project.rootDir}/app/src/main/kotlin")

// refactor to multi-module
//    detekt {
//        //toolVersion = "1.19.0"
//        parallel = true
//        config = files("${project.rootDir}/detekt.yml")
//
//        // Applies the config files on top of detekt's default config file. `false` by default.
//        buildUponDefaultConfig = true
//    }
//    plugins.withType(DetektPlugin) {
//        tasks.withType(Detekt) { detektTask ->
//            finalizedBy(reportMerge)
//
//            reportMerge.configure { mergeTask ->
//                mergeTask.input.from(detektTask.xmlReportFile) // or detektTask.sarifReportFile
//            }
//        }
//    }

    configure<io.gitlab.arturbosch.detekt.extensions.DetektExtension> {
        toolVersion = Versions.DETEKT
        input = files("$projectDir/src")
        config = files("${project.rootDir}/detekt.yml")
        parallel = true
    }

    tasks.withType<Detekt> {
        exclude("**/test/")
        exclude(".*/tmp/.*")
    }

    dependencies {
        CONFIGURATION_DETEKT_PLUGINS(Plugins.DETEKT_KTLINT)
    }
}

//tasks.withType(Detekt).configureEach {
//    reports {
//        xml.required.set(true)
//        html.required.set(true)
//        txt.required.set(true)
//        sarif.required.set(true)
//    }
//}