package com.ucne.ticketsapp.ui.ticket

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ucne.ticketsapp.data.repository.*
import com.ucne.ticketsapp.ui.states.TicketListUiState
import com.ucne.ticketsapp.ui.states.TicketPackageListUiState
import com.ucne.ticketsapp.ui.states.TicketUiState
import com.ucne.ticketsapp.util.toPackageList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TicketListViewModel @Inject constructor(
    private val ticketsRepository: TicketsRepository,
    private val clientesRepository: ClientesRepository,
    private val tiposRepository: TiposRepository,
    private val estatusRepository: EstatusRepository,
    private val prioridadesRepository: PrioridadesRepository,
    private val sistemasRepository: SistemasRepository
) : ViewModel() {

    private val listTicketUiState = MutableStateFlow(TicketListUiState())
    val ticketListUiState: StateFlow<TicketListUiState> = listTicketUiState.asStateFlow()

    private val listTicketPackageUiState = MutableStateFlow(TicketPackageListUiState())
    val ticketPackageListUiState: StateFlow<TicketPackageListUiState> =
        listTicketPackageUiState.asStateFlow()

    fun refresh() {
        viewModelScope.launch {
            listTicketUiState.update {
                it.copy(
                    list = ticketsRepository.getTickets()
                )
            }
            listTicketPackageUiState.update {
                it.copy(
                    // extension declared in  util directory
                    list = listTicketUiState.value.list.toPackageList()
                )
            }
            upgradeTicketPackage()
        }
    }

    init {
        refresh()
    }

    private fun upgradeTicketPackage() {
        viewModelScope.launch {
            val lista = listTicketPackageUiState.value.list
            val listAux: MutableList<TicketUiState> = mutableListOf()

            lista.forEach { ticket ->
                listAux.add (
                    TicketUiState(
                        id = ticket.id,
                        orden = ticket.orden,
                        clienteId = ticket.clienteId,
                        cliente = clientesRepository.getClientesById(ticket.clienteId)?.nombres ?: "cliente no encontrado",
                        tipoId = ticket.tipoId,
                        tipo = tiposRepository.getTiposById(ticket.tipoId)?.tipo ?: "tipo no encontrado",
                        sistemaId = ticket.sistemaId,
                        sistema = sistemasRepository.getSistemasById(ticket.sistemaId)?.sistema ?: "sistema no encontrado",
                        prioridadId = ticket.prioridadId,
                        prioridad = prioridadesRepository.getPrioridadesById(ticket.prioridadId)?.prioridad ?: "prioridad no encontrada",
                        estatusId = ticket.estatusId,
                        estatus = estatusRepository.getEstatusById(ticket.estatusId)?.estatus1 ?: "estatus no encontrado",
                        fecha = ticket.fecha,
                        fechaCerrado = ticket.fechaCerrado,
                        especificaciones = ticket.especificaciones
                    )
                )
            }
            listTicketPackageUiState.value = listTicketPackageUiState.value.copy(
                list = listAux
            )
        }

    }
}