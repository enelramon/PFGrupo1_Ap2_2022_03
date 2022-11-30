package com.ucne.ticketsapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ucne.ticketsapp.data.remote.dto.ClienteDto
import com.ucne.ticketsapp.data.remote.dto.ConfiguracionesDto
import com.ucne.ticketsapp.data.remote.dto.RespuestaDto
import com.ucne.ticketsapp.data.remote.dto.TicketsDto
import com.ucne.ticketsapp.data.repository.ClientesRepository
import com.ucne.ticketsapp.data.repository.RemoteConfigRepository
import com.ucne.ticketsapp.data.repository.SistemasRepository
import com.ucne.ticketsapp.data.repository.TicketsRepository
import com.ucne.ticketsapp.ui.states.ClientesListUiState
import com.ucne.ticketsapp.ui.states.TicketListUiState
import com.ucne.ticketsapp.util.UserConfig
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


data class ConfigUiState(
    val currentConfig: UserConfig = UserConfig()
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
    private val configRepository: RemoteConfigRepository,
) : ViewModel() {

    var profileUiState = MutableStateFlow(ProfileUiState())
        private set
    var clientUiState = MutableStateFlow(ClienteUiState())
        private set

    var configUiState = MutableStateFlow(ConfigUiState())
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
        viewModelScope.launch {
            clientRepository.getClientesById(user.clienteId)?.let {
                clientUiState.value = clientUiState.value.copy(
                    clienteId = it.clienteId,
                    nombres = it.nombres,
                    configuracionId = it.configuracionId,
                    tickets = it.tickets,
                    respuestas = it.respuestas
                )
            }

            val config = configRepository.getConfig(clientUiState.value.configuracionId)
            if (config != null) {
                configUiState.value = configUiState.value.copy(
                    currentConfig = UserConfig(config.theme, config.colorSchemeIndex)
                )
                setProfileTheme(config.theme)
                setProfileColor(config.colorSchemeIndex)
            }
        }

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
            val config = configRepository.getConfigByConfig(
                profileUiState.value.themeIndex,
                profileUiState.value.colorIndex
            )
            if (config != null) {
                configUiState.value = configUiState.value.copy(
                    currentConfig = UserConfig(config.theme, config.colorSchemeIndex)
                )
            }
            if (clienteCopiar != null) {
                clientRepository.updateClientes(
                    id = clientUiState.value.clienteId,
                    ClienteDto(
                        clienteId = clienteCopiar.clienteId,
                        nombres = clienteCopiar.nombres,
                        clave = clienteCopiar.clave,
                        configuracionId = config!!.configuracionId,
                        tickets = clienteCopiar.tickets,
                        respuestas = clienteCopiar.respuestas
                    )
                )
            }
        }
    }
}