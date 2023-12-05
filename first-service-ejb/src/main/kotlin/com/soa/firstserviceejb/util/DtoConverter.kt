package com.soa.firstserviceejb.util

interface DtoConverter<Entity, Dto> {
    fun toEntity(dto: Dto): Entity {
        throw NotImplementedError()
    }

    fun toDto(entity: Entity): Dto {
        throw NotImplementedError()
    }
}