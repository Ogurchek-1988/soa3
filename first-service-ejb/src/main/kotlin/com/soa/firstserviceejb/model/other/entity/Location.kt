package com.soa.firstserviceejb.model.other.entity

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import org.jetbrains.annotations.NotNull
import java.io.Serial
import java.io.Serializable

@Embeddable
@JsonIgnoreProperties(ignoreUnknown = true)
class Location(

    @field:Column(nullable = false)
    @field:NotNull
    var locationX: Float? = null,

    @field:Column(nullable = true)
    var locationY: Double? = null,

    @field:Column(nullable = false)
    @field:NotNull
    var locationName: String? = null
): Serializable