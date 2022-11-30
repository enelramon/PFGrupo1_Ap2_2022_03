package com.ucne.ticketsapp.ui.states

data class TicketUiState(
    val id: Int = 0,
    val orden : Int = 0,
    val clienteId: Int = 0,
    val cliente: String = "",
    val tipoId: Int = 0,
    val tipo: String = "",
    val sistemaId: Int = 0,
    val sistema: String = "",
    val prioridadId: Int = 0,
    val prioridad: String = "",
    val estatusId: Int = 0,
    val estatus: String = "",
    val fecha: String = "",
    val fechaCerrado : String = "",
    val especificaciones: String = ""
)

