// Top-level build file where you can add configuration options common to all sub-projects/modules.
import io.gitlab.arturbosch.detekt.Detekt
import io.gitlab.arturbosch.detekt.DetektCreateBaselineTask
import io.gitlab.arturbosch.detekt.report.ReportMergeTask

plugins {
    id("com.android.application") version "8.0.2" apply false
    id("org.jetbrains.kotlin.android") version "1.8.22" apply false
    id("com.google.devtools.ksp") version "1.8.22-1.0.11" apply false
    id("com.diffplug.spotless") version "6.19.0" apply false
    id("io.gitlab.arturbosch.detekt") version "1.23.0"
}

tasks.register<Delete>("clean") {
    delete(rootProject.buildDir)
}

// Detekt report merging
val reportMerge = tasks.register<ReportMergeTask>("reportMerge") {
    output.set(project.layout.buildDirectory.file("reports/detekt/merge.sarif"))
}

subprojects {
    // Detekt
    pluginManager.withPlugin("detekt") {
        detekt {
            buildUponDefaultConfig = true
        }
    }

    tasks.withType<Detekt>().configureEach {
        jvmTarget = "1.8"
        basePath = rootProject.projectDir.absolutePath

        reports {
            sarif.required.set(true)
            // workaround for https://github.com/detekt/detekt/issues/4192
            sarif.outputLocation.set(project.file("build/reports/detekt/detekt.sarif"))
        }
        finalizedBy(reportMerge)

        reportMerge.configure {
            input.from(sarifReportFile)
        }
    }

    tasks.withType<DetektCreateBaselineTask>().configureEach {
        jvmTarget = "1.8"
    }
}
