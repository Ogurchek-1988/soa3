package com.soa.firstservice.controller

import com.soa.common.dto.ParticipantDto
import com.soa.firstservice.config.JNDIConfig
import com.soa.firstserviceejb.service.GrammyService
import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response

@Path("/grammy")
open class GrammyController (
    open val grammyService: GrammyService = JNDIConfig.grammyService()
) {

    @POST
    @Path("/{band-id}/part/{name}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    open fun addPart(
        @PathParam("band-id") bandId: Int,
        @PathParam("name") name: String
    ): Response {
        val participantDto = ParticipantDto(name, bandId)
        return Response.ok(grammyService.saveParticipant(participantDto)).build()
    }

    @POST
    @Path("/{band-id}/nominate/{genre-id}")
    @Produces(MediaType.APPLICATION_JSON)
    open fun nominateBand(
        @PathParam("band-id") bandId: Int,
        @PathParam("genre-id") genreId: Int
    ): Response = Response.ok(grammyService.nominateBand(bandId, genreId)).build()

    @GET
    @Path("/test")
    open fun test(): Response {
        println("JOPA JOPA 1")
        return Response.ok("loh").build()
    }
}