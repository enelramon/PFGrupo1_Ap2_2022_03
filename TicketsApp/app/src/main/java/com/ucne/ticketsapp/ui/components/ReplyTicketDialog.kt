package com.ucne.ticketsapp.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DoubleArrow
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.ucne.ticketsapp.data.remote.dto.RespuestaDto
import com.ucne.ticketsapp.data.remote.dto.TicketsDto
import com.ucne.ticketsapp.util.getTicketColors

@Composable
fun ReplyTicketDialog(ticket: TicketsDto) {
    Dialog(
        onDismissRequest = { /*Cerrar el dialog*/ },
        properties = DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
    ) {
        val colors = getTicketColors(ticket)
        var writtingMode by remember { mutableStateOf(false) }
        var showRespuesta by remember { mutableStateOf(false) }
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 20.dp)
        ) {
            Column(
                modifier = Modifier.padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(onClick = { /*Cerrar Dialog*/ }) {
                    Icon(imageVector = Icons.Default.Close, contentDescription = null)
                }

                TextButton(onClick = { showRespuesta = !showRespuesta }) {
                    Text(
                        text = if (showRespuesta) {
                            "Ocultar Respuestas"
                        } else {
                            "Mostrar Respuestas"
                        }
                    )
                }
                if (showRespuesta) {
                    LazyColumn() {
                        ticket.respuestas.forEach {
                            item { RespuestaRow(respuesta = it) }
                        }
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp),
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.Center
                ) {
                    if (writtingMode) {
                        OutlinedTextField(
                            value = "",//uiState.value.respuesta,
                            onValueChange = { /*viewModel.setRespuesta(it)*/ },
                            placeholder = { Text("Respuesta") },
                            modifier = Modifier
                                .height(60.dp)
                                .fillMaxWidth()
                                .padding(8.dp)
                        )
                        Row() {
                            Button(
                                onClick = { writtingMode = false },
                                colors = ButtonDefaults.buttonColors(
                                    contentColor = Color.White
                                )
                            ) {
                                Text(text = "Cancelar")
                                Icon(imageVector = Icons.Default.Cancel, contentDescription = null)
                            }

                            Button(
                                onClick = { /*Agregar repueta*/ },
                                colors = ButtonDefaults.buttonColors(
                                    contentColor = Color.White
                                )
                            ) {
                                Text(text = "Responder")
                                Icon(imageVector = Icons.Default.Send, contentDescription = null)
                            }
                        }

                    } else {
                        Button(
                            onClick = { writtingMode = true },
                            enabled = ticket.estatusId != 3,
                            colors = ButtonDefaults.buttonColors(
                                contentColor = Color.White
                            )
                        ) {
                            Text(text = "Responder")
                            Icon(imageVector = Icons.Default.DoubleArrow, contentDescription = null)
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun RespuestaRow(respuesta: RespuestaDto) {
    Column {
        Row(horizontalArrangement = Arrangement.SpaceBetween) {
            Text(
                text = "Autor: ${respuesta.cliente.nombres}",
                style = MaterialTheme.typography.labelMedium
            )
            Text("Fecha: ${respuesta.fecha}")
        }
        Text(text = respuesta.respuesta, style = MaterialTheme.typography.bodyMedium)
        Divider()
    }

}
