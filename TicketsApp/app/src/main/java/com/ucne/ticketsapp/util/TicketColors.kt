package com.ucne.ticketsapp.util

import androidx.compose.ui.graphics.Color
import com.ucne.ticketsapp.data.remote.dto.TicketsDto

data class TicketColors(
    val tipoColor: Color,
    val prioridadColor: Color,
    val estatusColor: Color
)

fun getTicketColors(ticket: TicketsDto): TicketColors {

    val tipoColor = when (ticket.tipoId) {
        1 -> Color(0xFFEB3737)// Error
        2 -> Color(0xFF6AE751) //Sugerencia
        3 -> Color(0xFFE7E551) //Aviso
        else -> Color(0xFF03A9F4) //Otros
    }
    val prioridadColor: Color = when (ticket.prioridadId) {
        1 -> Color(0xFF03A9F4)//Baja
        2 -> Color(0xFF6AE751)//Normal
        3 -> Color(0xFFDBEB37)//Media
        4 -> Color(0xFFFAA22B)//Alta
        else -> Color(0xFFEB3737)//Emergencia
    }
    val estatusColor: Color = when (ticket.estatusId) {
        1 -> Color(0xFF8DC981)//Nuevo
        2 -> Color(0xFFC9CF8B)//En Proceso
        3 -> Color(0xFF8B8B8B)//Cerrado
        else -> Color(0xFF81CDF0) //Respondido
    }

    return TicketColors(
        tipoColor = tipoColor,
        prioridadColor = prioridadColor,
        estatusColor = estatusColor
    )
}