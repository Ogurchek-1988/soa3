package com.soa.firstserviceejb.model.musicBand.entity

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.soa.common.dto.MusicGenre
import com.soa.firstserviceejb.model.other.entity.Coordinates
import com.soa.firstserviceejb.model.other.entity.Person
import jakarta.persistence.*
import jakarta.validation.constraints.Min
import org.hibernate.annotations.CreationTimestamp
import java.io.Serial
import java.io.Serializable
import java.time.LocalDate

typealias MusicBandId = Int

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
class MusicBand(
    var name: String? = null,
    @field: Embedded
    var coordinates: Coordinates? = null,
    var numberOfParticipants: Int? = null,
    var establishmentDate: String? = null,
    @Enumerated(EnumType.STRING)
    var musicGenre: MusicGenre? = null,
    @field: Embedded
    var frontMan: Person? = null
): Serializable {

    @Id
    @Min(1)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: MusicBandId? = null

    @CreationTimestamp
    @field:Column(nullable = false)
    var creationDate: LocalDate? = null
}