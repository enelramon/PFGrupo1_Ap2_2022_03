package com.ucne.ticketsapp.data.remote.api

import com.ucne.ticketsapp.data.remote.dto.*
import retrofit2.Response
import retrofit2.http.*

interface TicketsApi {
    //TODO: Agregar Resource

    @GET("api/Tickets")
    suspend fun getTickets(): List<TicketsDto>

    @POST("api/Tickets")
    suspend fun postTicket(@Body ticket : TicketsDto) : Response<TicketsDto>

    @GET("api/Tickets/Top5Tickets/{id}")
    suspend fun getTop5Tickets(@Path("id") id: Int): List<TicketsDto>
    @GET("api/Tickets/{id}")
    suspend fun getTicketById(@Path("id") id: Int): TicketsDto?

    @PUT("api/Tickets/{id}")
    suspend fun putTicket(
        @Path("id") id: Int,
        @Body newTicket: TicketsDto
    ): Response<TicketsDto>

    @DELETE("api/Tickets/{id}")
    suspend fun deleteTickets(@Path("id") id: Int): Response<TicketsDto>

    @GET("api/Sistemas")
    suspend fun getSistemas(): List<SistemaDto>

    @GET("MoreTickets")
    suspend fun getSistemasWithMoreTickets(): List<SistemaDto>

    @POST("api/Sistemas")
    suspend fun postSistema(@Body sistema: SistemaDto): Response<SistemaDto>

    @GET("api/Sistemas/{id}")
    suspend fun getSistemaById(@Path("id") id: Int): SistemaDto?

    @PUT("api/Sistemas/{id}")
    suspend fun putSistema(
        @Path("id") id: Int,
        @Body newSistema: SistemaDto
    ): Response<SistemaDto>

    @DELETE("api/Sistemas/{id}")
    suspend fun deleteSistema(@Path("id") id: Int): Response<SistemaDto>


    @GET("api/Tipos")
    suspend fun getTipos(): List<TiposDto>

    @POST("api/Tipos")
    suspend fun postTipo(@Body tipo: TiposDto): Response<TiposDto>

    @GET("api/Tipos/{id}")
    suspend fun getTipoById(@Path("id") id: Int): TiposDto?

    @PUT("api/Tipos/{id}")
    suspend fun putTipo(@Path("id") id: Int, @Body newTipo: TiposDto): Response<TiposDto>

    @DELETE("api/Tipos/{id}")
    suspend fun deleteTipo(@Path("id") id: Int): Response<TiposDto>


    @GET("api/Prioridades")
    suspend fun getPrioridades(): List<PrioridadesDto>

    @POST("api/Prioridades")
    suspend fun postPrioridad(@Body prioridad: PrioridadesDto): Response<PrioridadesDto>

    @GET("api/Prioridades/{id}")
    suspend fun getPrioridadById(@Path("id") id: Int): PrioridadesDto?

    @PUT("api/Prioridades/{id}")
    suspend fun putPrioridad(
        @Path("id") id: Int,
        @Body newPrioridad: PrioridadesDto
    ): Response<PrioridadesDto>

    @DELETE("api/Prioridades/{id}")
    suspend fun deletePrioridad(@Path("id") id: Int): Response<PrioridadesDto>


    @GET("api/Estatus")
    suspend fun getEstatus(): Response<List<EstatusDto>>

    @POST("api/Estatus")
    suspend fun postEstatus(@Body estatus: EstatusDto): Response<EstatusDto>

    @GET("api/Estatus/{id}")
    suspend fun getEstatusById(@Path("id") id: Int): EstatusDto?

    @PUT("api/Estatus/{id}")
    suspend fun putEstatus(
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
    suspend fun putConfig(
        @Path("id") id: Int,
        @Body config: ConfiguracionesDto
    ): Response<ConfiguracionesDto>

    @DELETE("api/Configuraciones/{id}")
    suspend fun deleteConfig(@Path("id") id: Int): Response<ConfiguracionesDto>


    @GET("api/Clientes")
    suspend fun getClientes(): List<ClienteDto>

    @GET("api/Clientes/Top5Respondidos/{id}")
    suspend fun getTop5ClientesRespondieron(@Path("id") id: Int): List<ClienteDto>

    @GET("api/Clientes/TopRespondidosPor_Mi/{id}")
    suspend fun getTop5ClientesRespondidos(@Path("id") id: Int): List<ClienteDto>

    @POST("api/Clientes")
    suspend fun postCliente(@Body cliente: ClienteDto): Response<ClienteDto>

    @GET("api/Clientes/{id}")
    suspend fun getClienteById(@Path("id") id: Int): ClienteDto?

    @GET("api/Clientes/{nombre},{clave}")
    suspend fun getCliente(
        @Path("nombre") nombre: String,
        @Path("clave") clave: String
    ): ClienteDto?

    @PUT("api/Clientes/{id}")
    suspend fun putCliente(
        @Path("id") id: Int,
        @Body newCliente: ClienteDto
    ): Response<ClienteDto>

    @DELETE("api/Clientes/{id}")
    suspend fun deleteClientes(@Path("id") id: Int): Response<ClienteDto>
    @POST("api/Respuestas")
    suspend fun postRespuesta(@Body respuesta: RespuestaDto): Response<RespuestaDto>

    @GET("api/Respuestas/{id}")
    suspend fun getRespuestaById(@Path("id") id: Int): RespuestaDto?
    @GET("api/Respuestas/")
    suspend fun getRespuestas(): List<RespuestaDto>

}