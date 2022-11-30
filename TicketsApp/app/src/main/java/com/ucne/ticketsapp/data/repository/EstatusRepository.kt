package com.ucne.ticketsapp.data.repository

import com.ucne.ticketsapp.data.domain.Resource
import com.ucne.ticketsapp.data.remote.api.TicketsApi
import com.ucne.ticketsapp.data.remote.dto.ClienteDto
import com.ucne.ticketsapp.data.remote.dto.EstatusDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class EstatusRepository @Inject constructor(
    private val api: TicketsApi
) {
    fun getEstatus(): Flow<Resource<List<EstatusDto>>> = flow {

        try {

            emit(Resource.Loading())

            val getAll = api.getEstatus()

            emit(Resource.Success(getAll))

        } catch ( e: HttpException){

            emit(Resource.Error(e.message() ?: "HTTP SERVER ERROR, TIMEOUT, TRY AGAIN"))

        } catch (e: IOException) {

            emit(Resource.Error(e.message ?: "There may be a problem with your Connection, Please check your internet"))

        }
    }

    suspend fun postEstatus(estatusDto: EstatusDto): Response<EstatusDto> {
        try {
            return api.postEstatus(estatusDto)
        } catch (e: Exception) {
            throw e
        }
    }

    suspend fun getEstatusById(id: Int): EstatusDto? {
        try {
            return api.getEstatusById(id)
        } catch (e: Exception) {
            throw e
        }
    }

    //PUT
    suspend fun updateEstatus(id: Int, newEstatus:  EstatusDto): Response<EstatusDto> {
        try {
            return api.updateEstatus(id, newEstatus)
        } catch (e: Exception) {
            throw e
        }
    }

    suspend fun deleteEstatus(id: Int): Response<EstatusDto> {
        try {
            return api.deleteEstatus(id)
        } catch (e: Exception) {
            throw e
        }
    }
}