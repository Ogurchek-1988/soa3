package com.soa.firstserviceejb.model.musicBand.converter

import com.soa.common.dto.NewMusicBandDto
import com.soa.firstserviceejb.model.musicBand.entity.MusicBand
import com.soa.firstserviceejb.model.other.converter.CoordinatesConverter
import com.soa.firstserviceejb.model.other.converter.PersonConverter
import com.soa.firstserviceejb.util.DtoConverter

open class NewMusicBandConverter (
    private val personConverter: PersonConverter,
    private val coordinatesConverter: CoordinatesConverter
) : DtoConverter<MusicBand, NewMusicBandDto> {

    override fun toEntity(dto: NewMusicBandDto) = MusicBand(
        dto.name,
        coordinatesConverter.toEntity(dto.coordinates),
        dto.numberOfParticipants,
        dto.establishmentDate,
        dto.musicGenre,
        personConverter.toEntity(dto.frontMan)
    )
}