package com.ucne.parcial2.ui.ticket

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.TaskAlt
import androidx.compose.material.icons.filled.Update
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ucne.parcial2.data.remote.dto.TicketsDto
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TicketListScreen(
    onNewTicket: () -> Unit, viewModel: TicketViewModel = hiltViewModel(),
    onTicketClick: (Int) -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxWidth()
            .padding(8.dp),
        topBar = {
            TopAppBar(
                title = { Text(text = "Lista De Tickets",
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.headlineLarge) }
            )
        },

    ) {
        val uiState by viewModel.uiState.collectAsState()
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            TicketListBody(uiState.tickets) {
                onTicketClick(it)
            }
        }
    }
}

@Composable
fun TicketListBody(ticketList: List<TicketsDto>, onTicketClick: (Int) -> Unit) {
    Column(modifier = Modifier.fillMaxWidth()) {
        LazyColumn {
            items(ticketList) { ticket ->
                TicketRow(ticket) {
                    onTicketClick(it)
                }
            }
        }
    }
}

@Composable
fun TicketRow(ticket: TicketsDto, onTicketClick: (Int) -> Unit) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = { onTicketClick(ticket.ticketId) })
        ) {
            Row() {
                Text(
                    text = ticket.empresa,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.weight(3f)
                )
                Text(
                    text = ticket.fecha.substring(0, 10),
                    style = MaterialTheme.typography.titleSmall,
                    textAlign = TextAlign.End,
                    modifier = Modifier.weight(3f)
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Text(
                    text = ticket.asunto,
                    style = MaterialTheme.typography.titleMedium,
                )

                Icon(
                    imageVector = when (ticket.estatus) {
                        "Solicitado" -> {
                            Icons.Default.Star
                        }
                        "En Espera" -> {
                            Icons.Default.Update
                        }
                        else -> {
                            Icons.Default.TaskAlt
                        }
                    },
                    contentDescription = ticket.estatus,
                    //modifier = Modifier.weight(2f)
                    tint = when (ticket.estatus) {
                        "Solicitado" -> {
                            Color.Green
                        }
                        "En espera" -> {
                            Color.Gray
                        }
                        else -> {
                            Color.Blue
                        }
                    }
                )
            }
        }
        Divider(Modifier.fillMaxWidth())
    }
}

