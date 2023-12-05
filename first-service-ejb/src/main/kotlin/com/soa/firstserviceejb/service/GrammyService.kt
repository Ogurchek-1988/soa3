package com.soa.firstserviceejb.service

import com.soa.common.dto.ParticipantDto
import com.soa.firstserviceejb.model.grammy.entity.Grammy
import com.soa.firstserviceejb.model.grammy.repository.GrammyRepository
import com.soa.firstserviceejb.model.participants.converter.ParticipantsConverter
import com.soa.firstserviceejb.model.participants.repository.ParticipantsRepository
import jakarta.ejb.Remote
import jakarta.ejb.Stateless
import jakarta.inject.Inject
import jakarta.ws.rs.core.Response
import org.jboss.ejb3.annotation.Pool

@Remote
interface GrammyService {
    fun nominateBand(bandId: Int, genreId: Int): Int
    fun saveParticipant(participantDto: ParticipantDto): String
}

@Stateless
@Pool("slsb-strict-max-pool")
open class GrammyServiceImpl: GrammyService {

    @Inject
    private lateinit var grammyRepository: GrammyRepository

    @Inject
    private lateinit var participantsRepository: ParticipantsRepository

    private var participantsConverter: ParticipantsConverter = ParticipantsConverter()

    override fun nominateBand(bandId: Int, genreId: Int): Int {
        println("nominate method")
        val band = grammyRepository.save(Grammy(bandId, genreId))
        return band.bandId
    }

    override fun saveParticipant(participantDto: ParticipantDto): String {
        val participant = participantsRepository.save(participantsConverter.toEntity(participantDto))
        return participant.name
    }
}