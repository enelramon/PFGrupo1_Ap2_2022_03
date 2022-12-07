package com.ucne.ticketsapp.data.remote.dto

data class RespuestaDto(
    val respuestaId : Int = 0,
    val respuesta : String,
    val clienteId : Int,
    val fecha : String,
    val ticketId: Int,
)
