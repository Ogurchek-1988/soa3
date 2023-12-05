package com.soa.firstserviceejb.model.other.entity

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import org.jetbrains.annotations.NotNull
import java.io.Serial
import java.io.Serializable

@Embeddable
@JsonIgnoreProperties(ignoreUnknown = true)
class Coordinates(

    @field:Column(nullable = false)
    @field:NotNull
    var coordinateX: Double? = null,

    @field:Column(nullable = false)
    @field:NotNull
    var coordinateY: Double? = null,
) : Serializable {
    companion object {
        @Serial
        private val serialVersionUID = -2349276543L
    }
}