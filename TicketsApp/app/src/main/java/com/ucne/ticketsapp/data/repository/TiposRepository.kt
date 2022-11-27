package com.ucne.ticketsapp.data.repository

import com.ucne.ticketsapp.data.remote.api.TicketsApi
import com.ucne.ticketsapp.data.remote.dto.TiposDto
import retrofit2.Response
import javax.inject.Inject

class TiposRepository @Inject constructor(
    private val api: TicketsApi
) {
    suspend fun getTipos(): Response<List<TiposDto>> {
        try {
            return api.getTipos()
        } catch (e: Exception) {
            throw e
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