import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	war
	id("org.springframework.boot") version "3.0.0"
	id("io.spring.dependency-management") version "1.1.4"
	kotlin("plugin.spring") version "1.6.21"
	kotlin("jvm") version "1.6.21"
}

group = "com.soa"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

sourceSets {
	main {
		java {
			srcDir("src/main/kotlin")
			srcDir("build/generated-sources/jaxb")
		}
	}
}

val jaxb by configurations.creating

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
	implementation("org.springframework.ws:spring-ws-core")

	implementation("io.ktor:ktor-client:2.1.2")
	implementation("io.ktor:ktor-client-apache:2.1.2")
	implementation("io.ktor:ktor-client-logging:2.1.2")
	implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")

	implementation("wsdl4j:wsdl4j")
	implementation("jakarta.xml.bind:jakarta.xml.bind-api:4.0.0")
	implementation("jakarta.activation:jakarta.activation-api:2.1.0")
	implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-xml")
	jaxb("org.glassfish.jaxb:jaxb-xjc")
	implementation("jakarta.xml.soap:jakarta.xml.soap-api")
	implementation("com.sun.xml.messaging.saaj:saaj-impl")
}

springBoot {
	mainClass.set("com.soa.secondservice.SecondServiceApplicationKt")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs += "-Xjsr305=strict"
		jvmTarget = JavaVersion.VERSION_17.toString()
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

tasks.register("genJaxb") {
	ext["sourcesDir"] = "${buildDir}/generated-sources/jaxb"
	ext["schema"] = "src/main/resources/second-service.xsd"

	ext["sourcesDir"]?.let { outputs.dir(it) }

	doFirst {
		ant.withGroovyBuilder {
			"taskdef"("name" to "xjc", "classname" to "com.sun.tools.xjc.XJCTask", "classpath" to jaxb.asPath)
			ext["sourcesDir"]?.let { mkdir(it) }

			"xjc"("destdir" to ext["sourcesDir"], "schema" to ext["schema"]) {
				"arg"("value" to "-wsdl")
				"produces"("dir" to ext["sourcesDir"], "includes" to "**/*.java")
			}
		}
	}
}

tasks.compileKotlin {
	dependsOn("genJaxb")
}

