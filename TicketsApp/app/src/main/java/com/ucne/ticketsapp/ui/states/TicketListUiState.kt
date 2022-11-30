package com.ucne.ticketsapp.ui.states

import com.ucne.ticketsapp.data.remote.dto.TicketsDto


data class TicketListUiState(
    val list: List<TicketsDto> = emptyList()
)


data class TicketPackageListUiState(
    val list: List<TicketUiState> = emptyList()
)

