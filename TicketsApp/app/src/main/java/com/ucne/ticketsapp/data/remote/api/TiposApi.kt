package com.ucne.ticketsapp.data.remote.api

import com.ucne.ticketsapp.data.remote.dto.TiposDto
import retrofit2.Response
import retrofit2.http.*

interface TiposApi {

    @GET("api/Tipos")
    suspend fun getTipos(): Response<List<TiposDto>>

    @POST("api/Tipos")
    suspend fun postTipos(@Body tipo : TiposDto) : Response<TiposDto>

    @GET("api/Tipos/{id}")
    suspend fun getTiposById(@Path("id") id: Int): TiposDto?

    @PUT("api/Tipos/{id}")
    suspend fun updateTipos(@Path("id")  id:Int, @Body newTipo: TiposDto): Response<TiposDto>

    @DELETE("api/Tipos/{id}")
    suspend fun deleteTipos(@Path("id")  id:Int): Response<TiposDto>
}