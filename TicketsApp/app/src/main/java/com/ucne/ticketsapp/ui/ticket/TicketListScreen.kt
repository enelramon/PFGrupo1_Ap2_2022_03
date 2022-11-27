package com.ucne.ticketsapp.ui.ticket

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import com.ucne.ticketsapp.data.remote.dto.TicketsDto
import com.ucne.ticketsapp.ui.components.TicketRow


@Composable
fun TicketListScreen(
    tickets: List<TicketsDto>, selectedList: List<TicketsDto>,
    onReplyModeChange: () -> Unit,
    replyMode: Boolean,
) {
    LazyColumn {
        tickets.forEach {
            item {
                TicketRow(
                    ticket = it,
                    isSelected = selectedList.contains(it),
                    onReplyModeChange = onReplyModeChange,
                    replyMode = replyMode
                )
            }
        }
    }
}