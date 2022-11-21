package com.ucne.ticketsapp.data.remote.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ClienteDto(
    val clienteId: Int,
    val nombres: String,
    val clave : String,
    val configuracionId : Int,
    val configuracion : ConfiguracionesDto,
    val tickets: List<TicketsDto>,
    val respuestas : List<RespuestaDto>
)
