package com.ucne.ticketsapp.data.remote.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ClienteDto(
    val clienteId : Int,
    val nombres : String,
    val tickets : List<TicketsDto>,
    )
