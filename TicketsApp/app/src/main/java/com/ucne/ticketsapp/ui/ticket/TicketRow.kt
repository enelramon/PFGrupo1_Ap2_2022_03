package com.ucne.ticketsapp.ui.ticket

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.ucne.ticketsapp.ui.states.TicketUiState
import com.ucne.ticketsapp.util.getTicketColors


@Composable
fun TicketRow(
    onPress: (Int) -> Unit,
    onDoubleTap: (Int) -> Unit,
    ticket: TicketUiState,
) {
    val colors = getTicketColors(ticket.tipoId,ticket.prioridadId,ticket.estatusId)

    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        modifier = Modifier
            .pointerInput(Unit) {
                detectTapGestures(
                    onDoubleTap = { onDoubleTap(ticket.id) },//Open reply dialog with this ticket
                    onTap = { onPress(ticket.id) }//Open Ticket Screen with this ticket
                )
            }
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation =  0.dp),
        content = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(4.dp)
            ) {

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "TicketID: ${ticket.id} ",
                    )
                    Text(
                        text = "(Orden ${ticket.orden})",
                    )

                    Text(
                        text = "[${ticket.tipo}]",
                        textDecoration = TextDecoration.Underline
                    )
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(percent = 50))
                            .background(color = colors.tipoColor)
                            .padding(top = 3.dp)
                            .size(18.dp)
                    )
                }

                Text(
                    text = ticket.estatus,
                    style = MaterialTheme.typography.labelMedium,
                    modifier = Modifier.padding(3.dp)
                )

                Divider(
                    color = colors.estatusColor.copy(alpha = 0.65f),
                    thickness = 6.dp,
                    modifier = Modifier
                        .padding(4.dp)
                        .clip(RoundedCornerShape(18.dp))
                )

                Column {
                    Text(
                        text = "Creado por: ${ticket.cliente}",
                        style = MaterialTheme.typography.labelLarge,
                        modifier = Modifier
                            .padding(bottom = 4.dp)
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Sistema: ${ticket.sistema}",
                            style = MaterialTheme.typography.labelMedium
                        )
                        Text(
                            text = "Prioridad ${ticket.prioridad}",
                            style = MaterialTheme.typography.labelMedium
                        )
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(percent = 50))
                                .background(color = colors.prioridadColor)
                                .padding(top = 2.dp)
                                .size(14.dp)
                        )
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = if (ticket.estatusId == 3) Arrangement.SpaceBetween else Arrangement.Start
                    ) {
                        Text(
                            text = "Creacion: " + ticket.fecha.substring(0, 10),
                            style = MaterialTheme.typography.labelMedium,
                        )
                        if (ticket.estatusId == 3)
                            Text(
                                text = "Cierre: " + ticket.fechaCerrado.substring(0, 10),
                                style = MaterialTheme.typography.labelMedium,
                            )
                    }
                }
                Text(text = ticket.especificaciones, textAlign = TextAlign.Justify)
            }

        }
    )
}
