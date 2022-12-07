package com.ucne.ticketsapp.data.repository

import com.ucne.ticketsapp.data.remote.api.TicketsApi
import com.ucne.ticketsapp.data.remote.dto.ClienteDto
import retrofit2.Response
import javax.inject.Inject

class ClientesRepository @Inject constructor(
    private val api: TicketsApi
) {
    suspend fun getClientes(): List<ClienteDto> {
        try {
            return api.getClientes()
        } catch (e: Exception) {
            throw e
        }
    }

    suspend fun postCliente(clienteDto: ClienteDto): Response<ClienteDto> {
        try {
            return api.postCliente(clienteDto)
        } catch (e: Exception) {
            throw e
        }
    }

    suspend fun getTop5ClientesRespondieron(id : Int): List<ClienteDto> {
        try {
            return api.getTop5ClientesRespondieron(id)
        } catch (e: Exception) {
            throw e
        }
    }

    suspend fun getTop5ClientesRespondidos(id : Int): List<ClienteDto> {
        try {
            return api.getTop5ClientesRespondidos(id)
        } catch (e: Exception) {
            throw e
        }
    }
    suspend fun getClienteById(id: Int): ClienteDto? {
        try {
            return api.getClienteById(id)
        } catch (e: Exception) {
            throw e
        }
    }

    suspend fun getCliente(nombre: String, clave: String): ClienteDto? {
        try {
            return api.getCliente(nombre, clave)
        } catch (_: Exception) {

        }
        return null
    }

    suspend fun putCliente(id: Int, newCliente: ClienteDto): Response<ClienteDto> {
        try {
            return api.putCliente(id, newCliente)
        } catch (e: Exception) {
            throw e
        }
    }

    suspend fun deleteCliente(id: Int): Response<ClienteDto> {
        try {
            return api.deleteClientes(id)
        } catch (e: Exception) {
            throw e
        }
    }
}