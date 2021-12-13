dependencies {
    implementation("org.slf4j:log4j-over-slf4j")
    implementation("log4j:log4j:1.2.17")

    implementation(project(":log4j1"))
    implementation("org.springframework.boot:spring-boot-starter-logging")
}
