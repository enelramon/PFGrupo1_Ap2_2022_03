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
import com.ucne.ticketsapp.ui.states.TicketUiState
import com.ucne.ticketsapp.util.getTicketColors

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TicketRow(
    ticket: TicketUiState,
    modifier: Modifier = Modifier,
    onReplyModeChange: () -> Unit,
    replyMode: Boolean,
) {
    val colors = getTicketColors(ticket)
    //Datos del ticket
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp), elevation = CardDefaults.cardElevation(
            defaultElevation = 0.dp
        )
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .clip(
                    shape = RoundedCornerShape(size = 16.dp)
                )
                .padding(10.dp)
        ) {

            Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "TicketID: ${ticket.id} ",
                )
                Text(
                    text = "(Orden ${ticket.orden})",
                )

                Text(
                    text = "[${ticket.tipo}]",
                )
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(percent = 50))
                        .background(color = colors.tipoColor)
                        .padding(top = 3.dp)
                        .size(18.dp)
                )
            }
            Text(text = ticket.estatus,  style = MaterialTheme.typography.labelMedium,modifier= Modifier.padding(3.dp))
            Divider(
                color = colors.estatusColor.copy(alpha = 0.75f),
                thickness = 3.dp,
                modifier = Modifier
                    .padding(4.dp)
                    .clip(RoundedCornerShape(16.dp))
            )
            Column {
                Text(
                    text = "Creado por: ${ticket.cliente}",
                    style = MaterialTheme.typography.labelMedium,
                    modifier = Modifier
                        .padding(bottom = 4.dp)
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Sistema: ${ticket.sistema}")
                    Text(text = "Prioridad ${ticket.prioridad}")
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(percent = 50))
                            .background(color = colors.prioridadColor).padding(top=2.dp)
                            .size(14.dp)
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = if (ticket.estatusId == 3) Arrangement.SpaceBetween else Arrangement.Start
                ) {
                    Text(text = "Fecha de creacion: " + ticket.fecha.substring(0,10))
                    if (ticket.estatusId == 3)
                        Text(text = "Fecha de cierre: " + ticket.fechaCerrado.substring(0,10))
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