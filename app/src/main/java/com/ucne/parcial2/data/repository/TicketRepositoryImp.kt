package com.ucne.parcial2.data.repository

import com.ucne.parcial2.data.remote.TicketsApi
import com.ucne.parcial2.data.remote.dto.TicketsDto
import com.ucne.parcial2.utill.Resource
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.Flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class TicketRepositoryImp @Inject constructor(
    private val api: TicketsApi

): Ticket2Repository {

   override fun getTickets():Flow<Resource<List<TicketsDto>>> = flow {
        try {
            emit(Resource.Loading())

            val tickets = api.getTickets()

            emit (Resource.Success(tickets))
        } catch (e: HttpException) {

            emit(Resource.Error(e.message ?: "Error HTTP GENERAL"))
        } catch (e: IOException) {
            emit(Resource.Error(e.message ?: "Verificar tu conexion a internet"))
        }
    }
    override suspend fun putTicket(id: Int, ticketsDto: TicketsDto){
        api.putTicket(id,ticketsDto)
    }
    override  fun getTicketbyId(id: Int) :Flow<Resource<TicketsDto>> = flow {
        try {
            emit(Resource.Loading())

            val tickets = api.getTicketsbyId(id)

            emit (Resource.Success(tickets))
        } catch (e: HttpException) {

            emit(Resource.Error(e.message ?: "Error HTTP GENERAL"))
        } catch (e: IOException) {
            emit(Resource.Error(e.message ?: "Verificar tu conexion a internet"))
        }
    }


    override suspend fun deleteTicket(id: Int) = api.deleteTicket(id)
}