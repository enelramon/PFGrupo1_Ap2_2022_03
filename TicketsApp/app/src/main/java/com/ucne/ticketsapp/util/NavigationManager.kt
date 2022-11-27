package com.ucne.ticketsapp.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ucne.ticketsapp.data.remote.dto.ClienteDto
import com.ucne.ticketsapp.ui.MainLayout
import com.ucne.ticketsapp.ui.login.LoginScreen
import com.ucne.ticketsapp.ui.ticket.TicketScreen

@Composable
fun NavigationManager(
    navController: NavHostController,
) {
    val alphaAnimation = remember { androidx.compose.animation.core.Animatable(0f) }
    LaunchedEffect(Unit) {
        alphaAnimation.animateTo(1f)
    }
    var userLogeado: ClienteDto? = null
    var inAdminMode = false

    NavHost(
        navController = navController,
        startDestination = Screen.LoginScreen.ruta
    ) {
        //LoginScreen
        composable(Screen.LoginScreen.ruta)
        {
            LoginScreen() { user, adminMode ->
                userLogeado = user
                inAdminMode = adminMode
                navController.navigate(Screen.MainScreen.ruta)
            }
        }
        //Main Screen
        composable(Screen.MainScreen.ruta) {
            MainLayout(userLogeado!!, inAdminMode = inAdminMode, navController)
        }

        //Ticket Screen
        composable(Screen.TicketScreen.ruta) {
            TicketScreen(editMode = false, onPressCancel = { navController.navigateUp() })
        }
    }
}