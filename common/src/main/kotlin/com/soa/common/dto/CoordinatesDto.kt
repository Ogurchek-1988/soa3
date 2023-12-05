package com.soa.common.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable

data class CoordinatesDto(
    @JsonProperty("x") val x: Double,
    @JsonProperty("y") val y: Double
): Serializable