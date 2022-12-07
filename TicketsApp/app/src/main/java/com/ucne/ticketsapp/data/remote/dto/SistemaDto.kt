package com.ucne.ticketsapp.data.remote.dto

import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class SistemaDto(
    val sistemaId : Int,
    val sistema: String,
    val tickets : List<TicketsDto>
)
