package com.ucne.ticketsapp.data.repository

import com.ucne.ticketsapp.data.remote.api.TicketsApi
import com.ucne.ticketsapp.data.remote.dto.EstatusDto
import retrofit2.Response
import javax.inject.Inject

class EstatusRepository @Inject constructor(
    private val api: TicketsApi
) {
    suspend fun getEstatus(): Response<List<EstatusDto>> {
        try {
            return api.getEstatus()
        } catch (e: Exception) {
            throw e
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