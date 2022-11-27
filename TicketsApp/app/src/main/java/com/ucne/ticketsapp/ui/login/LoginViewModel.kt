@file:Suppress("ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE")

package com.ucne.ticketsapp.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ucne.ticketsapp.data.local.entities.User
import com.ucne.ticketsapp.data.remote.dto.ClienteDto
import com.ucne.ticketsapp.data.repository.ClientesRepository
import com.ucne.ticketsapp.data.repository.UserRepository
import com.ucne.ticketsapp.util.stringEncrypter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class LoginUiState(
    val nombre: String = "",
    val clave: String = "",
    val adminMode: Boolean = false,
    val recuerdame: Boolean = false,
    val funcionLogin: ((ClienteDto, Boolean) -> Unit)? = null
)

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val clienteRepository: ClientesRepository
) : ViewModel() {

    var uiState = MutableStateFlow(LoginUiState())
        private set
    private var user: ClienteDto? = null

    init {

        viewModelScope.launch {
            val usuarioLocal = userRepository.getUser()

            if (usuarioLocal != null) {
                setClave(usuarioLocal.password)
                setNombre(usuarioLocal.username)
                rememberMeChanged()

                user = clienteRepository.getCliente(
                    stringEncrypter(uiState.value.nombre),
                    stringEncrypter(uiState.value.clave)
                )

                if (user != null) {
                    uiState.value.funcionLogin?.let { it(user!!, uiState.value.adminMode) }
                    clean()
                }
            }
        }
    }

    fun login(): ClienteDto? {
        viewModelScope.launch {
            user = clienteRepository.getCliente(
                nombre = stringEncrypter(uiState.value.nombre),
                clave = stringEncrypter(uiState.value.clave)
            )

            if (user != null && uiState.value.recuerdame) {
                userRepository.insert(
                    User(
                        id = 0,
                        username = uiState.value.nombre,
                        password = uiState.value.clave,
                        adminMode = uiState.value.adminMode
                    )
                )
                clean()
            } else if (user != null && !uiState.value.recuerdame) {
                userRepository.delete()
                clean()
            }

        }

        return user
    }

    private fun clean() {
        setNombre("")
        setClave("")
    }

    fun setNombre(newValue: String) {
        uiState.value = uiState.value.copy(nombre = newValue)
    }

    fun setClave(newValue: String) {
        uiState.value = uiState.value.copy(clave = newValue)
    }

    fun setLoginFunction(funcion: ((ClienteDto, Boolean) -> Unit)) {
        uiState.value = uiState.value.copy(funcionLogin = funcion)
    }

    fun rememberMeChanged() {
        uiState.value = uiState.value.copy(recuerdame = !uiState.value.recuerdame)
    }

    fun setAdminMode() {
        uiState.value = uiState.value.copy(adminMode = true)
    }
}