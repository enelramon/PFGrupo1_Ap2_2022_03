package com.ucne.ticketsapp.util

import com.ucne.ticketsapp.data.remote.dto.TicketsDto
import com.ucne.ticketsapp.ui.states.TicketUiState

fun TicketsDto.toPackage() : TicketUiState {
        return TicketUiState(
            id= this.ticketId,
            clienteId = this.clienteId,
            sistemaId = this.sistemaId,
            sistema = "",
            prioridadId = this.prioridadId,
            prioridad = "",
            orden = this.orden,
            estatusId = this.estatusId,
            tipoId = this.tipoId,
            tipo = "",
            fecha = this.fechaCreacion,
            fechaCerrado = this.fechaFinalizado,
            especificaciones = this.especificaciones
        )
    }