package com.ucne.ticketsapp.data.repository

import com.ucne.ticketsapp.data.remote.api.TicketsApi
import com.ucne.ticketsapp.data.remote.dto.TiposDto
import retrofit2.Response
import javax.inject.Inject

class TiposRepository @Inject constructor(
    private val api: TicketsApi
) {
    suspend fun getTipos(): List<TiposDto> {
        try {
            return api.getTipos()
        } catch (e: Exception) {
            throw e
        }
    }

    suspend fun postTipos(tiposDto: TiposDto): Response<TiposDto> {
        try {
            return api.postTipo(tiposDto)
        } catch (e: Exception) {
            throw e
        }
    }

    suspend fun getTiposById(id: Int): TiposDto? {
        try {
            return api.getTipoById(id)
        } catch (e: Exception) {
            throw e
        }
    }

    //PUT
    suspend fun updateTipos(id: Int, newTipos: TiposDto): Response<TiposDto> {
        try {
            return api.putTipo(id, newTipos)
        } catch (e: Exception) {
            throw e
        }
    }

    suspend fun deleteTipos(id: Int): Response<TiposDto> {
        try {
            return api.deleteTipo(id)
        } catch (e: Exception) {
            throw e
        }
    }
}