package com.ucne.ticketsapp.data.remote.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ClienteDto(
    val clienteId: Int = 0,
    val nombres: String = "",
    val clave: String = "",
    val configuracionId: Int = 0,
    val tickets: List<TicketsDto>? = null,
    val respuestas: List<RespuestaDto>? = null
)
