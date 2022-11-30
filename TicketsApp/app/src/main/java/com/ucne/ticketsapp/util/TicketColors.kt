package com.ucne.ticketsapp.util

import androidx.compose.ui.graphics.Color
import com.ucne.ticketsapp.ui.states.TicketUiState

data class TicketColors(
    val tipoColor: Color,
    val prioridadColor: Color,
    val estatusColor: Color
)

fun getTicketColors(ticket: TicketUiState): TicketColors {

    val tipoColor = when (ticket.tipoId) {
        1 -> Color(0xFFEB3737)// Error
        2 -> Color(0xFF6AE751) //Sugerencia
        3 -> Color(0xFFFFFC31) //Aviso
        else -> Color(0xFF03A9F4) //Otros
    }
    val prioridadColor: Color = when (ticket.prioridadId) {
        1 -> Color(0xFF03A9F4)//Baja
        2 -> Color(0xFF6AE751)//Normal
        3 -> Color(0xFFECFF26)//Media
        4 -> Color(0xFFFAA22B)//Alta
        else -> Color(0xFFEB3737)//Emergencia
    }
    val estatusColor: Color = when (ticket.estatusId) {
        1 -> Color(0xFF60E745)//Nuevo
        2 -> Color(0xFFEDFF3E)//En Proceso
        3 -> Color(0xFF666666)//Cerrado
        else -> Color(0xFF42C2FF) //Respondido
    }

    return TicketColors(
        tipoColor = tipoColor,
        prioridadColor = prioridadColor,
        estatusColor = estatusColor
    )
}