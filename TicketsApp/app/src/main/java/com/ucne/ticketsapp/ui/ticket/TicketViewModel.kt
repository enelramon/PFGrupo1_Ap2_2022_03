package com.ucne.ticketsapp.ui.ticket

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ucne.ticketsapp.data.remote.dto.*
import com.ucne.ticketsapp.data.repository.*
import com.ucne.ticketsapp.ui.ClienteUiState
import com.ucne.ticketsapp.ui.states.TicketUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject





data class SistemaListUiState(
    val list: List<SistemaDto> = emptyList()
)

data class PrioridadesListUiState(
    val list: List<PrioridadesDto> = emptyList()
)

data class TipoListUiState(
    val list: List<TiposDto> = emptyList()
)


@HiltViewModel
class TicketViewModel @Inject constructor(
    private val sistemaRepository: SistemasRepository,
    private val prioridadesRepository: PrioridadesRepository,
    private val tiposRepository: TiposRepository,
    private val clientesRepository: ClientesRepository,
    private val ticketsRepository: TicketsRepository
) : ViewModel() {

    private val listPrioridadesUiState = MutableStateFlow(PrioridadesListUiState())
    val prioridadesListUiState: StateFlow<PrioridadesListUiState> =
        listPrioridadesUiState.asStateFlow()

    private var clientUiState = MutableStateFlow(ClienteUiState())

    private val listSistemaUiState = MutableStateFlow(SistemaListUiState())
    val sistemaListUiState: StateFlow<SistemaListUiState> = listSistemaUiState.asStateFlow()

    private val listTipoUiState = MutableStateFlow(TipoListUiState())
    val tipoListUiState: StateFlow<TipoListUiState> = listTipoUiState.asStateFlow()

    var ticketsUiState = MutableStateFlow(TicketUiState())
        private set

    init {
        viewModelScope.launch {
            listPrioridadesUiState.update {
                it.copy(
                    list = prioridadesRepository.getPrioridades()
                )
            }
            listSistemaUiState.update {
                it.copy(
                    list = sistemaRepository.getSistemas()
                )
            }
            listTipoUiState.update {
                it.copy(
                    list = tiposRepository.getTipos()
                )
            }
        }
    }

    fun setSistema(id: Int) {
        viewModelScope.launch {
            sistemaRepository.getSistemasById(id)?.let {
                ticketsUiState.value = ticketsUiState.value.copy(
                    sistemaId = id,
                    sistema = it.sistema
                )
            }
        }
    }

    fun setTipo(id: Int) {
        viewModelScope.launch {
            tiposRepository.getTiposById(id)?.let {
                ticketsUiState.value = ticketsUiState.value.copy(
                    tipoId = id,
                    tipo = it.tipo
                )
            }
        }
    }

    fun setPrioridad(id: Int) {
        viewModelScope.launch {
            prioridadesRepository.getPrioridadesById(id)?.let {
                ticketsUiState.value = ticketsUiState.value.copy(
                    prioridadId = id,
                    prioridad = it.prioridad
                )
            }
        }
    }

    fun setDate(it: String) {
        ticketsUiState.value = ticketsUiState.value.copy(
            fecha = it
        )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun save() {

        viewModelScope.launch {
            if (ticketsUiState.value.id > 0) {
                val lastTicket = ticketsRepository.getTicketsById(ticketsUiState.value.id)
                ticketsRepository.updateTickets(
                    ticketsUiState.value.id,
                    TicketsDto(
                        ticketId = ticketsUiState.value.id,
                        tipoId = ticketsUiState.value.tipoId,
                        prioridadId = ticketsUiState.value.prioridadId,
                        estatusId = 1,
                        sistemaId = ticketsUiState.value.sistemaId,
                        fechaCreacion = ticketsUiState.value.fecha,
                        fechaFinalizado = ticketsUiState.value.fecha,
                        orden = 1,
                        clienteId = ticketsUiState.value.clienteId,
                        especificaciones = ticketsUiState.value.especificaciones,
                        respuestas = lastTicket!!.respuestas,
                    )
                )
            }
            else
            {
                ticketsRepository.postTickets(
                    TicketsDto(
                        prioridadId = ticketsUiState.value.prioridadId,
                        estatusId = 1,
                        tipoId = ticketsUiState.value.tipoId,
                        sistemaId = ticketsUiState.value.sistemaId,
                        fechaCreacion = LocalDate.now().toString(),
                        fechaFinalizado = LocalDate.now().toString(),
                        orden = 1,
                        clienteId = ticketsUiState.value.clienteId,
                        especificaciones = ticketsUiState.value.especificaciones
                    )
                )
            }
            clean()
        }
    }

    private fun clean() {
        ticketsUiState.value = ticketsUiState.value.copy(
            id = 0,
            clienteId = 0,
            sistemaId = 0,
            sistema = "",
            prioridadId = 0,
            prioridad = "",
            estatusId = 0,
            tipoId = 0,
            tipo = "",
            fecha = "",
            especificaciones = ""
        )
    }

    fun setEspecificaciones(it: String) {
        ticketsUiState.value = ticketsUiState.value.copy(
            especificaciones = it
        )
    }

    fun setCliente(user: ClienteDto) {
        viewModelScope.launch {
            clientesRepository.getClientesById(user.clienteId)?.let {
                clientUiState.value = clientUiState.value.copy(
                    clienteId = it.clienteId,
                    nombres = it.nombres,
                    configuracionId = it.configuracionId,
                    tickets = it.tickets,
                    respuestas = it.respuestas
                )
                ticketsUiState.value = ticketsUiState.value.copy(
                    clienteId = it.clienteId
                )
            }
        }

    }
}