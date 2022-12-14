package com.ucne.ticketsapp.data.repository

import android.util.Log
import com.ucne.ticketsapp.data.remote.api.TicketsApi
import com.ucne.ticketsapp.data.remote.dto.TicketsDto
import retrofit2.Response
import javax.inject.Inject

class TicketsRepository @Inject constructor(
    private val api: TicketsApi
) {
    suspend fun getTickets(): List<TicketsDto> {
        try {
            return api.getTickets()
        } catch (e: Exception) {
            throw e
        }
    }
    suspend fun getTop5Tickets(id : Int): List<TicketsDto> {
        try {
            return api.getTop5Tickets(id)
        } catch (e: Exception) {
            throw e
        }
    }
    suspend fun postTickets(ticketsDto: TicketsDto): Response<TicketsDto> {
        try {
            return api.postTicket(ticketsDto)
        } catch (e: Exception) {
            Log.i("Ticket", e.message!!)
            throw e
        }
    }

    suspend fun getTicketsById(id: Int): TicketsDto? {
        try {
            return api.getTicketById(id)
        } catch (e: Exception) {
            throw e
        }
    }

    //PUT
    suspend fun updateTickets(id: Int, newTicket: TicketsDto): Response<TicketsDto> {
        try {
            return api.putTicket(id, newTicket)
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