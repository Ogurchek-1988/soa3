package com.soa.secondservice.controller

import com.soa.common.dto.ParticipantDto
import com.soa.secondservice.service.GrammyService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/grammy/bands")
class GrammyController {

    @Autowired
    private lateinit var grammyService: GrammyService

    @PostMapping("/{band-id}/nominate/{genre-id}")
    fun nominateBand(
        @PathVariable("band-id") bandId: Int,
        @PathVariable("genre-id") genreId: Int
    ): ResponseEntity<Any> {
        println("ПРОШЛО!")
       val tmp = grammyService.nominateBand(bandId, genreId)
        return ResponseEntity.status(tmp.status.value).build()
    }

    @PostMapping("/participants")
    fun addPart(
        @RequestBody participantDto: ParticipantDto
    ) {
        println(participantDto.toString())
        grammyService.addPart(participantDto)
    }

    @GetMapping("jopa")
    fun test() {
        println("JOPA")
    }

    @GetMapping("/test")
     fun l() {
        val tmp = grammyService.test()
    //    val tmp2 = toResponse(tmp)
        println("APAPAPAPAPAP")
        println(tmp.status)
    }
}