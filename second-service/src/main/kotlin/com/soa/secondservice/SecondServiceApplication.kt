package com.soa.secondservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.EnableEurekaClient

@SpringBootApplication
@EnableEurekaClient
class SecondServiceApplication

fun main(args: Array<String>) {
	runApplication<SecondServiceApplication>(*args)
}
