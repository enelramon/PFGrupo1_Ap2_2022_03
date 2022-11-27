package com.ucne.ticketsapp.ui.ticket

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ucne.ticketsapp.data.remote.dto.PrioridadesDto
import com.ucne.ticketsapp.data.remote.dto.SistemaDto
import com.ucne.ticketsapp.data.remote.dto.TiposDto
import com.ucne.ticketsapp.data.repository.PrioridadesRepository
import com.ucne.ticketsapp.data.repository.SistemasRepository
import com.ucne.ticketsapp.data.repository.TiposRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class TicketUiState(
    val id: Int = 0,
    val sistemaId: Int = 0,
    val sistema: String = "",
    val prioridadId: Int = 0,
    val prioridad: String = "",
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
    private val tiposRepository: TiposRepository
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
            sistemaRepository.getSistemasById(id)?.let {
                ticketsUiState.value = ticketsUiState.value.copy(
                    sistemaId = id,
                    sistema = it.sistema
                )
            }
        }
    }

    fun setDate(it: String) {
        ticketsUiState.value = ticketsUiState.value.copy(
            fecha = it
        )
    }

    fun setEspecificaciones(it: String) {
        ticketsUiState.value = ticketsUiState.value.copy(
            especificaciones = it
        )
    }
}