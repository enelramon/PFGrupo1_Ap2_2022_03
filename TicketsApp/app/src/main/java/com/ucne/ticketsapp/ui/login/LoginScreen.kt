package com.ucne.ticketsapp.ui.login

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.outlined.Password
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ucne.ticketsapp.R
import com.ucne.ticketsapp.data.remote.dto.ClienteDto
import com.ucne.ticketsapp.util.NoConnectionDialog
import com.ucne.ticketsapp.util.haveNetwork
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    login: (ClienteDto, Boolean) -> Unit
) {
    var clicksCounter by remember { mutableStateOf(0) }
    var withNet by remember { mutableStateOf(true) }
    val context = LocalContext.current
    withNet = haveNetwork(context)

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            val uiState = viewModel.uiState.collectAsState()
            Image(
                painter = painterResource(id = R.drawable.ticketsappforeground),
                contentDescription = null
            )
            Text(
                text = "Tickets App",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .padding(vertical = 20.dp)
                    .clickable {
                        if (!uiState.value.adminMode)
                            clicksCounter++
                        if (clicksCounter <= 9 && !uiState.value.adminMode) {

                            if (clicksCounter >= 5) {
                                scope.launch {
                                    val job = scope.launch {
                                        snackbarHostState.showSnackbar(
                                            "${10 - clicksCounter} click mas para entrar en modo Admin",
                                            duration = SnackbarDuration.Indefinite,
                                        )
                                    }
                                    delay(750)
                                    job.cancel()
                                }
                            }
                        } else if (clicksCounter == 10 && !uiState.value.adminMode) {
                            viewModel.setAdminMode()
                            scope.launch {
                                val job = scope.launch {
                                    snackbarHostState.showSnackbar(
                                        "¡Felicidades! Estas en modo Administrador",
                                        duration = SnackbarDuration.Indefinite,
                                    )
                                }
                                delay(750)
                                job.cancel()
                            }
                        } else {
                            scope.launch {
                                val job = scope.launch {
                                    snackbarHostState.showSnackbar(
                                        "¡Ya estas en modo Administrador !",
                                        duration = SnackbarDuration.Indefinite,
                                    )
                                }
                                delay(750)
                                job.cancel()
                            }
                        }
                    }
            )
            if (withNet) {

                viewModel.setLoginFunction(login)
                OutlinedTextField(
                    value = uiState.value.nombre,
                    onValueChange = { viewModel.setNombre(it) },
                    maxLines = 1,
                    singleLine = true,
                    leadingIcon = {
                        Icon(imageVector = Icons.Outlined.Person, null)
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    ),
                    label = { Text("Usuario") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                )

                var passwordVisible by rememberSaveable { mutableStateOf(false) }

                OutlinedTextField(
                    value = uiState.value.clave,
                    onValueChange = { viewModel.setClave(it) },
                    maxLines = 1,
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done
                    ),
                    singleLine = true,
                    leadingIcon = {
                        Icon(imageVector = Icons.Outlined.Password, null)
                    },

                    trailingIcon = {
                        val image = if (passwordVisible)
                            Icons.Filled.Visibility
                        else Icons.Filled.VisibilityOff

                        val description = if (passwordVisible) "Hide password" else "Show password"

                        IconButton(onClick = { passwordVisible = !passwordVisible }) {
                            Icon(imageVector = image, description)
                        }
                    },
                    label = { Text("Clave") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Checkbox(
                        checked = uiState.value.recuerdame,
                        onCheckedChange = { viewModel.rememberMeChanged() })
                    Text(
                        text = "Recuerdame en este dispositivo",
                        modifier = Modifier.padding(start = 10.dp, top = 8.dp)
                    )
                }

                Button(
                    onClick = {
                        if (viewModel.login() == null) {
                            scope.launch {
                                snackbarHostState.showSnackbar(
                                    "No se pudo logear"
                                )
                            }
                        } else {
                            login(viewModel.login()!!, uiState.value.adminMode)
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Text(text = "Ingresar")
                }
            } else {
                NoConnectionDialog()
                Button(onClick = {
                    withNet = haveNetwork(context)
                }) {
                    Text("Reintentar")
                }
            }
        }
    }
}