package com.ucne.ticketsapp.data.repository

import com.ucne.ticketsapp.data.remote.api.TicketsApi
import com.ucne.ticketsapp.data.remote.dto.ConfiguracionesDto
import retrofit2.Response
import javax.inject.Inject

class RemoteConfigRepository @Inject constructor(
    private val api: TicketsApi
) {
    suspend fun getConfiguraciones(): Response<List<ConfiguracionesDto>> {
        try {
            return api.getConfigs()
        } catch (e: Exception) {
            throw e
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