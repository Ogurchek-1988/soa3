package com.soa.firstserviceejb.model.other.converter

import com.soa.common.dto.PersonDto
import com.soa.firstserviceejb.model.other.entity.Person
import com.soa.firstserviceejb.util.DtoConverter

class PersonConverter(
    private val locationConverter: LocationConverter
) : DtoConverter<Person, PersonDto> {

    override fun toEntity(dto: PersonDto) = Person(
        dto.name,
        dto.birthday,
        dto.passportID,
        locationConverter.toEntity(dto.location)
    )

    override fun toDto(entity: Person) = PersonDto(
        entity.personName!!,
        entity.birthday!!,
        entity.passportID!!,
        locationConverter.toDto(entity.location!!)
    )
}