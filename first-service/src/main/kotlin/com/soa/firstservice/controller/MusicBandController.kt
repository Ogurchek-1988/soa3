package com.soa.firstservice.controller

import com.soa.common.dto.MusicGenre
import com.soa.common.dto.NewMusicBandDto
import com.soa.firstservice.config.JNDIConfig
import com.soa.firstserviceejb.service.MusicBandService
import com.soa.firstserviceejb.util.ResultList
import jakarta.enterprise.context.ApplicationScoped
import jakarta.validation.constraints.Min
import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response

@Path("/bands")
@ApplicationScoped
open class MusicBandController (
    open val musicBandService: MusicBandService = JNDIConfig.musicBandService()
) {

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    open fun addBand(
        bandDto: NewMusicBandDto
    ): Response {
        println("addBand - controller1")
        return Response.ok(musicBandService.saveBand(bandDto)).build()
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    open fun getBand(
        @PathParam("id") id: Int
    ): Response {
        println("getBand - controller1")
        return Response.ok(musicBandService.getBandById(id)).build()
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    open fun updateBand(
        @PathParam("id") id: Int,
        bandDto: NewMusicBandDto
    ): Response {
        println("updateBand - controller1")
        return Response.ok(musicBandService.updateBand(id, bandDto)).build()
    }

    @DELETE
    @Path("/{id}")
    open fun deleteBand(
        @PathParam("id") id: Int
    ): Response {
        println("deleteBand - controller1")
        return Response.ok(musicBandService.deleteBand(id)).build()
    }

    @GET
    @Path("/search")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    open fun searchBands(
        @QueryParam("sort") sort: List<String>,
        @QueryParam("filter") filter: List<String>,
        @Min(0) @QueryParam("page") page: Int,
        @Min(1) @QueryParam("pageSize") pageSize: Int
    ): Response {
        println("Method with filters started")
        /*val tmp = musicBandService.getAllBands(sort, filter, page, pageSize)*/
        println("вышел из куьще")
        /*println(tmp)
        if (tmp.i == 200) {
            println("in if")
            return Response.ok(musicBandService.getAllBands(sort, filter, page, pageSize).entityPage).build()
        }*/
        return Response.ok(musicBandService.getAllBands(sort, filter, page, pageSize)).build()
    }

    @GET
    @Path("/sum/participants")
    open fun getSumParticipants(): Response {
        println("getSumParticipants - controller1")
        val test = musicBandService.getSumParticipants()
        println("test = $test")
        return Response.ok(test).build()
    }

    @POST
    @Path("/sum/participants")
    open fun getMinParticipants(
        @QueryParam("participants-number") participantsNumber: Int,
        @QueryParam("comparison") comparison: String
    ): Response {
        println("getMinParticipants - controller1")
        if (comparison == "lower")
            return Response.ok(musicBandService.getMinParticipants(participantsNumber)).build()
        return Response.noContent().build()
    }

    @POST
    @Path("/search/genre")
    open fun searchGenre(
        @QueryParam("genre") genre: MusicGenre,
        @QueryParam("comparison") comparison: String
    ): Response {
        println("searchGenre - controller1")
        if (comparison == "lower")
            return Response.ok(musicBandService.searchGenre(genre)).build()
        return Response.noContent().build()
    }

    @GET
    @Path("/test/jopa")
    open fun test() {
        println("TEST JOPA")
    }
}