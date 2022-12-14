package com.ucne.ticketsapp.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.ucne.ticketsapp.ui.MainViewModel

@Composable
fun ProfileDialog(
    viewModel: MainViewModel,
    onCancelPress: () -> Unit
) {
    val profileUiState = viewModel.profileUiState.collectAsState()
    val userUiState = viewModel.clientUiState.collectAsState()
    Dialog(
        onDismissRequest = { },
        properties = DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
    ) {
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
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = null,
                        modifier = Modifier.size(100.dp)
                    )
                    Text(
                        text = userUiState.value.nombres,
                        style = MaterialTheme.typography.headlineLarge
                    )
                    Text(
                        text = "ID: #${userUiState.value.clienteId}",
                        style = MaterialTheme.typography.headlineSmall
                    )
                    Column {
                        Divider(modifier = Modifier.padding(vertical = 10.dp))
                        Text(
                            text = "Tema",
                            style = MaterialTheme.typography.headlineSmall,
                            modifier = Modifier.padding(bottom = 15.dp)
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(text = "Light")
                                RadioButton(
                                    selected = profileUiState.value.themeIndex == 0,
                                    onClick = { viewModel.setProfileTheme(0) }
                                )
                            }

                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(text = "Dark")
                                RadioButton(
                                    selected = profileUiState.value.themeIndex == 1,
                                    onClick = { viewModel.setProfileTheme(1) }
                                )
                            }

                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(text = "System")
                                RadioButton(
                                    selected = profileUiState.value.themeIndex == 2,
                                    onClick = { viewModel.setProfileTheme(2) }
                                )
                            }
                        }
                    }
                    Column {
                        Divider(modifier = Modifier.padding(vertical = 10.dp))
                        Text(
                            text = "Accent Color",
                            style = MaterialTheme.typography.headlineSmall,
                            modifier = Modifier.padding(bottom = 15.dp)
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            RadioButton(
                                selected = profileUiState.value.colorIndex == 0,
                                onClick = { viewModel.setProfileColor(0) },
                                colors = RadioButtonDefaults.colors(
                                    selectedColor = Color(0xFFD0BCFF),
                                    unselectedColor = Color(0xFFD0BCFF)
                                ),
                                modifier = Modifier.size(25.dp)
                            )

                            RadioButton(
                                selected = profileUiState.value.colorIndex == 1,
                                onClick = { viewModel.setProfileColor(1) },
                                colors = RadioButtonDefaults.colors(
                                    selectedColor = Color(0xFF4CAF50),
                                    unselectedColor = Color(0xFF4CAF50)
                                ),
                                modifier = Modifier.size(25.dp)
                            )
                            RadioButton(
                                selected = profileUiState.value.colorIndex == 2,
                                onClick = { viewModel.setProfileColor(2) },
                                colors = RadioButtonDefaults.colors(
                                    selectedColor = Color(0xFF2196F3),
                                    unselectedColor = Color(0xFF2196F3)
                                ),
                                modifier = Modifier.size(25.dp)
                            )

                        }

                        Row(
                            modifier = Modifier.fillMaxWidth().padding(top=10.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            RadioButton(
                                selected = profileUiState.value.colorIndex == 3,
                                onClick = { viewModel.setProfileColor(3) },
                                colors = RadioButtonDefaults.colors(
                                    selectedColor = Color(0xFFFFE929),
                                    unselectedColor = Color(0xFFFFE929)
                                ),
                                modifier = Modifier.size(25.dp)
                            )

                            RadioButton(
                                selected = profileUiState.value.colorIndex == 4,
                                onClick = { viewModel.setProfileColor(4) },
                                colors = RadioButtonDefaults.colors(
                                    selectedColor = Color(0xFFFF7B07),
                                    unselectedColor = Color(0xFFFF7B07)
                                ),
                                modifier = Modifier.size(25.dp)
                            )
                            RadioButton(
                                selected = profileUiState.value.colorIndex == 5,
                                onClick = { viewModel.setProfileColor(5) },
                                colors = RadioButtonDefaults.colors(
                                    selectedColor = Color(0xFFF32121),
                                    unselectedColor = Color(0xFFF32121)
                                ),
                                modifier = Modifier.size(25.dp)
                            )

                        }
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp),
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    OutlinedButton(
                        onClick =  onCancelPress
                    ) {
                        Icon(imageVector = Icons.Default.Close, contentDescription = "Cancelar")
                        Text(text = "Cancelar")
                    }

                    Button(
                        onClick = {
                            viewModel.saveProfile()
                            onCancelPress()
                        }
                    ) {
                        Icon(imageVector = Icons.Default.Done, contentDescription = "Aceptar")
                        Text(text = "Aceptar")
                    }
                }
            }
        }
    }
}
