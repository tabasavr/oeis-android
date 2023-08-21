// Top-level build file where you can add configuration options common to all sub-projects/modules.
import io.gitlab.arturbosch.detekt.Detekt
import io.gitlab.arturbosch.detekt.DetektCreateBaselineTask
import io.gitlab.arturbosch.detekt.report.ReportMergeTask

plugins {
    alias(libs.plugins.agp.app) apply false
    alias(libs.plugins.kotlin) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.spotless) apply false
    alias(libs.plugins.detekt)
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
