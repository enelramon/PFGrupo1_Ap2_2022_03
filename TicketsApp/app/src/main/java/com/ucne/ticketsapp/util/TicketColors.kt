package com.ucne.ticketsapp.util

import androidx.compose.ui.graphics.Color

data class TicketColors(
    val tipoColor: Color,
    val prioridadColor: Color,
    val estatusColor: Color
)

fun getTicketColors(tipoId: Int, prioridadId: Int, estatusId: Int): TicketColors {
    val tipoColor = when (tipoId) {
        1 -> Color(0xFFEC2121)// Error
        2 -> Color(0xFF35F00F) //Sugerencia
        3 -> Color(0xFFFFC107) //Aviso
        else -> Color(0xFF2196F3) //Otros
    }
    val prioridadColor: Color = when (prioridadId) {
        1 -> Color(0xFF03A9F4)//Baja
        2 -> Color(0xFF40DB21)//Normal
        3 -> Color(0xFFECFF26)//Media
        4 -> Color(0xFFE48C15)//Alta
        else -> Color(0xFFDF1818)//Emergencia
    }
    val estatusColor: Color = when (estatusId) {
        1 -> Color(0xFF34E710)//Nuevo
        2 -> Color(0xFFFFD800)//En Proceso
        3 -> Color(0xFF666666)//Cerrado
        else -> Color(0xFF0EA5EC) //Modificado
    }
    return TicketColors(
        tipoColor = tipoColor,
        prioridadColor = prioridadColor,
        estatusColor = estatusColor
    )
}


