package com.ucne.ticketsapp.util

sealed class Screen(
    val ruta: String
) {
    object MainScreen : Screen("Main")
    object HomeScreen : Screen("Home")
    object TicketsListScreen : Screen("Tickets")
    object TicketScreen : Screen("Tickets")
    object ClientesListScreen : Screen("Clientes")
    object LoginScreen : Screen("Login")
}