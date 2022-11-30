package com.ucne.ticketsapp.ui

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.ucne.ticketsapp.data.remote.dto.ClienteDto
import com.ucne.ticketsapp.data.remote.dto.TicketsDto
import com.ucne.ticketsapp.ui.components.*
import com.ucne.ticketsapp.ui.home.HomeScreen
import com.ucne.ticketsapp.ui.theme.TicketsAppLoggedTheme
import com.ucne.ticketsapp.ui.ticket.TicketListScreen
import com.ucne.ticketsapp.ui.ticket.TicketScreen
import com.ucne.ticketsapp.util.Screen
import com.ucne.ticketsapp.util.filtrosTickets

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainLayout(
    user: ClienteDto,
    inAdminMode: Boolean,
    navHostCont: NavHostController,
    viewModel: MainViewModel = hiltViewModel()
) {

    viewModel.setCliente(user)
    val colorUiState by viewModel.configUiState.collectAsState()
    TicketsAppLoggedTheme(userColor = colorUiState.currentConfig) {
        var selectedCase by remember { mutableStateOf(0) }
        var lastSelectedCase by remember { mutableStateOf(0) }
        var navRailExpanded by remember { mutableStateOf(false) }
        var selectedTicketOption by remember { mutableStateOf(-1) }
        var ticketFilterSelected by remember { mutableStateOf(0) }

        val ticketsListUiState by viewModel.ticketListUiState.collectAsState()

        CustomDrawer(
            user = user,
            isAdminMode = inAdminMode,
            navToClientes = { selectedCase = 2 },
            navToHome = { selectedCase = 0 },
            navToLogin = { navHostCont.navigate(Screen.LoginScreen.ruta) },
            navToTickets = { selectedCase = 1 }) {
            Scaffold(
                topBar = {
                    when (selectedCase) {
                        1 -> {
                            TopBar(
                                title = "Tickets",
                                filtros = if (selectedTicketOption != 1) {
                                    filtrosTickets
                                } else null,
                                onFilterSelected = { ticketFilterSelected = it },
                                onShowMenu = { navRailExpanded = !navRailExpanded }
                            )
                        }
                        2 -> {
                            CenterAlignedTopAppBar(title = { Text("Clientes (No Disponible)") })
                        }
                        else -> {
                            CenterAlignedTopAppBar(
                                title = {
                                    Text(text = "Home")
                                },
                                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                                    containerColor = MaterialTheme.colorScheme.primaryContainer
                                )
                            )
                        }
                    }
                },
                bottomBar = {
                    BottomBar(
                        isAdminMode = inAdminMode,
                        selectedCase = selectedCase,
                        onSelectedCaseChange = {
                            lastSelectedCase = selectedCase
                            selectedCase = it
                        }
                    )
                },
                floatingActionButton = {
                    if (navRailExpanded && (selectedCase == 1 || selectedCase == 2)) {
                        when (selectedCase) {
                            1 -> {
                                Column(
                                    modifier = Modifier
                                        .height(500.dp)
                                        .offset(y = (-80).dp, x = 15.dp)

                                ) {
                                    NavigationRail(
                                        header = {
                                            FloatingActionButton(onClick = {
                                                selectedTicketOption = 1
                                                navRailExpanded = false
                                            }) {

                                                Icon(
                                                    imageVector = Icons.Default.AddCircle,
                                                    contentDescription = null
                                                )
                                            }
                                        },
                                        containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                                        modifier = Modifier.clip(
                                            RoundedCornerShape(size = 20.dp)
                                        )
                                    ) {
                                        Column(
                                            modifier = Modifier.clip(
                                                RoundedCornerShape(size = 20.dp)
                                            )
                                        ) {
                                            NavigationRailItem(
                                                icon = {
                                                    Icon(
                                                        imageVector = Icons.Default.Search,
                                                        contentDescription = null
                                                    )
                                                },
                                                label = { Text("Buscar") },
                                                selected = selectedTicketOption == 0,
                                                onClick = {
                                                    selectedTicketOption = 0
                                                    navRailExpanded = false
                                                },
                                                modifier = Modifier
                                                    .padding(vertical = 10.dp)
                                            )
                                            NavigationRailItem(
                                                icon = {
                                                    Icon(
                                                        imageVector = Icons.Default.Quickreply,
                                                        contentDescription = null
                                                    )
                                                },
                                                label = { Text("Quick Reply") },
                                                selected = selectedTicketOption == 2,
                                                onClick = {
                                                    selectedTicketOption = 2
                                                    navRailExpanded = false
                                                },
                                                modifier = Modifier
                                                    .padding(vertical = 10.dp)
                                            )

                                            NavigationRailItem(
                                                icon = {
                                                    Icon(
                                                        imageVector = Icons.Default.ReplyAll,
                                                        contentDescription = null
                                                    )
                                                },
                                                label = {
                                                    Text(
                                                        "Responder todos",
                                                        textAlign = TextAlign.Center
                                                    )
                                                },
                                                selected = selectedTicketOption == 3,
                                                onClick = {
                                                    selectedTicketOption = 3
                                                    navRailExpanded = false
                                                },
                                                modifier = Modifier

                                                    .padding(vertical = 10.dp)
                                            )


                                            NavigationRailItem(
                                                icon = {
                                                    Icon(
                                                        imageVector = Icons.Default.Reply,
                                                        contentDescription = null
                                                    )
                                                },
                                                label = { Text("Responder") },
                                                selected = selectedTicketOption == 4,
                                                onClick = {
                                                    selectedTicketOption = 4
                                                    navRailExpanded = false
                                                },
                                                modifier = Modifier
                                                    .padding(vertical = 10.dp)
                                            )

                                            NavigationRailItem(
                                                icon = {
                                                    Icon(
                                                        imageVector = Icons.Default.DisabledByDefault,
                                                        contentDescription = null
                                                    )
                                                },
                                                label = { Text("Cerrar") },
                                                selected = selectedTicketOption == 5,
                                                onClick = {
                                                    selectedTicketOption = 5
                                                    navRailExpanded = false
                                                },
                                                modifier = Modifier
                                                    .padding(vertical = 10.dp)
                                            )
                                        }

                                    }
                                }
                            }//TICKETS
                            2 -> {
                                Column(verticalArrangement = Arrangement.Center) {

                                }
                            }//CLIENTES
                        }
                    }
                }
            ) {

                when (selectedCase) {
                    1 -> {//Tickets
                        when (selectedTicketOption) {
                            1 -> {
                                TicketScreen(
                                    onPressCancel = { selectedTicketOption = -1 },
                                    user = user
                                )
                            }
                            else -> {
                                val selectedTickets: List<TicketsDto> = emptyList()
                                TicketListScreen(
                                    onReplyModeChange = {},
                                    replyMode = selectedTicketOption == 2
                                )

                            }
                        }
                    }
                    2 -> {//Clientes
                        //Disabled Temporally
                    }
                    else -> {
                        if (selectedCase == 3) {
                            ProfileDialog(
                                lastSelectedModule = lastSelectedCase,
                                viewModel = viewModel
                            ) { selectedCase = it }
                        } else if (selectedCase == 0) {
                            HomeScreen(viewModel)
                        }
                    }
                }
            }
        }
    }
}