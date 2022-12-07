package com.ucne.ticketsapp.data.repository


import com.ucne.ticketsapp.data.remote.api.TicketsApi
import com.ucne.ticketsapp.data.remote.dto.SistemaDto
import retrofit2.Response
import javax.inject.Inject

class SistemasRepository @Inject constructor(
    private val api: TicketsApi
) {
    suspend fun getSistemas(): List<SistemaDto> {
        try {
            return api.getSistemas()
        } catch (e: Exception) {
            throw e
        }
    }

    suspend fun getSistemasWithMoreTickets(): List<SistemaDto> {
        try {
            return api.getSistemasWithMoreTickets()
        } catch (e: Exception) {
            throw e
        }
    }

    suspend fun postSistemas(sistemaDto: SistemaDto): Response<SistemaDto> {
        try {
            return api.postSistema(sistemaDto)
        } catch (e: Exception) {
            throw e
        }
    }

    suspend fun getSistemasById(id: Int): SistemaDto? {
        try {
            return api.getSistemaById(id)
        } catch (e: Exception) {
            throw e
        }
    }

    //PUT
    suspend fun updateSistemas(id: Int, newSistema: SistemaDto): Response<SistemaDto> {
        try {
            return api.putSistema(id, newSistema)
        } catch (e: Exception) {
            throw e
        }
    }

    suspend fun deleteSistemas(id: Int): Response<SistemaDto> {
        try {
            return api.deleteSistema(id)
        } catch (e: Exception) {
            throw e
        }
    }
}