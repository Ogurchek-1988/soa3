package com.soa.secondservice.service

import com.soa.common.dto.ParticipantDto
import com.soa.secondservice.util.toResponse
import kotlinx.coroutines.runBlocking
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class GrammyService {

    @Autowired
    private lateinit var firstServiceClient: FirstServiceClient

    fun nominateBand(bandId: Int, genreId: Int) = runBlocking{
        return@runBlocking firstServiceClient.nominateBand(bandId, genreId)
    }

    fun addPart(participantDto: ParticipantDto) = runBlocking {
        return@runBlocking toResponse(firstServiceClient.addPart(participantDto))
    }

    fun test() = runBlocking {
        return@runBlocking firstServiceClient.test()
    }
}