package com.soa.firstserviceejb.model.grammy.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.validation.constraints.Min

typealias GrammyId = Int

@Entity
class Grammy(
    var bandId: Int,
    var genreId: Int
) {
    @Id
    @Min(1)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: GrammyId? = null
}