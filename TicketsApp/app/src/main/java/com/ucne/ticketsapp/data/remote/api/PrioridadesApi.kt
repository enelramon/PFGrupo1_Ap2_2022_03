package com.ucne.ticketsapp.data.remote.api

import com.ucne.ticketsapp.data.remote.dto.PrioridadesDto
import retrofit2.Response
import retrofit2.http.*

interface PrioridadesApi {

    @GET("api/Prioridades")
    suspend fun getPrioridades(): Response<List<PrioridadesDto>>

    @POST("api/Prioridades")
    suspend fun postPrioridades(@Body prioridad : PrioridadesDto) : Response<PrioridadesDto>

    @GET("api/Prioridades/{id}")
    suspend fun getPrioridadesById(@Path("id") id: Int): PrioridadesDto?

    @PUT("api/Prioridades/{id}")
    suspend fun updatePrioridades(@Path("id")  id:Int, @Body newPrioridad: PrioridadesDto): Response<PrioridadesDto>

    @DELETE("api/Prioridades/{id}")
    suspend fun deletePrioridades(@Path("id")  id:Int): Response<PrioridadesDto>
}