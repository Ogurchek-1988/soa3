package com.soa.common.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable

data class LocationDto(
    @JsonProperty("x") val x: Float,
    @JsonProperty("y") val y: Double,
    @JsonProperty("name") val name: String
): Serializable