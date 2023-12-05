package com.soa.common.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable

data class PersonDto(
    @JsonProperty("name") val name: String,
    @JsonProperty("birthday") val birthday: String,
    @JsonProperty("passportID") val passportID: String,
    @JsonProperty("location") val location: LocationDto
): Serializable