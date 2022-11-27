package com.ucne.ticketsapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ucne.ticketsapp.data.remote.dto.TicketsDto
import com.ucne.ticketsapp.util.getTicketColors

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TicketRow(
    ticket: TicketsDto,
    modifier: Modifier = Modifier,
    isSelected: Boolean,
    onReplyModeChange: () -> Unit,
    replyMode: Boolean,
) {
    val colors = getTicketColors(ticket)
    //Datos del ticket
    Card(
        modifier = modifier.fillMaxWidth(), elevation = CardDefaults.cardElevation(
            defaultElevation = if (isSelected) {
                0.dp
            } else {
                20.dp
            }
        )
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .background(
                    color = colors.estatusColor.copy(alpha = 0.75f),
                    shape = RoundedCornerShape(size = 16.dp)
                )
                .padding(vertical = 8.dp)
        ) {

            Row {
                Text(
                    text = "Respuestas de Ticket ${ticket.orden} (${ticket.tipo.tipo})",
                    style = MaterialTheme.typography.headlineLarge
                )
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(percent = 50))
                        .background(color = colors.tipoColor)
                        .size(13.dp)
                )
            }
            Column {
                Text(
                    text = "Creado por: ${ticket.cliente.nombres}",
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier
                        .padding(bottom = 15.dp)
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Sistema: " + ticket.sistema.sistema)
                    Text(text = "Prioridad " + ticket.prioridad.prioridad)
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(percent = 50))
                            .background(color = colors.prioridadColor)
                            .size(13.dp)
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Creado  " + ticket.fechaCreacion)
                    if (ticket.estatusId == 3)
                        Text(text = "Cerrado  " + ticket.fechaFinalizado)
                }
            }
            Text(text = ticket.especificaciones)
        }
        if (replyMode) {
            OutlinedTextField(
                value = "",//uiState.value.respuesta,
                onValueChange = { /*viewModel.setRespuesta(it)*/ },
                placeholder = { Text("Respuesta") },
                modifier = Modifier
                    .height(60.dp)
                    .fillMaxWidth()
                    .padding(8.dp)
            )
            Row {
                Button(
                    onClick = onReplyModeChange,
                    colors = ButtonDefaults.buttonColors(
                        contentColor = Color.White
                    )
                ) {
                    Text(text = "Cancelar")
                    Icon(imageVector = Icons.Default.Cancel, contentDescription = null)
                }

                Button(
                    onClick = { /*Agregar respuesta*/ },
                    colors = ButtonDefaults.buttonColors(
                        contentColor = Color.White
                    )
                ) {
                    Text(text = "Responder")
                    Icon(imageVector = Icons.Default.Send, contentDescription = null)
                }
            }
        }
    }
}