package com.ucne.ticketsapp.ui.ticket

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ucne.ticketsapp.ui.components.CustomDatePickerDialog
import com.ucne.ticketsapp.util.noRippleClickable


@Composable
fun TicketScreen(
    editMode: Boolean = false,
    onPressCancel: () -> Unit,
    viewModel: TicketViewModel = hiltViewModel()
) {
    val uiState = viewModel.ticketsUiState.collectAsState()
    val sistemas = viewModel.sistemaListUiState.collectAsState()
    val prioridades = viewModel.prioridadesListUiState.collectAsState()
    val tipos = viewModel.tipoListUiState.collectAsState()

    var sistemaExpanded by remember { mutableStateOf(false) }
    var tipoExpanded by remember { mutableStateOf(false) }
    var prioridadExpanded by remember { mutableStateOf(false) }
    var showDatePicker by remember { mutableStateOf(false) }
    if (showDatePicker) {
        CustomDatePickerDialog("", { viewModel.setDate(it) }) { showDatePicker = false }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 20.dp, horizontal = 8.dp)
            .padding(bottom = 60.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            Text(
                text = if (editMode) "Edición de Tickets" else "Creacion de Tickets",
                style = MaterialTheme.typography.headlineLarge
            )

            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                OutlinedTextField(
                    label = { Text("Sistema") },
                    value = uiState.value.sistema,
                    enabled = false, readOnly = true,
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Default.ArrowDropDown,
                            contentDescription = null
                        )
                    },
                    onValueChange = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .noRippleClickable {
                            sistemaExpanded = true
                        }

                )
                DropdownMenu(
                    expanded = sistemaExpanded,
                    onDismissRequest = { sistemaExpanded = false },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp)

                ) {
                    sistemas.value.list.forEach { sistema ->
                        DropdownMenuItem(
                            text = {
                                Text(
                                    text = sistema.sistema,
                                    textAlign = TextAlign.Center,
                                    color = Color.White
                                )
                            },
                            onClick = {
                                sistemaExpanded = false
                                viewModel.setSistema(sistema.sistemaId)
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 8.dp)
                        )
                    }
                }

                OutlinedTextField(
                    label = { Text("Tipo") },
                    value = uiState.value.tipo,
                    enabled = false, readOnly = true,
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Default.ArrowDropDown,
                            contentDescription = null
                        )
                    },
                    onValueChange = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .noRippleClickable {
                            tipoExpanded = true
                        }

                )
                DropdownMenu(
                    expanded = tipoExpanded,
                    onDismissRequest = { tipoExpanded = false },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp)

                ) {
                    tipos.value.list.forEach {
                        DropdownMenuItem(
                            text = {
                                Text(
                                    text = it.tipo,
                                    textAlign = TextAlign.Center,
                                    color = Color.White
                                )
                            },
                            onClick = {
                                tipoExpanded = false
                                viewModel.setTipo(it.tipoId)
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 8.dp)
                        )
                    }
                }


                if (editMode) {
                    OutlinedTextField(
                        label = { Text("Fecha") },
                        value = uiState.value.sistema,
                        enabled = false, readOnly = true,
                        trailingIcon = {
                            Icon(
                                imageVector = Icons.Default.ArrowDropDown,
                                contentDescription = null
                            )
                        },
                        onValueChange = {},
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                            .noRippleClickable {
                                showDatePicker = true
                            }

                    )
                }
                OutlinedTextField(
                    label = { Text("Prioridad") },
                    value = uiState.value.prioridad,
                    enabled = false, readOnly = true,
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Default.ArrowDropDown,
                            contentDescription = null
                        )
                    },
                    onValueChange = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .noRippleClickable {
                            prioridadExpanded = true
                        }

                )
                DropdownMenu(
                    expanded = prioridadExpanded,
                    onDismissRequest = { prioridadExpanded = false },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp)

                ) {
                    prioridades.value.list.forEach {
                        DropdownMenuItem(
                            text = {
                                Text(
                                    text = it.prioridad,
                                    textAlign = TextAlign.Center,
                                    color = Color.White
                                )
                            },
                            onClick = {
                                tipoExpanded = false
                                viewModel.setPrioridad(it.prioridadId)
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 8.dp)
                        )
                    }
                }
                OutlinedTextField(
                    label = { Text("Especificaciones") },
                    value = uiState.value.especificaciones,
                    onValueChange = { viewModel.setEspecificaciones(it) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .padding(vertical = 8.dp)
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = onPressCancel,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFDC3939),
                    contentColor = Color.White
                )
            ) {
                Icon(imageVector = Icons.Default.Close, contentDescription = "Cancelar")
                Text(text = "Cancelar")
            }

            Button(
                onClick = { /*Guardo*/ },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF5CDC5A),
                    contentColor = Color.White
                )
            ) {
                Icon(imageVector = Icons.Default.Done, contentDescription = "Aceptar")
                Text(text = "Aceptar")
            }
        }
    }
}