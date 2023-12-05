package com.soa.firstserviceejb.model.other.converter

import com.soa.common.dto.LocationDto
import com.soa.firstserviceejb.model.other.entity.Location
import com.soa.firstserviceejb.util.DtoConverter

class LocationConverter: DtoConverter<Location, LocationDto> {

    override fun toEntity(dto: LocationDto) = Location(
        dto.x,
        dto.y,
        dto.name
    )

    override fun toDto(entity: Location) = LocationDto(
        entity.locationX!!,
        entity.locationY!!,
        entity.locationName!!
    )
}