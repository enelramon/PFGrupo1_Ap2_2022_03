package com.ucne.ticketsapp.data.remote

import com.ucne.ticketsapp.data.remote.dto.TicketsDto
import retrofit2.Response
import retrofit2.http.*

interface TicketsApi {
    @GET("api/Tickets")
    suspend fun getTickets(): Response<List<TicketsDto>>

    @POST("api/Tickets")
    suspend fun postTickets(@Body articulo : TicketsDto) : Response<TicketsDto>

    @GET("api/Tickets/{id}")
    suspend fun getTicketsById(@Path("id") id: Int): TicketsDto?

    @PUT("api/Tickets/{id}")
    suspend fun updateTickets(@Path("id")  id:Int, @Body newArticulo: TicketsDto): Response<TicketsDto>

    @DELETE("api/Tickets/{id}")
    suspend fun deleteTickets(@Path("id")  id:Int): Response<TicketsDto>

}