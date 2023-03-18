package com.ucne.parcial2.ui

import androidx.compose.runtime.Composable

import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.ucne.parcial2.ui.ticket.TicketListScreen
import com.ucne.parcial2.ui.ticket.TicketScreen
import com.ucne.parcial2.utill.Screen

@Composable
fun NavigationManageger(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.TicketListScreen.route){
        composable(route = Screen.TicketListScreen.route){
            TicketListScreen(onNewTicket = { }){id ->
                navController.navigate(Screen.TicketScreen.route + "/${id}")
            }
        }
        composable(route = Screen.TicketScreen.route + "/{id}",
            arguments = listOf(
                navArgument("id"){type = NavType.IntType})
        ){capturar ->
            val ticketId = capturar.arguments?.getInt("id")?:0

           TicketScreen(ticketId = ticketId){
               navController.navigate(Screen.TicketListScreen.route)
           }
        }
    }

}