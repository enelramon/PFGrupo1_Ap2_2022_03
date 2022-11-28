package com.ucne.ticketsapp.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ucne.ticketsapp.data.remote.dto.ClienteDto
import com.ucne.ticketsapp.util.noRippleClickable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomDrawer(
    user: ClienteDto, isAdminMode: Boolean,
    navToHome: () -> Unit,
    navToTickets: () -> Unit,
    navToClientes: () -> Unit,
    navToLogin: () -> Unit,
    content: @Composable () -> Unit
) {
    ModalNavigationDrawer(
        drawerContent = {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Row(modifier = Modifier.padding(16.dp)) {
                        Icon(
                            imageVector = Icons.Default.AccountCircle,
                            contentDescription = null,
                            modifier = Modifier.size(100.dp)
                        )
                        Column(Modifier.padding(start = 10.dp)) {
                            Text(
                                text = user.nombres,
                                style = MaterialTheme.typography.headlineMedium,
                                modifier = Modifier.padding(bottom = 5.dp)
                            )
                            Text(
                                text = "ID: #" + user.clienteId,
                                style = MaterialTheme.typography.headlineSmall
                            )
                            if (isAdminMode) {
                                Row {
                                    Text(text = "Modo Administrador", fontWeight = FontWeight.Light)
                                    Icon(
                                        imageVector = Icons.Default.AdminPanelSettings,
                                        contentDescription = null
                                    )
                                }
                            }
                        }
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                            .clip(shape = AbsoluteRoundedCornerShape(size = 16.dp))
                            .noRippleClickable(navToHome)
                    ) {
                        Row(modifier = Modifier.padding(10.dp)) {
                            Icon(imageVector = Icons.Default.Home, contentDescription = null)
                            Text(
                                text = "Home",
                                style = MaterialTheme.typography.labelLarge,
                                modifier = Modifier.padding(start = 10.dp)
                            )
                        }
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                            .clip(shape = AbsoluteRoundedCornerShape(size = 16.dp))
                            .noRippleClickable(navToTickets)
                    ) {
                        Row(modifier = Modifier.padding(10.dp)) {
                            Icon(imageVector = Icons.Default.Sell, contentDescription = null)
                            Text(
                                text = "Tickets",
                                style = MaterialTheme.typography.labelLarge,
                                modifier = Modifier.padding(start = 10.dp)
                            )
                        }

                    }
                    if (isAdminMode) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp, vertical = 8.dp)
                                .clip(shape = AbsoluteRoundedCornerShape(size = 16.dp))
                                .noRippleClickable(navToClientes)
                        ) {
                            Row(modifier = Modifier.padding(10.dp)) {
                                Icon(imageVector = Icons.Default.Person, contentDescription = null)
                                Text(
                                    text = "Clientes",
                                    style = MaterialTheme.typography.labelLarge,
                                    modifier = Modifier.padding(start = 10.dp)
                                )
                            }
                        }
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 16.dp)
                        .clip(shape = AbsoluteRoundedCornerShape(size = 16.dp))
                        .noRippleClickable(navToLogin),
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Row(modifier = Modifier.padding(10.dp)) {
                        Icon(imageVector = Icons.Default.Logout, contentDescription = null)
                        Text(
                            text = "Cerrar Sesión",
                            style = MaterialTheme.typography.labelLarge,
                            modifier = Modifier.padding(start = 10.dp)
                        )
                    }
                }
            }
        },
        content = content
    )
}