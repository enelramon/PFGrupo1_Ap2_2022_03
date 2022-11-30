package com.ucne.ticketsapp.data.repository

import com.ucne.ticketsapp.data.domain.Resource
import com.ucne.ticketsapp.data.remote.api.TicketsApi
import com.ucne.ticketsapp.data.remote.dto.ClienteDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class ClientesRepository @Inject constructor(
    private val api: TicketsApi
) {

    fun getClientes(): Flow<Resource<List<ClienteDto>>> = flow {

        try {

            emit(Resource.Loading())

            val getAll = api.getClientes()

            emit(Resource.Success(getAll))

        } catch ( e: HttpException){

            emit(Resource.Error(e.message() ?: "HTTP SERVER ERROR, TIMEOUT, TRY AGAIN"))

        } catch (e: IOException) {

            emit(Resource.Error(e.message ?: "There may be a problem with your Connection, Please check your internet"))

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