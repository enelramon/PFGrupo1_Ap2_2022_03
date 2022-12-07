package com.ucne.ticketsapp.data.remote.dto

import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class TicketsDto(
    val ticketId : Int = 0,
    val fechaCreacion: String,
    val clienteId: Int,
    val sistemaId: Int,
    val tipoId: Int,
    val prioridadId: Int,
    val estatusId: Int,
    val especificaciones: String,
    val fechaFinalizado: String,
    val orden: Int,
    val respuestas: List<RespuestaDto> = emptyList(),
)
