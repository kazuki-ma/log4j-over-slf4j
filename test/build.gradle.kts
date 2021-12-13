dependencies {
    implementation(project(":jdk"))
    implementation(project(":commons"))
    implementation(project(":log4j1"))
    implementation(project(":log4j2"))
    implementation(project(":slf4j"))

    // Used compile/coding time.
    implementation("org.slf4j:slf4j-api")
    implementation("org.slf4j:jul-to-slf4j") // Java Util Logging > SLF4J.

    // Used Runtime
    listOf(
            "ch.qos.logback:logback-classic", // The log implementation.

            "org.slf4j:jcl-over-slf4j", // Commons-Logging > SLF4J
            "org.slf4j:log4j-over-slf4j", // Log4j 1.x > SLF4J
            "org.apache.logging.log4j:log4j-to-slf4j", // Log4j 2.x > SLF4J
    ).forEach { runtimeOnly(it) }
}

configurations.all {
    exclude(group = "log4j") // = Old only log4j impl.
    exclude(group = "org.apache.logging.log4j", module = "log4j-slf4j-impl") // = SLF4J > Log4J 2.x. (Apache Side)
    exclude(group = "org.slf4j", module = "slf4j-log4j12") // = SLF4J > Log4J 1.x. (SLF4J Side)
    exclude(group = "org.slf4j", module = "slf4j-jdk14") // = SLF4J > JDK14.
    exclude(group = "commons-logging", module = "commons-logging-api") // Replaced by jcl-over-slf4j
    exclude(group = "commons-logging", module = "commons-logging") // Implementation is not needed.
    exclude(group = "org.apache.logging.log4j", module = "log4j-core")
}
