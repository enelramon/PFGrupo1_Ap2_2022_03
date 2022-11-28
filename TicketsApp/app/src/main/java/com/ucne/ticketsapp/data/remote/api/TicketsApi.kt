package com.ucne.ticketsapp.data.remote.api

import com.ucne.ticketsapp.data.remote.dto.*
import retrofit2.Response
import retrofit2.http.*

interface TicketsApi {
    @GET("api/Tickets")
    suspend fun getTickets(): List<TicketsDto>

    @POST("api/Tickets")
    suspend fun postTickets(@Body ticket : TicketsDto) : Response<TicketsDto>

    @GET("api/Tickets/{id}")
    suspend fun getTicketsById(@Path("id") id: Int): TicketsDto?

    @PUT("api/Tickets/{id}")
    suspend fun updateTickets(
        @Path("id") id: Int,
        @Body newTicket: TicketsDto
    ): Response<TicketsDto>

    @DELETE("api/Tickets/{id}")
    suspend fun deleteTickets(@Path("id") id: Int): Response<TicketsDto>

    @GET("api/Sistemas")
    suspend fun getSistemas(): List<SistemaDto>

    @POST("api/Sistemas")
    suspend fun postSistemas(@Body sistema: SistemaDto): Response<SistemaDto>

    @GET("api/Sistemas/{id}")
    suspend fun getSistemasById(@Path("id") id: Int): SistemaDto?

    @PUT("api/Sistemas/{id}")
    suspend fun updateSistemas(
        @Path("id") id: Int,
        @Body newSistema: SistemaDto
    ): Response<SistemaDto>

    @DELETE("api/Sistemas/{id}")
    suspend fun deleteSistemas(@Path("id") id: Int): Response<SistemaDto>


    @GET("api/Tipos")
    suspend fun getTipos(): List<TiposDto>

    @POST("api/Tipos")
    suspend fun postTipos(@Body tipo: TiposDto): Response<TiposDto>

    @GET("api/Tipos/{id}")
    suspend fun getTiposById(@Path("id") id: Int): TiposDto?

    @PUT("api/Tipos/{id}")
    suspend fun updateTipos(@Path("id") id: Int, @Body newTipo: TiposDto): Response<TiposDto>

    @DELETE("api/Tipos/{id}")
    suspend fun deleteTipos(@Path("id") id: Int): Response<TiposDto>


    @GET("api/Prioridades")
    suspend fun getPrioridades(): List<PrioridadesDto>

    @POST("api/Prioridades")
    suspend fun postPrioridades(@Body prioridad: PrioridadesDto): Response<PrioridadesDto>

    @GET("api/Prioridades/{id}")
    suspend fun getPrioridadesById(@Path("id") id: Int): PrioridadesDto?

    @PUT("api/Prioridades/{id}")
    suspend fun updatePrioridades(
        @Path("id") id: Int,
        @Body newPrioridad: PrioridadesDto
    ): Response<PrioridadesDto>

    @DELETE("api/Prioridades/{id}")
    suspend fun deletePrioridades(@Path("id") id: Int): Response<PrioridadesDto>


    @GET("api/Estatus")
    suspend fun getEstatus(): Response<List<EstatusDto>>

    @POST("api/Estatus")
    suspend fun postEstatus(@Body estatus: EstatusDto): Response<EstatusDto>

    @GET("api/Estatus/{id}")
    suspend fun getEstatusById(@Path("id") id: Int): EstatusDto?

    @PUT("api/Estatus/{id}")
    suspend fun updateEstatus(
        @Path("id") id: Int,
        @Body newEstatus: EstatusDto
    ): Response<EstatusDto>

    @DELETE("api/Estatus/{id}")
    suspend fun deleteEstatus(@Path("id") id: Int): Response<EstatusDto>


    @GET("api/Configuraciones")
    suspend fun getConfigs(): Response<List<ConfiguracionesDto>>

    @POST("api/Configuraciones")
    suspend fun postConfig(@Body config: ConfiguracionesDto): Response<ConfiguracionesDto>

    @GET("api/Configuraciones/{id}")
    suspend fun getConfigById(@Path("id") id: Int): ConfiguracionesDto?

    @GET("api/Configuraciones/{theme},{colorIndex}")
    suspend fun getConfig(
        @Path("theme") theme: Int,
        @Path("colorIndex") colorIndex: Int
    ): ConfiguracionesDto?

    @PUT("api/Configuraciones/{id}")
    suspend fun updateConfig(
        @Path("id") id: Int,
        @Body config: ConfiguracionesDto
    ): Response<ConfiguracionesDto>

    @DELETE("api/Configuraciones/{id}")
    suspend fun deleteConfig(@Path("id") id: Int): Response<ConfiguracionesDto>


    @GET("api/Clientes")
    suspend fun getClientes(): List<ClienteDto>

    @POST("api/Clientes")
    suspend fun postClientes(@Body cliente: ClienteDto): Response<ClienteDto>

    @GET("api/Clientes/{id}")
    suspend fun getClientesById(@Path("id") id: Int): ClienteDto?

    @GET("api/Clientes/{nombre},{clave}")
    suspend fun getCliente(
        @Path("nombre") nombre: String,
        @Path("clave") clave: String
    ): ClienteDto?

    @PUT("api/Clientes/{id}")
    suspend fun updateClientes(
        @Path("id") id: Int,
        @Body newCliente: ClienteDto
    ): Response<ClienteDto>

    @DELETE("api/Clientes/{id}")
    suspend fun deleteClientes(@Path("id") id: Int): Response<ClienteDto>


}