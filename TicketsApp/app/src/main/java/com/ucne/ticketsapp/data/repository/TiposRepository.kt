package com.ucne.ticketsapp.data.repository

import com.ucne.ticketsapp.data.domain.Resource
import com.ucne.ticketsapp.data.remote.api.TicketsApi
import com.ucne.ticketsapp.data.remote.dto.ClienteDto
import com.ucne.ticketsapp.data.remote.dto.TiposDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class TiposRepository @Inject constructor(
    private val api: TicketsApi
) {
    fun getTipos(): Flow<Resource<List<TiposDto>>> = flow {

        try {

            emit(Resource.Loading())

            val getAll = api.getTipos()

            emit(Resource.Success(getAll))

        } catch ( e: HttpException){

            emit(Resource.Error(e.message() ?: "HTTP SERVER ERROR, TIMEOUT, TRY AGAIN"))

        } catch (e: IOException) {

            emit(Resource.Error(e.message ?: "There may be a problem with your Connection, Please check your internet"))

        }
    }

    suspend fun postTipos(tiposDto: TiposDto): Response<TiposDto> {
        try {
            return api.postTipos(tiposDto)
        } catch (e: Exception) {
            throw e
        }
    }

    suspend fun getTiposById(id: Int): TiposDto? {
        try {
            return api.getTiposById(id)
        } catch (e: Exception) {
            throw e
        }
    }

    //PUT
    suspend fun updateTipos(id: Int, newTipos: TiposDto): Response<TiposDto> {
        try {
            return api.updateTipos(id, newTipos)
        } catch (e: Exception) {
            throw e
        }
    }

    suspend fun deleteTipos(id: Int): Response<TiposDto> {
        try {
            return api.deleteTipos(id)
        } catch (e: Exception) {
            throw e
        }
    }
}