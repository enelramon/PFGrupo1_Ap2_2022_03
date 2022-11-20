package com.ucne.ticketsapp.data.remote.api

import com.ucne.ticketsapp.data.remote.dto.EstatusDto
import retrofit2.Response
import retrofit2.http.*

interface EstatusApi {

    @GET("api/Estatus")
    suspend fun getEstatus(): Response<List<EstatusDto>>

    @POST("api/Estatus")
    suspend fun postEstatus(@Body estatus : EstatusDto) : Response<EstatusDto>

    @GET("api/Estatus/{id}")
    suspend fun getEstatusById(@Path("id") id: Int): EstatusDto?

    @PUT("api/Estatus/{id}")
    suspend fun updateEstatus(@Path("id")  id:Int, @Body newEstatus: EstatusDto): Response<EstatusDto>

    @DELETE("api/Estatus/{id}")
    suspend fun deleteEstatus(@Path("id")  id:Int): Response<EstatusDto>
}