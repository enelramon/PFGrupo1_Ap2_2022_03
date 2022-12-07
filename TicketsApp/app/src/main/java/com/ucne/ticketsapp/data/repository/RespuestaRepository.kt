package com.ucne.ticketsapp.data.repository

import com.ucne.ticketsapp.data.remote.api.TicketsApi
import com.ucne.ticketsapp.data.remote.dto.RespuestaDto
import retrofit2.Response
import javax.inject.Inject

class RespuestaRepository @Inject constructor(
    private val api : TicketsApi
){
    suspend fun getRespuestaById(id:Int) : RespuestaDto? {
        try {
            return api.getRespuestaById(id)
        }catch (e:Exception){
            throw e
        }
    }

    suspend fun postRespuesta(respuesta : RespuestaDto) : Response<RespuestaDto>{
        try {
            return api.postRespuesta(respuesta = respuesta)
        }catch (e:Exception) {
            throw e
        }
    }

    suspend fun getRespuestas(): List<RespuestaDto> {
        try {
            return api.getRespuestas()
        }catch (e : Exception){
            throw e
        }
    }
}