package com.ucne.ticketsapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterAlt
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.ucne.ticketsapp.util.noRippleClickable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    title: String,
    filtros: List<String>?,
    onFilterSelected: ((Int) -> Unit)?,
    onShowMenu: () -> Unit
) {
    var showFilters by remember { mutableStateOf(false) }
    var filterSelected by remember { mutableStateOf(0) }
    Column(Modifier.fillMaxWidth()) {
        CenterAlignedTopAppBar(
            title = {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = title)
                    Row(
                        modifier = Modifier.fillMaxHeight(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        if (filtros != null) {
                            IconButton(onClick = { showFilters = true }) {
                                Icon(
                                    imageVector = Icons.Default.FilterAlt,
                                    contentDescription = null
                                )
                            }
                            IconButton(
                                onClick = onShowMenu,
                                modifier = Modifier.padding(horizontal = 10.dp)
                            ) {
                                Icon(imageVector = Icons.Default.Menu, contentDescription = null)
                            }
                        }
                    }

                }
            },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
        )
        if (showFilters) {
            FiltrosDialog(
                filtros = filtros!!,
                onFilterSelected = {
                    onFilterSelected!!
                    filterSelected = it
                },
                filterSelected = filterSelected
            ) {
                showFilters = false
            }
        }
    }
}


@Composable
private fun FiltrosDialog(
    filtros: List<String>,
    filterSelected: Int,
    onFilterSelected: (Int) -> Unit,
    onDimissRequest: () -> Unit
) {
    Dialog(onDismissRequest = {}) {
        Column(
            modifier = Modifier
                .background(
                    color = MaterialTheme.colorScheme.primaryContainer,
                    shape = RoundedCornerShape(16.dp)
                )
                .padding(horizontal = 10.dp)
        ) {
            Text(
                text = "Filtros",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp)
            )
            for (i in filtros.indices) {
                Text(
                    text = filtros[i],
                    textAlign = TextAlign.Center,
                    textDecoration = if (i == filterSelected) TextDecoration.Underline else null,
                    fontWeight = if (i == filterSelected) FontWeight.Bold else null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(2.dp)
                        .noRippleClickable {
                            onFilterSelected(i)
                            onDimissRequest()
                        }
                )
            }
        }
    }

}
