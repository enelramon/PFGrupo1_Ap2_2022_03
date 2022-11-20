package com.ucne.ticketsapp.data.remote.api

import com.ucne.ticketsapp.data.remote.dto.ClienteDto
import retrofit2.Response
import retrofit2.http.*

interface ClientesApi {

    @GET("api/Clientes")
    suspend fun getClientes(): Response<List<ClienteDto>>

    @POST("api/Clientes")
    suspend fun postClientes(@Body cliente : ClienteDto) : Response<ClienteDto>

    @GET("api/Clientes/{id}")
    suspend fun getClientesById(@Path("id") id: Int): ClienteDto?

    @PUT("api/Clientes/{id}")
    suspend fun updateClientes(@Path("id")  id:Int, @Body newCliente: ClienteDto): Response<ClienteDto>

    @DELETE("api/Clientes/{id}")
    suspend fun deleteClientes(@Path("id")  id:Int): Response<ClienteDto>
}