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
    group = "io.gitlab.arturbosch.detekt"

    apply(plugin = "io.gitlab.arturbosch.detekt")

    configure<io.gitlab.arturbosch.detekt.extensions.DetektExtension> {
        toolVersion = Versions.DETEKT
        source = files("$projectDir/src")
        config = files("${rootDir}/config/quality/detekt/detekt.yml")
        parallel = true
        autoCorrect = true
    }

    tasks.withType<Detekt> {
        exclude("**/test/")
        exclude(".*/tmp/.*")
    }

    dependencies {
        CONFIGURATION_DETEKT_PLUGINS(Plugins.DETEKT_KTLINT)
    }
}

val kotlinFiles = "**/*.kt"
val kotlinScriptFiles = "**/*.kts"
val resourceFiles = "**/resources/**"
val buildFiles = "**/build/**"

val detektFormat by tasks.registering(Detekt::class) {
    description = "Formats whole project."
    parallel = true
    disableDefaultRuleSets = true
    buildUponDefaultConfig = true
    autoCorrect = true
    include(kotlinFiles)
    include(kotlinScriptFiles)
    exclude(resourceFiles)
    exclude(buildFiles)
    reports {
        xml.required.set(false)
        html.required.set(false)
        txt.required.set(false)
    }
}