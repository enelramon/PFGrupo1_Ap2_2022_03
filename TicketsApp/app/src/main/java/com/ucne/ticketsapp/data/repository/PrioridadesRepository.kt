package com.ucne.ticketsapp.data.repository

import com.ucne.ticketsapp.data.domain.Resource
import com.ucne.ticketsapp.data.remote.api.TicketsApi
import com.ucne.ticketsapp.data.remote.dto.ClienteDto
import com.ucne.ticketsapp.data.remote.dto.PrioridadesDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class PrioridadesRepository @Inject constructor(
    private val api: TicketsApi
) {
    fun getPrioridades(): Flow<Resource<List<PrioridadesDto>>> = flow {

        try {

            emit(Resource.Loading())

            val getAll = api.getPrioridades()

            emit(Resource.Success(getAll))

        } catch ( e: HttpException){

            emit(Resource.Error(e.message() ?: "HTTP SERVER ERROR, TIMEOUT, TRY AGAIN"))

        } catch (e: IOException) {

            emit(Resource.Error(e.message ?: "There may be a problem with your Connection, Please check your internet"))

        }
    }

    suspend fun postEstatus(prioridadesDto: PrioridadesDto): Response<PrioridadesDto> {
        try {
            return api.postPrioridades(prioridadesDto)
        } catch (e: Exception) {
            throw e
        }
    }

    suspend fun getPrioridadesById(id: Int): PrioridadesDto? {
        try {
            return api.getPrioridadesById(id)
        } catch (e: Exception) {
            throw e
        }
    }

    //PUT
    suspend fun updatePrioridades(id: Int, newPrioridades: PrioridadesDto): Response<PrioridadesDto> {
        try {
            return api.updatePrioridades(id, newPrioridades)
        } catch (e: Exception) {
            throw e
        }
    }

    suspend fun deletePrioridades(id: Int): Response<PrioridadesDto> {
        try {
            return api.deletePrioridades(id)
        } catch (e: Exception) {
            throw e
        }
    }
}