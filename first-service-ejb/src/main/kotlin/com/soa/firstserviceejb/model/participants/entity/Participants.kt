package com.soa.firstserviceejb.model.participants.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.validation.constraints.Min

typealias ParticipantsId = Int

@Entity
class Participants(
    var name: String,
    var bandId: Int
) {
    @Id
    @Min(1)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: ParticipantsId? = null
}