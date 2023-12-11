package com.soa.secondservice.controller

import com.soa.common.dto.ParticipantDto
import com.soa.secondservice.service.GrammyService
import com.soa.secondservice.soap.AddPart
import com.soa.secondservice.soap.NominateBand
import lombok.RequiredArgsConstructor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.ws.server.endpoint.annotation.Endpoint
import org.springframework.ws.server.endpoint.annotation.PayloadRoot
import org.springframework.ws.server.endpoint.annotation.RequestPayload
import org.springframework.ws.server.endpoint.annotation.ResponsePayload

const val NAMESPACE_URI = "http://com/soa/secondservice/soap"

@Endpoint
@RequiredArgsConstructor
class GrammyEndpoint {

    @Autowired
    private lateinit var grammyService: GrammyService

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "nominateBand")
    @ResponsePayload
    fun nominateBand(@RequestPayload request: NominateBand) { grammyService.nominateBand(request.bandId, request.genreId) }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addPart")
    @ResponsePayload
    fun addPart(@RequestPayload request: AddPart) { grammyService.addPart(ParticipantDto(request.name, request.bandId)) }
}