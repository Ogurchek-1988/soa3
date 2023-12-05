package com.soa.common.dto

import java.io.Serializable
import java.time.LocalDate

data class MusicBandDto(
    val id: Int,
    val name: String,
    val coordinates: CoordinatesDto,

    val creationDate: LocalDate,
    val numberOfParticipants: Int,
    val establishmentDate: String,
    val musicGenre: MusicGenre,
    val frontMan: PersonDto
): Serializable