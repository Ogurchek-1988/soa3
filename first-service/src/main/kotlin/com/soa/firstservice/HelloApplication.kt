package com.soa.firstservice

import com.fasterxml.jackson.databind.ObjectMapper
import com.soa.firstservice.config.ObjectMapperProvider
import jakarta.servlet.ServletConfig
import jakarta.ws.rs.ApplicationPath
import jakarta.ws.rs.core.Application
import jakarta.ws.rs.core.Context
import org.jboss.resteasy.plugins.server.servlet.ResteasyContextParameters

@ApplicationPath("/api")
open class HelloApplication : Application() {
}
