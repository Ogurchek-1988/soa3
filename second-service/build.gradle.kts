import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	war
	id("org.springframework.boot") version "2.7.10"
	id("io.spring.dependency-management") version "1.1.3"
	kotlin("plugin.spring") version "1.6.21"
	kotlin("jvm") version "1.6.21"
}

group = "com.soa"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation(project(":common"))
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation(kotlin("reflect"))
	compileOnly("javax.ws.rs:javax.ws.rs-api:2.1.1")
	compileOnly("org.projectlombok:lombok")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	annotationProcessor("org.projectlombok:lombok")
	providedRuntime("org.springframework.boot:spring-boot-starter-tomcat")
	testImplementation("org.springframework.boot:spring-boot-starter-test")

	implementation("io.ktor:ktor-client:2.1.2")
	implementation("io.ktor:ktor-client-apache:2.1.2")
	implementation("io.ktor:ktor-client-logging:2.1.2")
	/*implementation("org.springframework.cloud:spring-cloud-starter-config")*/
	implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")
}

springBoot {
	mainClass.set("com.soa.secondservice.SecondServiceApplicationKt")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs += "-Xjsr305=strict"
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

dependencyManagement {
	imports {
		mavenBom( "org.springframework.cloud:spring-cloud-dependencies:2021.0.8")
	}
}

