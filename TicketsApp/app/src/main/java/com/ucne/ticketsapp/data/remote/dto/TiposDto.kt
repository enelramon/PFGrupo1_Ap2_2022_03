package com.ucne.ticketsapp.data.remote.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TiposDto(
    val tipoId: Int,
    val tipo: String,
    val tickets: List<TicketsDto>
)
