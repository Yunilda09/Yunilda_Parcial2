package com.ucne.parcial2.ui.ticket

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ucne.parcial2.data.entity.TicketEntity
import com.ucne.parcial2.data.remote.TicketsApi
import com.ucne.parcial2.data.remote.dto.TicketsDto
import com.ucne.parcial2.data.repository.TicketRepository
import com.ucne.parcial2.data.repository.TicketRepositoryImp
import com.ucne.parcial2.utill.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


data class TicketsListState(
    val isLoading: Boolean = false,
    val tickets: List<TicketsDto> = emptyList(),
    val error: String = ""
)
data class TicketsState(
    val isLoading: Boolean = false,
    val ticket: TicketsDto ? =  null,
    val error: String = ""
)
@HiltViewModel
class TicketViewModel @Inject constructor(

    private val ticketRepository: TicketRepositoryImp

) : ViewModel() {
    var ticketId by mutableStateOf(0)
    var empresa by mutableStateOf("")
    var asunto by mutableStateOf("")
    var especificaciones by mutableStateOf("" )
    var estatus by mutableStateOf("")

    val opcionesEstatus = listOf("Solicitado", "En espera", "Finalizado")
    var uiState = MutableStateFlow(TicketsListState())
        private set
    var uiStateTicket = MutableStateFlow(TicketsState())
        private set

    private fun Limpiar(){
        empresa = ""
        asunto = ""
        estatus = ""
        especificaciones = ""
    }
    fun setTicket(id:Int){
        ticketId = id
        Limpiar()
        ticketRepository.getTicketbyId(ticketId).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    uiStateTicket.update { it.copy(isLoading = true) }
                }
                is Resource.Success -> {
                    uiStateTicket.update {
                        it.copy(ticket = result.data )
                    }
                    empresa = uiStateTicket.value.ticket!!.empresa
                    asunto = uiStateTicket.value.ticket!!.asunto
                    estatus = uiStateTicket.value.ticket!!.estatus
                    especificaciones = uiStateTicket.value.ticket!!.especificaciones
                }
                is Resource.Error -> {
                    uiStateTicket.update { it.copy(error = result.message ?: "Error desconocido") }
                }
            }
        }.launchIn(viewModelScope)
    }
    fun putTicket(){
        viewModelScope.launch {
            ticketRepository.putTicket(ticketId, TicketsDto(asunto,
                empresa,
                uiStateTicket.value.ticket!!.encargadoId,
                especificaciones,
                estatus,uiStateTicket.value.ticket!!.fecha,
                uiStateTicket.value.ticket!!.orden,
                ticketId = ticketId ))
        }

    }
    init {
        ticketRepository.getTickets().onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    uiState.update { it.copy(isLoading = true) }
                }
                is Resource.Success -> {
                    uiState.update {
                        it.copy(tickets = result.data ?: emptyList())
                    }
                }
                is Resource.Error -> {
                    uiState.update { it.copy(error = result.message ?: "Error desconocido") }
                }
            }
        }.launchIn(viewModelScope)
    }
}