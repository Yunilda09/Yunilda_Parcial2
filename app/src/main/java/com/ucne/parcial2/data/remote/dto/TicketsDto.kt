package com.ucne.parcial2.data.remote.dto

data class TicketsDto(
    val asunto: String,
    val empresa: String,
    val encargadoId: Int,
    val especificaciones: String,
    val estatus: String,
    val fecha: String,
    val orden: Int,
    val ticketId: Int
)