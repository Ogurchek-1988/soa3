package com.soa.firstservice.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import jakarta.ws.rs.ext.ContextResolver
import jakarta.ws.rs.ext.Provider


@Provider
class ObjectMapperProvider : ContextResolver<ObjectMapper> {
    private val objectMapper: ObjectMapper = createObjectMapper()

    override fun getContext(type: Class<*>): ObjectMapper {
        return objectMapper
    }

    private fun createObjectMapper(): ObjectMapper {
        val objectMapper = ObjectMapper()
        objectMapper.registerModule(JavaTimeModule()) // Register the Java 8 date/time module
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
        return objectMapper
    }
}