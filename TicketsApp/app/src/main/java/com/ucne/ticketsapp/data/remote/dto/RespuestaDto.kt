package com.ucne.ticketsapp.data.remote.dto

data class RespuestaDto(
    val respuestaId : Int,
    val respuesta : String,
    val clienteId : Int,
    val cliente : ClienteDto,
    val fecha : String,
    val ticketId: Int,
    val ticket: TicketsDto
)
