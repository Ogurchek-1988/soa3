package com.soa.secondservice

import com.soa.secondservice.config.WebServiceConfig
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.EnableEurekaClient
import java.util.Arrays

@SpringBootApplication
@EnableEurekaClient
class SecondServiceApplication

fun main(args: Array<String>) {
	SpringApplication.run(
		listOf(
		SecondServiceApplication::class.java,
		WebServiceConfig::class.java
	).toTypedArray(), args)
}
