package com.ucne.ticketsapp.data.repository


import com.ucne.ticketsapp.data.domain.Resource
import com.ucne.ticketsapp.data.remote.api.TicketsApi
import com.ucne.ticketsapp.data.remote.dto.ClienteDto
import com.ucne.ticketsapp.data.remote.dto.SistemaDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class SistemasRepository @Inject constructor(
    private val api: TicketsApi
) {
    fun getSistemas(): Flow<Resource<List<SistemaDto>>> = flow {

        try {

            emit(Resource.Loading())

            val getAll = api.getSistemas()

            emit(Resource.Success(getAll))

        } catch ( e: HttpException){

            emit(Resource.Error(e.message() ?: "HTTP SERVER ERROR, TIMEOUT, TRY AGAIN"))

        } catch (e: IOException) {

            emit(Resource.Error(e.message ?: "There may be a problem with your Connection, Please check your internet"))

        }
    }

    suspend fun postSistemas(sistemaDto: SistemaDto): Response<SistemaDto> {
        try {
            return api.postSistemas(sistemaDto)
        } catch (e: Exception) {
            throw e
        }
    }

    suspend fun getSistemasById(id: Int): SistemaDto? {
        try {
            return api.getSistemasById(id)
        } catch (e: Exception) {
            throw e
        }
    }

    //PUT
    suspend fun updateSistemas(id: Int, newSistema: SistemaDto): Response<SistemaDto> {
        try {
            return api.updateSistemas(id, newSistema)
        } catch (e: Exception) {
            throw e
        }
    }

    suspend fun deleteSistemas(id: Int): Response<SistemaDto> {
        try {
            return api.deleteSistemas(id)
        } catch (e: Exception) {
            throw e
        }
    }
}