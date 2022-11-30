package com.ucne.ticketsapp.data.repository

import android.util.Log
import com.ucne.ticketsapp.data.domain.Resource
import com.ucne.ticketsapp.data.remote.api.TicketsApi
import com.ucne.ticketsapp.data.remote.dto.ClienteDto
import com.ucne.ticketsapp.data.remote.dto.TicketsDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class TicketsRepository @Inject constructor(
    private val api: TicketsApi
) {
    fun getTickets(): Flow<Resource<List<TicketsDto>>> = flow {

        try {

            emit(Resource.Loading())

            val getAll = api.getTickets()

            emit(Resource.Success(getAll))

        } catch ( e: HttpException){

            emit(Resource.Error(e.message() ?: "HTTP SERVER ERROR, TIMEOUT, TRY AGAIN"))

        } catch (e: IOException) {

            emit(Resource.Error(e.message ?: "There may be a problem with your Connection, Please check your internet"))

        }
    }

    suspend fun postTickets(ticketsDto: TicketsDto): Response<TicketsDto> {
        try {
            return api.postTickets(ticketsDto)
        } catch (e: Exception) {
            Log.i("Ticket", e.message!!)
            throw e
        }
    }

    suspend fun getTicketsById(id: Int): TicketsDto? {
        try {
            return api.getTicketsById(id)
        } catch (e: Exception) {
            throw e
        }
    }

    //PUT
    suspend fun updateTickets(id: Int, newTicket: TicketsDto): Response<TicketsDto> {
        try {
            return api.updateTickets(id, newTicket)
        } catch (e: Exception) {
            throw e
        }
    }

    suspend fun deleteTickets(id: Int): Response<TicketsDto> {
        try {
            return api.deleteTickets(id)
        } catch (e: Exception) {
            throw e
        }
    }
}