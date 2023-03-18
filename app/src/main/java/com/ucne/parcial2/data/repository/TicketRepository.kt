package com.ucne.parcial2.data.repository

//import com.ucne.parcial2.data.dao.TicketDao
import com.ucne.parcial2.data.entity.TicketEntity
import com.ucne.parcial2.data.entity.toTicketDto
import com.ucne.parcial2.data.remote.TicketsApi
import com.ucne.parcial2.data.remote.dto.TicketsDto
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TicketRepository @Inject constructor(
    //private val ticketDao: TicketDao,
    private val ticketsApi: TicketsApi
) {
    suspend fun insert(ticket: TicketEntity){
        //ticketDao.insert(ticket)

       // val ticketsNoEnviadas = ticketDao.getNoEnviadas()
        //ticketsApi.postticket(ticket.toTicketsDto())
        ticketsApi.postticket(ticket.toTicketDto())
    }
    //suspend fun delete(ticket: TicketEntity) = ticketDao.delete(ticket)
    //suspend fun find(ticketId: Int) = ticketDao.find(ticketId)
    suspend fun putTicket(id: Int, tickestDto: TicketsDto) = ticketsApi.putTicket(id, tickestDto)

    //fun getList(): Flow<List<TicketEntity>> = ticketDao.getList()
}