package com.ucne.ticketsapp.ui.ticket.reply

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DoubleArrow
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import com.ucne.ticketsapp.util.getTicketColors

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReplyTicketDialog(
    ticketId: Int = 0,
    clienteId: Int = 0,
    viewModel: TicketReplyViewModel = hiltViewModel(),
    onPressCancel: () -> Unit,
) {
    remember {
        viewModel.findTicketById(ticketId)
        0
    }
    viewModel.refreshAnswers()
    Dialog(
        onDismissRequest = onPressCancel,
        properties = DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = false)
    ) {
        val uiState by viewModel.uiState.collectAsState()
        var writtingMode by remember { mutableStateOf(false) }
        var showRespuesta by remember { mutableStateOf(false) }

        val colors = getTicketColors(uiState.tipoId, uiState.prioridadId, uiState.estatusId)

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 20.dp, horizontal = 8.dp)
                .background(MaterialTheme.colorScheme.surface)
        ) {
            Row {
                IconButton(onClick = onPressCancel) {
                    Icon(imageVector = Icons.Default.Close, contentDescription = null)
                }
            }

            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
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
                                text = "TicketID: ${uiState.id} ",
                            )
                            Text(
                                text = "(Orden ${uiState.orden})",
                            )


                        }
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = "[${uiState.tipo}]",
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
                            text = uiState.estatus,
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
                                text = "Creado por: ${uiState.cliente}",
                                style = MaterialTheme.typography.labelLarge,
                                modifier = Modifier
                                    .padding(bottom = 4.dp)
                            )
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = "Sistema: ${uiState.sistema}",
                                    style = MaterialTheme.typography.labelMedium
                                )
                                Text(
                                    text = "Prioridad ${uiState.prioridad}",
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
                                horizontalArrangement = if (uiState.estatusId == 3) Arrangement.SpaceBetween else Arrangement.Start
                            ) {
                                Text(
                                    text = "Creacion: " + if (uiState.fecha.length > 9) (uiState.fecha.substring(
                                        0,
                                        10
                                    )) else "",
                                    style = MaterialTheme.typography.labelMedium,
                                )
                                if (uiState.estatusId == 3)
                                    Text(
                                        text = "Cierre: " + if (uiState.fechaCerrado.length > 9) (uiState.fechaCerrado.substring(
                                            0,
                                            10
                                        )) else "",
                                        style = MaterialTheme.typography.labelMedium,
                                    )
                            }
                        }
                        Text(
                            text = uiState.especificaciones,
                            textAlign = TextAlign.Justify,
                            modifier = Modifier.padding(vertical = 10.dp)
                        )
                    }
                }
            )

            TextButton(onClick = { showRespuesta = !showRespuesta }) {
                Text(
                    text = if (showRespuesta) {
                        "Ocultar Respuestas"
                    } else {
                        "Mostrar Respuestas"
                    }
                )
            }
            val respuestasUiState by viewModel.listUiState.collectAsState()
            if (showRespuesta) {

                LazyColumn(modifier = Modifier.fillMaxWidth()) {
                    respuestasUiState.respuestas.forEach {
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
                    Column(modifier = Modifier.fillMaxWidth()) {
                        OutlinedTextField(
                            value = viewModel.respuesta,
                            onValueChange = { viewModel.respuesta = it },
                            placeholder = { Text("Respuesta") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Button(
                                onClick = { writtingMode = false }
                            ) {
                                Text(text = "Cancelar")
                                Icon(imageVector = Icons.Default.Cancel, contentDescription = null)
                            }

                            Button(
                                enabled = viewModel.respuesta.length<255 && viewModel.respuesta.isNotBlank(),
                                onClick = {
                                    viewModel.addAnswer(
                                        ticketId = uiState.id,
                                        clienteId = clienteId
                                    )
                                    writtingMode = false
                                }
                            ) {
                                Text(text = "Responder")
                                Icon(imageVector = Icons.Default.Send, contentDescription = null)
                            }
                        }
                    }
                } else {
                    Button(
                        onClick = { writtingMode = true },
                        enabled = (uiState.estatusId) != 3
                    ) {
                        Text(text = "Responder")
                        Icon(imageVector = Icons.Default.DoubleArrow, contentDescription = null)
                    }
                }
            }
        }
    }
}


@Composable
private fun RespuestaRow(respuesta: ReplyUiState) {

    Column(modifier = Modifier.padding(5.dp)) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
        ) {

            Text(
                text = "Autor: ${respuesta.autor}",
                style = MaterialTheme.typography.labelMedium
            )
            Text(
                "Fecha: ${respuesta.fecha.substring(0, 10)}",
                style = MaterialTheme.typography.labelSmall
            )
        }
        Text(text = respuesta.contenido, style = MaterialTheme.typography.bodyMedium)
        Divider()
    }
}
