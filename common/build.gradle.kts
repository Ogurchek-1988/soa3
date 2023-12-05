plugins {
    id("java")
    kotlin("jvm") version "1.6.21"
}

group = "org.soa"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.0.1")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.12.0")
}

tasks.test {
    useJUnitPlatform()
}