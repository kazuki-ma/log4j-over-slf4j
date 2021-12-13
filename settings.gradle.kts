pluginManagement {
    repositories {
        maven("https://plugins.gradle.org/m2/")
    }
}

include(":log4j1_ordering:bad_log4j1used")
include(":log4j1_ordering:good_slf4jprefered")
include("jdk")
include("commons")
include("log4j1")
include("log4j2")
include("slf4j")
include("test")
include("test-spring")
