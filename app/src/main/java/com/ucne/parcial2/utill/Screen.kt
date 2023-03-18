package com.ucne.parcial2.utill

sealed class Screen(val route: String){
    object TicketListScreen: Screen("Consulta")
    object TicketScreen: Screen("Registro")
}