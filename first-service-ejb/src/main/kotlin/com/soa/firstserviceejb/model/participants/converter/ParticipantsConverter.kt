package com.soa.firstserviceejb.model.participants.converter

import com.soa.common.dto.ParticipantDto
import com.soa.firstserviceejb.model.participants.entity.Participants
import com.soa.firstserviceejb.util.DtoConverter

class ParticipantsConverter: DtoConverter<Participants, ParticipantDto> {

    override fun toEntity(dto: ParticipantDto) = Participants(
        dto.name,
        dto.bandId
    )
}