package com.ucne.ticketsapp.data.repository

import com.ucne.ticketsapp.data.remote.api.PrioridadesApi
import com.ucne.ticketsapp.data.remote.dto.PrioridadesDto
import retrofit2.Response
import javax.inject.Inject

class PrioridadesRepository @Inject constructor(
    private val api: PrioridadesApi
) {
    suspend fun getPrioridades(): Response<List<PrioridadesDto>> {
        try {
            return api.getPrioridades()
        } catch (e: Exception) {
            throw e
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