package com.soa.firstserviceejb.model.other.entity

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.soa.firstserviceejb.model.other.entity.Location
import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import jakarta.persistence.Embedded
import org.jetbrains.annotations.NotNull
import java.io.Serial
import java.io.Serializable

@Embeddable
@JsonIgnoreProperties(ignoreUnknown = true)
class Person (
    @field:Column(nullable = false)
    @field:NotNull
    var personName: String? = null,
    @field:Column(nullable = true)
    var birthday: String? = null,
    @field:Column(nullable = false)
    @field:NotNull
    var passportID: String? = null,
    @field:Embedded
    var location: Location? = null
) : Serializable