package com.soa.firstserviceejb

import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces

@Path("/hello-world")
open class HelloResource {
    @GET
    @Produces("text/plain")
    fun hello() : String {
        return "Hello, World!"
    }
}