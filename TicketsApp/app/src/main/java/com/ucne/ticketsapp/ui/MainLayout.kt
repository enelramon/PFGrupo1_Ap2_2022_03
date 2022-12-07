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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.ucne.ticketsapp.ui.components.*
import com.ucne.ticketsapp.ui.home.HomeScreen
import com.ucne.ticketsapp.ui.theme.TicketsAppLoggedTheme
import com.ucne.ticketsapp.ui.ticket.TicketListScreen
import com.ucne.ticketsapp.ui.ticket.TicketScreen
import com.ucne.ticketsapp.ui.ticket.reply.ReplyTicketDialog
import com.ucne.ticketsapp.util.Screen
import com.ucne.ticketsapp.util.filtrosTickets
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainLayout(
    userId: Int,
    inAdminMode: Boolean,
    navHostCont: NavHostController,
    viewModel: MainViewModel = hiltViewModel()
) {
    remember {
        viewModel.setCliente(userId)
        0
    }

    val colorUiState by viewModel.configUiState.collectAsState()
    val clienteUiState by viewModel.clientUiState.collectAsState()

    TicketsAppLoggedTheme(
        userColor = colorUiState.currentConfig
    ) {
        val snackbarHostState = remember { SnackbarHostState() }
        val scope = rememberCoroutineScope()

        var selectedTicketOption by rememberSaveable { mutableStateOf(-1) }
        var selectedCase by rememberSaveable { mutableStateOf(0) }
        var lastSelectedCase by rememberSaveable { mutableStateOf(0) }

        var ticketIdSelected by remember { mutableStateOf(0) }

        var navRailExpanded by rememberSaveable { mutableStateOf(false) }
        var closeTicketMode by rememberSaveable { mutableStateOf(false) }
        var editTicketMode by rememberSaveable { mutableStateOf(false) }
        var replyTicketMode by rememberSaveable { mutableStateOf(false) }

        var ticketFilterSelected by rememberSaveable { mutableStateOf(0) }

        CustomDrawer(
            userId = userId,
            user = clienteUiState.nombres,
            navToHome = { selectedCase = 0 },
            navToLogin = { navHostCont.navigate(Screen.LoginScreen.ruta) },
            isAdminMode = inAdminMode,
            navToTickets = { selectedCase = 1 },
            navToClientes = { selectedCase = 2 }
        ) {
            Scaffold(
                snackbarHost = { SnackbarHost(snackbarHostState) },
                topBar = {
                    when (selectedCase) {
                        1 -> {
                            TopBar(
                                title = when (selectedTicketOption) {
                                    5 -> {
                                        "Tickets (Modo responder)"
                                    }
                                    2 -> {
                                        "Edición de Tickets"
                                    }
                                    1 -> {
                                        "Creación de Tickets"
                                    }
                                    else -> {
                                        "Tickets"
                                    }
                                },
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
                                        .height(400.dp)
                                ) {
                                    NavigationRail(
                                        header = {//Crear ticket
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
                                        modifier = Modifier.clip(RoundedCornerShape(size = 20.dp))
                                    ) {
                                        Column(
                                            modifier = Modifier.clip(
                                                RoundedCornerShape(size = 20.dp)
                                            )
                                        ) {
                                            NavigationRailItem(//Busqueda
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
                                                    editTicketMode = false
                                                    closeTicketMode = false
                                                    replyTicketMode = false
                                                    navRailExpanded = false
                                                },
                                                modifier = Modifier.padding(vertical = 10.dp)
                                            )
                                            NavigationRailItem(
                                                icon = {
                                                    Icon(
                                                        imageVector = Icons.Default.Edit,
                                                        contentDescription = null
                                                    )
                                                },
                                                label = { Text("Editar") },
                                                selected = selectedTicketOption == 2,
                                                onClick = {
                                                    selectedTicketOption = 2
                                                    editTicketMode = true
                                                    closeTicketMode = false
                                                    replyTicketMode = false
                                                    navRailExpanded = false
                                                    scope.launch {
                                                        val job = scope.launch {
                                                            snackbarHostState.showSnackbar(
                                                                "Presione el ticket a editar",
                                                                duration = SnackbarDuration.Indefinite,
                                                            )
                                                        }
                                                        delay(1050)
                                                        job.cancel()
                                                    }
                                                },
                                                modifier = Modifier.padding(vertical = 10.dp)
                                            )

                                            NavigationRailItem(
                                                icon = {
                                                    Icon(
                                                        imageVector = Icons.Default.Reply,
                                                        contentDescription = null
                                                    )
                                                },
                                                label = { Text("Responder") },
                                                selected = replyTicketMode,
                                                onClick = {
                                                    editTicketMode = false
                                                    closeTicketMode = false
                                                    navRailExpanded = false
                                                    scope.launch {
                                                        val job = scope.launch {
                                                            snackbarHostState.showSnackbar(
                                                                "Presione el ticket a responder",
                                                                duration = SnackbarDuration.Indefinite,
                                                            )
                                                        }
                                                        delay(1050)
                                                        job.cancel()
                                                    }
                                                    replyTicketMode = true
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
                                                selected = selectedTicketOption == 6,
                                                onClick = {
                                                    selectedTicketOption = 6
                                                    editTicketMode = false
                                                    closeTicketMode = true
                                                    replyTicketMode = false
                                                    navRailExpanded = false
                                                    scope.launch {
                                                        val job = scope.launch {
                                                            snackbarHostState.showSnackbar(
                                                                "Presione el ticket a cerrar",
                                                                duration = SnackbarDuration.Indefinite,
                                                            )
                                                        }
                                                        delay(1050)
                                                        job.cancel()
                                                    }
                                                },
                                                modifier = Modifier
                                                    .padding(vertical = 10.dp)
                                            )
                                        }
                                    }
                                }
                            }//TICKETS
                            2 -> {
                                Column(verticalArrangement = Arrangement.Center) {/*NavRail de clientes*/ }
                            }//CLIENTES
                        }
                    }
                }
            ) {
                when (selectedCase) {
                    1 -> {//Tickets
                        when (selectedTicketOption) {
                            //0 abrir la busqueda
                            1 -> {
                                TicketScreen(
                                    ticketId = ticketIdSelected,
                                    onPressCancel = {
                                        selectedTicketOption = -1
                                        ticketIdSelected = 0
                                    },
                                    userId = userId
                                )
                            }
                            5 -> {
                                ReplyTicketDialog(
                                    ticketId = ticketIdSelected,
                                    clienteId = clienteUiState.clienteId
                                ) {
                                    selectedTicketOption = -1
                                }
                            }
                            else -> {
                                TicketListScreen(
                                    onRowDoubleTap = {
                                        ticketIdSelected = it
                                        selectedTicketOption = 5
                                    },
                                    onRowSelect = { ticketId ->
                                        ticketIdSelected = ticketId
                                        if (editTicketMode) {
                                            selectedTicketOption = 1
                                        } else if (closeTicketMode) {
                                            viewModel.closeTicket(ticketId)
                                        }else if(replyTicketMode){
                                            selectedTicketOption = 5
                                        }
                                    }
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
                                viewModel = viewModel
                            ) { selectedCase = lastSelectedCase }
                        } else if (selectedCase == 0) {
                            HomeScreen()
                        }
                    }
                }
            }
        }
    }
}