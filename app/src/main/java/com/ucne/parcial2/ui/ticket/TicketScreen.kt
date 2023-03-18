package com.ucne.parcial2.ui.ticket

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.ui.text.style.TextAlign


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TicketScreen(
    ticketId: Int,
    viewModel: TicketViewModel = hiltViewModel(),
    onSaveClick: () -> Unit
) {
    remember {
        viewModel.setTicket(ticketId)
        0
    }
    TicketBody(viewModel = viewModel) {
        onSaveClick()
    }

}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun TicketBody(
    viewModel: TicketViewModel,
    onSaveClick: () -> Unit
) {
    var expanded by remember {
        mutableStateOf(false)
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .clickable { },
        //elevation = 8.dp
    ) {
        Scaffold(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            "Registro De Tickets",
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.headlineLarge
                        )
                    }
                )
            },
            floatingActionButton = {
                ExtendedFloatingActionButton(
                    modifier = Modifier
                        .padding(8.dp),

                    //text = { Text("Guardar") },
                    content = {
                        Icon(
                            imageVector = Icons.Filled.Save,
                            contentDescription = "Save"
                        )
                    },
                    onClick = {
                        viewModel.putTicket()
                        onSaveClick()
                    }
                )
            }
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(it)
            ) {

                OutlinedTextField(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    value = viewModel.empresa,
                    onValueChange = { viewModel.empresa = it },
                    label = { Text("Empresa") }
                )

                OutlinedTextField(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    value = viewModel.asunto,
                    onValueChange = { viewModel.asunto = it },
                    label = { Text("Asunto") }
                )
                OutlinedTextField(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                        .clickable { expanded = true },
                    value = viewModel.estatus,
                    enabled = false, readOnly = true,
                    onValueChange = { viewModel.estatus = it },
                    label = { Text("Estatus") }
                )
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp)

                ) {
                    viewModel.opcionesEstatus.forEach { opcion ->
                        DropdownMenuItem(
                            text = {
                                Text(text = opcion, textAlign = TextAlign.Center)
                            },
                            onClick = {
                                expanded = false
                                viewModel.estatus = opcion
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 8.dp)
                        )
                    }
                }
                OutlinedTextField(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    value = viewModel.especificaciones,
                    onValueChange = { viewModel.especificaciones = it },
                    label = { Text("Especificaciones") }
                )

            }
        }
    }
}





