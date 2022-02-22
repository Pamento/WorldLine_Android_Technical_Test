import io.gitlab.arturbosch.detekt.Detekt
import io.gitlab.arturbosch.detekt.DetektPlugin
import io.gitlab.arturbosch.detekt.report.ReportMergeTask

// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    //ext.hilt_version = '2.40'
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        // don't need this (com.android.tools.build:gradle:7.1.1) here becose is cam from buildSrc.build.gradle.kts
        //classpath 'com.android.tools.build:gradle:7.1.1'
        //classpath "org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.0"
        classpath(Plugins.DETEKT)
        classpath(Plugins.HILT)
    }
}

// form here after add buildSrc rootProject build.gradle.kts the task is inreachable
//task clean(type: Delete) {
//    delete rootProject.buildDir
//}
//
//task reportMerge(type: ReportMergeTask) {
//    output = project.layout.buildDirectory.file("reports/detekt/merge.xml")
//    // or "reports/detekt/merge.sarif"
//}

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

//    configure<DetektExtension> {
//        toolVersion = Versions.DETEKT
//        input = files("$projectDir/src")
//        config = files("${project.rootDir}/detekt.yml")
//        parallel = true
//    }
//
//    tasks.withType<io.gitlab.arturbosch.detekt.Detekt> {
//        exclude("**/test/")
//        exclude(".*/tmp/.*")
//    }
//
//    dependencies {
//        CONFIGURATION_DETEKT_PLUGINS(Plugins.DETEKT_KTLINT)
//    }
}

//tasks.withType(Detekt).configureEach {
//    reports {
//        xml.required.set(true)
//        html.required.set(true)
//        txt.required.set(true)
//        sarif.required.set(true)
//    }
//}