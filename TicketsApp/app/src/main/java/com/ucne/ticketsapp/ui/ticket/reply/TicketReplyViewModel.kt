package com.ucne.ticketsapp.ui.ticket.reply

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ucne.ticketsapp.data.remote.dto.RespuestaDto
import com.ucne.ticketsapp.data.repository.*
import com.ucne.ticketsapp.ui.states.TicketUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject


data class RespuestasUiState(
    val respuestas: List<ReplyUiState> = emptyList()
)

data class ReplyUiState(
    val fecha: String = "",
    val contenido: String = "",
    val autor: String = ""
)

@HiltViewModel
class TicketReplyViewModel @Inject constructor(
    private val ticketsRepository: TicketsRepository,
    private val clientesRepository: ClientesRepository,
    private val repository: RespuestaRepository,
    private val tiposRepository: TiposRepository,
    private val sistemasRepository: SistemasRepository,
    private val prioridadesRepository: PrioridadesRepository,
    private val estatusRepository: EstatusRepository,
) : ViewModel() {
    var uiState = MutableStateFlow(TicketUiState())
        private set

    private val listRepliesUiState = MutableStateFlow(RespuestasUiState())
    val listUiState = listRepliesUiState.asStateFlow()
    var respuesta by mutableStateOf("")

    fun findTicketById(ticketId: Int) {
        viewModelScope.launch {
            ticketsRepository.getTicketsById(ticketId)?.let {
                uiState.value = uiState.value.copy(
                    id = it.ticketId,
                    orden = it.orden,
                    clienteId = it.clienteId,
                    cliente = clientesRepository.getClienteById(it.clienteId)?.nombres
                        ?: "No encontrado",
                    tipoId = it.tipoId,
                    tipo = tiposRepository.getTiposById(it.tipoId)?.tipo ?: "No encontrado",
                    sistemaId = it.sistemaId,
                    sistema = sistemasRepository.getSistemasById(it.sistemaId)?.sistema
                        ?: "No encontrado",
                    prioridadId = it.prioridadId,
                    prioridad = prioridadesRepository.getPrioridadesById(it.prioridadId)?.prioridad
                        ?: "No encontrado",
                    estatusId = it.estatusId,
                    estatus = estatusRepository.getEstatusById(it.estatusId)?.estatus1
                        ?: "No encontrado",
                    fecha = it.fechaCreacion,
                    fechaCerrado = it.fechaFinalizado,
                    especificaciones = it.especificaciones
                )
                refreshAnswers()
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun addAnswer(ticketId: Int, clienteId: Int) {
        viewModelScope.launch {
            repository.postRespuesta(
                RespuestaDto(
                    respuesta = respuesta,
                    clienteId = clienteId,
                    fecha = LocalDate.now().toString(),
                    ticketId = ticketId,
                )
            )
        }
    }

    fun refreshAnswers() {
        val lista  : MutableList<ReplyUiState> = mutableListOf()
        var autor by mutableStateOf("")
        viewModelScope.launch {
            repository.getRespuestas().filter { r -> r.ticketId == uiState.value.id }.forEach {
                autor = clientesRepository.getClienteById(it.clienteId)?.nombres ?: ""
                lista.add(ReplyUiState(fecha = it.fecha, contenido = it.respuesta, autor = autor))
            }
            listRepliesUiState.update {
                it.copy(
                    respuestas = lista
                )
            }
        }
    }
}


