package com.ucne.parcial2.data.repository

import com.ucne.parcial2.data.remote.dto.TicketsDto
import com.ucne.parcial2.utill.Resource
import kotlinx.coroutines.flow.Flow

interface Ticket2Repository{
    fun getTickets(): Flow<Resource<List<TicketsDto>>>
    suspend fun putTicket(id: Int, ticketsDto: TicketsDto)
     fun getTicketbyId(id: Int): Flow<Resource<TicketsDto>>
    suspend fun deleteTicket(id: Int)
}