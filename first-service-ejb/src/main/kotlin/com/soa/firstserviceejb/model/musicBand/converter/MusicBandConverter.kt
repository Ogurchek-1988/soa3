package com.soa.firstserviceejb.model.musicBand.converter

import com.soa.common.dto.MusicBandDto
import com.soa.firstserviceejb.model.musicBand.entity.MusicBand
import com.soa.firstserviceejb.model.other.converter.CoordinatesConverter
import com.soa.firstserviceejb.model.other.converter.PersonConverter
import com.soa.firstserviceejb.util.DtoConverter

class MusicBandConverter(
    private val personConverter: PersonConverter,
    private val coordinatesConverter: CoordinatesConverter
) : DtoConverter<MusicBand, MusicBandDto> {
    override fun toDto(entity: MusicBand) = MusicBandDto(
        entity.id!!,
        entity.name!!,
        coordinatesConverter.toDto(entity.coordinates!!),
        entity.creationDate!!,
        entity.numberOfParticipants!!,
        entity.establishmentDate!!,
        entity.musicGenre!!,
        personConverter.toDto(entity.frontMan!!)
    )
}