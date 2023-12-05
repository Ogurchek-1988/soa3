package com.soa.firstserviceejb.model.musicBand.entity

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.io.Serial
import java.io.Serializable

@JsonIgnoreProperties(ignoreUnknown = true)
data class MusicBandArray (
    var page: Long? = null,
    var pagesTotal: Long? = null,
    var pageSize: Long? = null,
    var bands: ArrayList<MusicBand> = ArrayList()
): Serializable
