package com.ucne.ticketsapp.util

import com.ucne.ticketsapp.data.remote.dto.TicketsDto
import com.ucne.ticketsapp.ui.states.TicketUiState

fun List<TicketsDto>.toPackageList(): List<TicketUiState> {
    val list : MutableList<TicketUiState> = mutableListOf()

    this.forEach {dto ->
        list.add(dto.toPackage())
    }
    return list
}