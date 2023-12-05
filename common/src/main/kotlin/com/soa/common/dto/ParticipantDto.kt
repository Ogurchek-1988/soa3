package com.soa.common.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable

data class ParticipantDto(
    @JsonProperty("name") val name: String,
    @JsonProperty("band-id") val bandId: Int
): Serializable
