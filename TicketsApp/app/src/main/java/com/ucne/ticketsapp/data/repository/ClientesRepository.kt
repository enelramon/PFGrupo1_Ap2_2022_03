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

    suspend fun postClientes(clienteDto: ClienteDto): Response<ClienteDto> {
        try {
            return api.postClientes(clienteDto)
        } catch (e: Exception) {
            throw e
        }
    }

    suspend fun getClientesById(id: Int): ClienteDto? {
        try {
            return api.getClientesById(id)
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

    //PUT
    suspend fun updateClientes(id: Int, newCliente: ClienteDto): Response<ClienteDto> {
        try {
            return api.updateClientes(id, newCliente)
        } catch (e: Exception) {
            throw e
        }
    }

    suspend fun deleteClientes(id: Int): Response<ClienteDto> {
        try {
            return api.deleteClientes(id)
        } catch (e: Exception) {
            throw e
        }
    }
}