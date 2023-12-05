package com.soa.common.dto


import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable

data class NewMusicBandDto(
    @JsonProperty("name") val name: String,
    @JsonProperty("coordinates") val coordinates: CoordinatesDto,
    @JsonProperty("numberOfParticipants") val numberOfParticipants: Int,
    @JsonProperty("establishmentDate") val establishmentDate: String,
    @JsonProperty("musicGenre") val musicGenre: MusicGenre,
    @JsonProperty("frontMan") val frontMan: PersonDto
): Serializable