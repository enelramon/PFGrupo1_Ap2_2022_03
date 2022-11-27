package com.ucne.ticketsapp.util

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.WifiOff
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun NoConnectionDialog() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(imageVector = Icons.Default.WifiOff, contentDescription = null, Modifier.size(100.dp))
        Text(
            text = "¡Oops!, ¡Sin internet!",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(8.dp)
        )
        Text(text = "Por favor revisa tu conexión de internet", modifier = Modifier.padding(8.dp))
    }
}