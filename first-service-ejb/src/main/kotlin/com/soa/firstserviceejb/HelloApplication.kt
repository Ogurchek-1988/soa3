package com.soa.firstserviceejb

import jakarta.ws.rs.ApplicationPath
import jakarta.ws.rs.core.Application

@ApplicationPath("/api")
open class HelloApplication : Application() {

}