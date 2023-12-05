package com.soa.firstserviceejb.model.other.converter

import com.soa.common.dto.CoordinatesDto
import com.soa.firstserviceejb.model.other.entity.Coordinates
import com.soa.firstserviceejb.util.DtoConverter

class CoordinatesConverter: DtoConverter<Coordinates, CoordinatesDto> {

    override fun toEntity(dto: CoordinatesDto) = Coordinates(
        dto.x,
        dto.y
    )

    override fun toDto(entity: Coordinates) = CoordinatesDto(
        entity.coordinateX!!,
        entity.coordinateY!!
    )

}