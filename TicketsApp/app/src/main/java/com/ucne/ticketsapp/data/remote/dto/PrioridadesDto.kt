package com.ucne.ticketsapp.data.remote.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PrioridadesDto(
    val prioridadId : Int,
    val prioridad : String,
    val tickets : List<TicketsDto>
)
