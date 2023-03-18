package com.ucne.parcial2.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ucne.parcial2.data.remote.dto.TicketsDto


@Entity(tableName = "Tickets")
data class TicketEntity(
    @PrimaryKey(autoGenerate = true)
    val asunto: String,
    val empresa: String,
    val encargadoId: Int? = null,
    val especificaciones: String,
    val estatus: String,
    val fecha: String,
    val orden: Int,
    val ticketId: Int? = null,
) {

}

fun TicketEntity.toTicketDto(): TicketsDto {
    return TicketsDto(
        asunto = this.asunto,
        empresa = this.empresa,
        encargadoId = this.encargadoId ?:0,
        especificaciones = this.especificaciones,
        estatus = this.estatus,
        fecha = this.fecha,
        orden = this.orden,
        ticketId = this.ticketId ?: 0,

        )
}