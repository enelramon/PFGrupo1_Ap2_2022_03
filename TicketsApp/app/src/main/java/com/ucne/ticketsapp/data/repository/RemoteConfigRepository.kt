package com.ucne.ticketsapp.data.repository

import com.ucne.ticketsapp.data.domain.Resource
import com.ucne.ticketsapp.data.remote.api.TicketsApi
import com.ucne.ticketsapp.data.remote.dto.ClienteDto
import com.ucne.ticketsapp.data.remote.dto.ConfiguracionesDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class RemoteConfigRepository @Inject constructor(
    private val api: TicketsApi
) {
    fun getClientes(): Flow<Resource<List<ConfiguracionesDto>>> = flow {

        try {

            emit(Resource.Loading())

            val getAll = api.getConfigs()

            emit(Resource.Success(getAll))

        } catch ( e: HttpException){

            emit(Resource.Error(e.message() ?: "HTTP SERVER ERROR, TIMEOUT, TRY AGAIN"))

        } catch (e: IOException) {

            emit(Resource.Error(e.message ?: "There may be a problem with your Connection, Please check your internet"))

        }
    }

    suspend fun postConfiguraciones(configuracionesDto: ConfiguracionesDto): Response<ConfiguracionesDto> {
        try {
            return api.postConfig(configuracionesDto)
        } catch (e: Exception) {
            throw e
        }
    }

    suspend fun getConfig(id: Int): ConfiguracionesDto? {
        try {
            return api.getConfigById(id)
        } catch (e: Exception) {
            throw e
        }
    }

    suspend fun getConfigByConfig(theme: Int, colorIndex: Int): ConfiguracionesDto? {
        try {
            return api.getConfig(theme, colorIndex)
        } catch (e: Exception) {
            throw e
        }
    }


    //PUT
    suspend fun updateConfig(id: Int, newConfig: ConfiguracionesDto): Response<ConfiguracionesDto> {
        try {
            return api.updateConfig(id, newConfig)
        } catch (e: Exception) {
            throw e
        }
    }

    suspend fun deleteConfig(id: Int): Response<ConfiguracionesDto> {
        try {
            return api.deleteConfig(id)
        } catch (e: Exception) {
            throw e
        }
    }
}