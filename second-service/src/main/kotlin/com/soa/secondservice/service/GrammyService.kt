package com.soa.secondservice.service

import com.soa.common.dto.ParticipantDto
import io.ktor.client.statement.*
import kotlinx.coroutines.runBlocking
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class GrammyService {

    @Autowired
    private lateinit var firstServiceClient: FirstServiceClient

    fun nominateBand(bandId: Int, genreId: Int):HttpResponse {
        println("ЕЕЕЕЕ ТАК ЕЁ")
        return firstServiceClient.nominateBand(bandId, genreId)
    }


    fun addPart(participantDto: ParticipantDto) = firstServiceClient.addPart(participantDto)

    fun test() = runBlocking {
        return@runBlocking firstServiceClient.test()
    }
}