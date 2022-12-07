package com.ucne.ticketsapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.ucne.ticketsapp.util.noRippleClickable

@Composable
fun BottomBar(
    isAdminMode: Boolean,
    selectedCase: Int,
    onSelectedCaseChange: (Int) -> Unit
) {
    BottomAppBar {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column(
                modifier = Modifier
                    .noRippleClickable { onSelectedCaseChange(1) }
                    .fillMaxHeight()
                    .widthIn(min = 60.dp)
                    .padding(start = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                if (selectedCase == 1) {
                    SelectedIconWithText(icon = Icons.Default.Sell, text = "Tickets")
                } else {
                    Icon(imageVector = Icons.Default.Sell, contentDescription = "Tickets")
                    Text(text = "Tickets")
                }
            }

            Column(
                modifier = Modifier
                    .widthIn(min = 60.dp)
                    .noRippleClickable { onSelectedCaseChange(0) }
                    .fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                if (selectedCase == 0) {
                    SelectedIconWithText(icon = Icons.Default.Home, text = "Home")
                } else {
                    Icon(imageVector = Icons.Default.Home, contentDescription = "Home")
                    Text(text = "Home")
                }
            }

            if (isAdminMode) {
                Column(
                    modifier = Modifier
                        .noRippleClickable { onSelectedCaseChange(2) }
                        .fillMaxHeight(),

                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    if (selectedCase == 2) {
                        SelectedIconWithText(icon = Icons.Default.People, text = "Clientes")
                    } else {
                        Icon(imageVector = Icons.Default.People, contentDescription = "Clientes")
                        Text(text = "Clientes")
                    }
                }
            }

            Column(
                modifier = Modifier
                    .noRippleClickable { onSelectedCaseChange(3) }
                    .fillMaxHeight()
                    .padding(end = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                if (selectedCase == 3) {
                    SelectedIconWithText(icon = Icons.Default.ManageAccounts, text = "Perfíl")
                } else {
                    Icon(imageVector = Icons.Default.ManageAccounts, contentDescription = "Configuración")
                    Text(text = "Perfíl")
                }
            }
        }
    }
}


@Composable
private fun SelectedIconWithText(
    icon: ImageVector,
    text: String
) {

    val color = (MaterialTheme.colorScheme.secondaryContainer).copy(alpha = 0.75f)
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .clip(shape = AbsoluteRoundedCornerShape(size = 20.dp))
            .background(color),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = icon, null
        )
        Text(
            text = text
        )
    }
}

