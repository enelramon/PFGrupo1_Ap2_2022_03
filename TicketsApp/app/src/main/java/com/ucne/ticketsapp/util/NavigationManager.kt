package com.ucne.ticketsapp.util

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.ucne.ticketsapp.ui.MainLayout
import com.ucne.ticketsapp.ui.login.LoginScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavigationManager(
    navController: NavHostController,
) {


    NavHost(
        navController = navController,
        startDestination = Screen.LoginScreen.ruta
    ) {
        //LoginScreen
        composable(Screen.LoginScreen.ruta)
        {
            LoginScreen { user, adminMode ->
                navController.navigate(Screen.MainScreen.ruta +"/$user/$adminMode")
            }
        }
        //Main Screen
        composable(
            route = Screen.MainScreen.ruta+"/{userId}/{adminMode}",
            arguments = listOf(
                navArgument("userId"){type = NavType.IntType},
                navArgument("adminMode"){type = NavType.BoolType}
            )
        ) {
            val userLogeado = it.arguments?.getInt("userId")?:0
            val inAdminMode = it.arguments?.getBoolean("adminMode")?: false
            MainLayout(userLogeado, inAdminMode = inAdminMode, navController)
        }
    }
}