package com.ucne.ticketsapp

import android.app.Application
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.navigation.compose.rememberNavController
import com.ucne.ticketsapp.ui.theme.TicketsAppTheme
import com.ucne.ticketsapp.util.NavigationManager
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TicketsAppTheme {
                val navController = rememberNavController()

                NavigationManager(navController = navController)
            }
        }
    }
}

@HiltAndroidApp
class AndroidApp : Application()