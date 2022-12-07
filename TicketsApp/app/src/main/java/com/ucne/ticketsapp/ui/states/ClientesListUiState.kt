package com.ucne.ticketsapp.ui.states

import com.ucne.ticketsapp.data.remote.dto.ClienteDto

data class ClientesListUiState(
    val list: List<ClienteDto> = emptyList()
)