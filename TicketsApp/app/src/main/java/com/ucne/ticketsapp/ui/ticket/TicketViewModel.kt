package com.ucne.ticketsapp.ui.ticket

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ucne.ticketsapp.data.remote.dto.PrioridadesDto
import com.ucne.ticketsapp.data.remote.dto.SistemaDto
import com.ucne.ticketsapp.data.remote.dto.TicketsDto
import com.ucne.ticketsapp.data.remote.dto.TiposDto
import com.ucne.ticketsapp.data.repository.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

data class TicketUiState(
    val id: Int = 0,
    val clienteId: Int = 0,
    val sistemaId: Int = 0,
    val sistema: String = "",
    val prioridadId: Int = 0,
    val prioridad: String = "",
    val estatusId: Int = 0,
    val tipoId: Int = 0,
    val tipo: String = "",
    val fecha: String = "",
    val especificaciones: String = ""
)


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
    private val estatusRepository: EstatusRepository,
    private val ticketsRepository: TicketsRepository
) : ViewModel() {

    private val listPrioridadesUiState = MutableStateFlow(PrioridadesListUiState())
    val prioridadesListUiState: StateFlow<PrioridadesListUiState> =
        listPrioridadesUiState.asStateFlow()

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
        ticketsUiState.value = ticketsUiState.value.copy(
            sistema = ""
        )
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
        ticketsUiState.value = ticketsUiState.value.copy(
            tipo = ""
        )
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
        ticketsUiState.value = ticketsUiState.value.copy(
            prioridad = ""
        )
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
            val tipoSelected = tiposRepository.getTiposById(ticketsUiState.value.tipoId)
            val prioridadSelected =
                prioridadesRepository.getPrioridadesById(ticketsUiState.value.prioridadId)
            val sistemaSelected = sistemaRepository.getSistemasById(ticketsUiState.value.sistemaId)

            val clienteSelected = clientesRepository.getClientesById(ticketsUiState.value.clienteId)
            val formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy")

            if (ticketsUiState.value.id == 0) {
                val estatusSelected = estatusRepository.getEstatusById(1)
                ticketsRepository.postTickets(
                    TicketsDto(
                        tipoId = ticketsUiState.value.tipoId,
                        tipo = tipoSelected!!,
                        prioridadId = ticketsUiState.value.prioridadId,
                        prioridad = prioridadSelected!!,
                        estatusId = 1,
                        estatus = estatusSelected!!,
                        sistemaId = ticketsUiState.value.sistemaId,
                        sistema = sistemaSelected!!,
                        fechaCreacion = LocalDate.now().format(formatter),
                        fechaFinalizado = "",
                        orden = 1,
                        clienteId = ticketsUiState.value.clienteId,
                        especificaciones = ticketsUiState.value.especificaciones,
                        respuestas = emptyList(),
                        cliente = clienteSelected!!,
                    )
                )
            } else {
                val lastTicket = ticketsRepository.getTicketsById(ticketsUiState.value.id)
                val estatusSelected =
                    estatusRepository.getEstatusById(ticketsUiState.value.estatusId)
                ticketsRepository.updateTickets(
                    ticketsUiState.value.id,
                    TicketsDto(
                        ticketId = ticketsUiState.value.id,
                        tipoId = ticketsUiState.value.tipoId,
                        tipo = tipoSelected!!,
                        prioridadId = ticketsUiState.value.prioridadId,
                        prioridad = prioridadSelected!!,
                        estatusId = 1,
                        estatus = estatusSelected!!,
                        sistemaId = ticketsUiState.value.sistemaId,
                        sistema = sistemaSelected!!,
                        fechaCreacion = LocalDate.now().format(formatter),
                        fechaFinalizado = "",
                        orden = 1,
                        clienteId = ticketsUiState.value.clienteId,
                        especificaciones = ticketsUiState.value.especificaciones,
                        respuestas = lastTicket!!.respuestas,
                        cliente = clienteSelected!!,
                    )
                )
            }

        }
        clean()
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

    fun setCliente(clienteId: Int) {
        ticketsUiState.value = ticketsUiState.value.copy(
            clienteId = clienteId
        )
    }
}