package com.ucne.ticketsapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import java.util.*

@Composable
fun CustomDatePickerDialog(
    label: String,
    onSelectDate: (String) -> Unit,
    onDismissRequest: () -> Unit
) {
    Dialog(onDismissRequest = onDismissRequest) {
        DatePickerUI(label, onSelectDate, onDismissRequest)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerUI(
    label: String,
    onSelectDate: (String) -> Unit,
    onDismissRequest: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier.background(MaterialTheme.colorScheme.secondaryContainer)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp, horizontal = 5.dp)
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(30.dp))

            val chosenYear = remember { mutableStateOf(currentYear) }
            val chosenMonth = remember { mutableStateOf(currentMonth) }
            val chosenDay = remember { mutableStateOf(currentDay) }

            DateSelectionSection(
                onYearChosen = { chosenYear.value = it.toInt() },
                onMonthChosen = { chosenMonth.value = monthsNames.indexOf(it) + 1 },
                onDayChosen = { chosenDay.value = it.toInt() },
            )
            Spacer(modifier = Modifier.height(30.dp))

            Button(
                shape = RoundedCornerShape(5.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
                onClick = {
                    onSelectDate("${chosenDay.value}/${chosenMonth.value}/${chosenYear.value}")
                    onDismissRequest()
                }
            ) {
                Text(
                    text = "Seleccionar",
                    style = MaterialTheme.typography.labelLarge,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
fun DateSelectionSection(
    onYearChosen: (String) -> Unit,
    onMonthChosen: (String) -> Unit,
    onDayChosen: (String) -> Unit,
) {
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
    ) {
        InfiniteItemsPicker(
            items = days,
            firstIndex = Int.MAX_VALUE / 2 + (currentDay - 2),
            onItemSelected = onDayChosen
        )

        InfiniteItemsPicker(
            items = monthsNames,
            firstIndex = Int.MAX_VALUE / 2 - 4 + currentMonth,
            onItemSelected = onMonthChosen
        )

        InfiniteItemsPicker(
            items = years,
            firstIndex = Int.MAX_VALUE / 2 + (currentYear - 2360),
            onItemSelected = onYearChosen
        )
    }
}

@Composable
fun InfiniteItemsPicker(
    items: List<String>,
    firstIndex: Int,
    onItemSelected: (String) -> Unit,
) {

    val listState = rememberLazyListState(firstIndex)
    val currentValue = remember { mutableStateOf("") }

    LaunchedEffect(key1 = !listState.isScrollInProgress) {
        onItemSelected(currentValue.value)
        listState.animateScrollToItem(index = listState.firstVisibleItemIndex)
    }

    Box(modifier = Modifier.height(106.dp)) {
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            state = listState,
            content = {
                items(count = Int.MAX_VALUE, itemContent = {
                    val index = it % items.size
                    if (it == listState.firstVisibleItemIndex + 1) {
                        currentValue.value = items[index]
                    }

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = items[index],
                        modifier = Modifier.alpha(if (it == listState.firstVisibleItemIndex + 1) 1f else 0.3f),
                        style = MaterialTheme.typography.bodyLarge,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(6.dp))
                })
            }
        )
    }
}

val currentYear = Calendar.getInstance().get(Calendar.YEAR)
val currentDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
val currentMonth = Calendar.getInstance().get(Calendar.MONTH)

val years = (1950..2500).map { it.toString() }
val monthsNumber = (1..12).map { it.toString() }
val days = (1..31).map { it.toString() }
val monthsNames = listOf(
    "Jan",
    "Feb",
    "Mar",
    "Apr",
    "May",
    "Jun",
    "Jul",
    "Aug",
    "Sep",
    "Oct",
    "Nov",
    "Dec"
)