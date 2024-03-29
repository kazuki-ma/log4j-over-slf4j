plugins {
    id("org.gradle.java")
    id("org.springframework.boot") version "2.5.6" apply false
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
}

subprojects {
    if (!file("src").isDirectory) {
        return@subprojects
    }

    apply {
        plugin("io.spring.dependency-management")
        plugin("java")
        plugin("org.gradle.project-report")
    }

    repositories {
        mavenCentral()
    }

    dependencyManagement {
        imports {
            mavenBom(org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES) {
                extra["log4j2.version"] = "2.15.0"
            }
        }
    }

    dependencies {
        testImplementation("junit:junit")
    }

    tasks.named<DependencyReportTask>("dependencyReport"){
        configurations = setOf(
                project.configurations.getByName("compileClasspath"),
                project.configurations.getByName("runtimeClasspath"),
                project.configurations.getByName("testRuntimeClasspath"),
        )
        outputFile = project.file("dependencies.txt")
    }
}
