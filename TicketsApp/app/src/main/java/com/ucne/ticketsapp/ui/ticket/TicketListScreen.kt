package com.ucne.ticketsapp.ui.ticket

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel


@Composable
fun TicketListScreen(
    onRowSelect: (Int) -> Unit,
    onRowDoubleTap: (Int) -> Unit,
    viewModel: TicketListViewModel = hiltViewModel()
) {
    viewModel.refresh()
    val uiState = viewModel.ticketPackageListUiState.collectAsState()


    LazyColumn(
        Modifier.padding(
            top = 70.dp,
            bottom = 80.dp,
            start = 10.dp, end = 10.dp
        )
    ) {
        uiState.value.list.forEach {
            item {
                TicketRow(
                    onDoubleTap = { onRowDoubleTap(it) },
                    onPress = {
                        onRowSelect(it)
                    },
                    ticket = it,
                )
            }
        }
    }
}