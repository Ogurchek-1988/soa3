package com.soa.firstserviceejb.util

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.soa.firstserviceejb.model.musicBand.entity.MusicBandArray
import lombok.AllArgsConstructor
import lombok.NoArgsConstructor
import lombok.ToString
import java.io.Serializable


@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
data class ResultList(
    val s: String,
    val entityPage: MusicBandArray?,
    val i: Int) : Serializable