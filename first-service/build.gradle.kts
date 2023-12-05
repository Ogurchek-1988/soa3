import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  id("war")
  kotlin("jvm")
  kotlin("plugin.jpa") version "1.6.21"
  id("org.hibernate.orm") version "6.4.0.CR1"
  id("io.freefair.lombok") version "8.4"
}

group = "com.soa"
version "1.0-SNAPSHOT"

repositories {
  mavenCentral()
}

tasks.withType<KotlinCompile> {
  kotlinOptions {
    jvmTarget = "11"
  }
}

dependencies {
  implementation(kotlin("reflect"))
  compileOnly("jakarta.persistence:jakarta.persistence-api:3.0.0")
  compileOnly("jakarta.ws.rs:jakarta.ws.rs-api:3.0.0")
  compileOnly("jakarta.servlet:jakarta.servlet-api:5.0.0")
  compileOnly("jakarta.platform:jakarta.jakartaee-web-api:10.0.0")
  implementation("jakarta.validation:jakarta.validation-api:3.0.2")
  implementation("org.postgresql:postgresql:42.5.1")
  implementation(project(":common"))
  implementation(project(":first-service-ejb"))
  implementation("org.springframework.data:spring-data-commons:2.7.17")
  compileOnly("org.jboss.resteasy:resteasy-jaxrs:3.15.3.Final")
  implementation("org.glassfish.jaxb:jaxb-runtime:4.0.2")
  implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.12.0")
  implementation("org.wildfly:wildfly-ejb-client-bom:30.0.0.Final")
}