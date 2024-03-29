dependencies {
    implementation(project(":jdk"))
    implementation(project(":commons"))
    implementation(project(":log4j1"))
    implementation(project(":log4j2"))
    implementation(project(":slf4j"))

    // Used compile/coding time.
    implementation("org.springframework.boot:spring-boot-starter-logging") // 実際は starter-web の依存とかに入ってることが多い)

    // Used Runtime
    runtimeOnly("org.slf4j:log4j-over-slf4j")
    // log4j 1.x だけ個別にルーティングする必要がある
    // 2015 年に EOL のため 2017 年に spring-boot-starter-logging からも取り除かれている
    // https://github.com/spring-projects/spring-boot/issues/11148

    testImplementation("org.springframework.boot:spring-boot-starter-test")
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
