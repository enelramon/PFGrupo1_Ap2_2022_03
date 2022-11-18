package com.ucne.ticketsapp.data.remote.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EstatusDto(
    val estatusId : Int,
    val estatus1 : String,
    val eTickets: List<TicketsDto>
)
