package com.ucne.ticketsapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ucne.ticketsapp.data.remote.dto.ClienteDto
import com.ucne.ticketsapp.data.remote.dto.ConfiguracionesDto
import com.ucne.ticketsapp.data.remote.dto.RespuestaDto
import com.ucne.ticketsapp.data.remote.dto.TicketsDto
import com.ucne.ticketsapp.data.repository.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ProfileUiState(
    val themeIndex: Int = 0,
    val colorIndex: Int = 0
)

data class TicketListUiState(
    val list: List<TicketsDto> = emptyList()
)

data class ClientesListUiState(
    val list: List<ClienteDto> = emptyList()
)


data class TicketUiState(
    val ticketId: Int = 0,
    val fechaCreacion: String = "",
    val clienteId: Int = 0,
    val sistemaId: Int = 0,
    val tipoId: Int = 0,
    val prioridadId: Int = 0,
    val estatusId: Int = 0,
    val especificaciones: String = "",
    val fechaFinalizado: String = "",
    val orden: Int = 0,

    val estatus: String = "",
    val prioridad: String = "",
    val sistema: String = "",
    val tipo: String = "",
)

data class ClienteUiState(
    val clienteId: Int = 0,
    val nombres: String = "",
    val configuracionId: Int = 0,
    val configuracion: ConfiguracionesDto? = null,
    val tickets: List<TicketsDto>? = null,
    val respuestas: List<RespuestaDto>? = null
)

@HiltViewModel
class MainViewModel @Inject constructor(
    private val clientRepository: ClientesRepository,
    private val ticketsRepository: TicketsRepository,
    private val sistemaRepository: SistemasRepository,
    private val prioridadesRepository: PrioridadesRepository,
    private val tipoRepository: TiposRepository,
    private val estatusRepository: EstatusRepository,
    private val configRepository: RemoteConfigRepository,
    private val LocalConfigRepository: LocalConfiguracionRepository,
) : ViewModel() {

    var profileUiState = MutableStateFlow(ProfileUiState())
        private set
    var clientUiState = MutableStateFlow(ClienteUiState())
        private set


    private val listTicketUiState = MutableStateFlow(TicketListUiState())
    val ticketListUiState: StateFlow<TicketListUiState> = listTicketUiState.asStateFlow()

    private val listClienteUiState = MutableStateFlow(ClientesListUiState())
    val clienteListUiState: StateFlow<ClientesListUiState> = listClienteUiState.asStateFlow()


    init {

        viewModelScope.launch {
            listTicketUiState.update {
                it.copy(list = ticketsRepository.getTickets())
            }
            listClienteUiState.update {
                it.copy(list = clientRepository.getClientes())
            }
        }
    }

    fun setCliente(user: ClienteDto) {
        clientUiState.value = clientUiState.value.copy(
            clienteId = user.clienteId,
            nombres = user.nombres,
            configuracionId = user.configuracionId,
            configuracion = user.configuracion,
            tickets = user.tickets,
            respuestas = user.respuestas
        )
    }


    fun setProfileTheme(index: Int) {
        profileUiState.value = profileUiState.value.copy(
            themeIndex = index
        )
    }

    fun setProfileColor(index: Int) {
        profileUiState.value = profileUiState.value.copy(
            colorIndex = index
        )
    }

    fun saveProfile() {
        viewModelScope.launch {
            val clienteCopiar = clientRepository.getClientesById(clientUiState.value.clienteId)
            if (clienteCopiar != null) {
                clientRepository.updateClientes(
                    id = clientUiState.value.clienteId,
                    clienteCopiar
                )
            }
        }
    }


    fun cleanCliente() {
        clientUiState.value = clientUiState.value.copy(
            clienteId = 0,
            nombres = "",
            configuracionId = 0,
            configuracion = null,
            tickets = null,
            respuestas = null
        )
    }

    fun cleanConfig() {
        profileUiState.value = profileUiState.value.copy(
            themeIndex = 0,
            colorIndex = 0
        )
    }
}