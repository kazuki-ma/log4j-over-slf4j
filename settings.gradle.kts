pluginManagement {
    repositories {
        maven("https://plugins.gradle.org/m2/")
    }
}

include("jdk")
include("commons")
include("log4j1")
include("log4j2")
include("slf4j")
include("test")
include("test-spring")
