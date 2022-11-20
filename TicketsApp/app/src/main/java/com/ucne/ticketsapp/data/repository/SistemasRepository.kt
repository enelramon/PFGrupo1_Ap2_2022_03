package com.ucne.ticketsapp.data.repository

import com.ucne.ticketsapp.data.remote.api.SistemasApi
import com.ucne.ticketsapp.data.remote.dto.SistemaDto
import retrofit2.Response
import javax.inject.Inject

class SistemasRepository @Inject constructor(
    private val api: SistemasApi
) {
    suspend fun getSistemas(): Response<List<SistemaDto>> {
        try {
            return api.getSistemas()
        } catch (e: Exception) {
            throw e
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