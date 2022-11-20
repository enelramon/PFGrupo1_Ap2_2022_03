package com.ucne.ticketsapp.data.remote.api

import com.ucne.ticketsapp.data.remote.dto.SistemaDto
import retrofit2.Response
import retrofit2.http.*

interface SistemasApi {

    @GET("api/Sistemas")
    suspend fun getSistemas(): Response<List<SistemaDto>>

    @POST("api/Sistemas")
    suspend fun postSistemas(@Body sistema : SistemaDto) : Response<SistemaDto>

    @GET("api/Sistemas/{id}")
    suspend fun getSistemasById(@Path("id") id: Int): SistemaDto?

    @PUT("api/Sistemas/{id}")
    suspend fun updateSistemas(@Path("id")  id:Int, @Body newSistema: SistemaDto): Response<SistemaDto>

    @DELETE("api/Sistemas/{id}")
    suspend fun deleteSistemas(@Path("id")  id:Int): Response<SistemaDto>
}