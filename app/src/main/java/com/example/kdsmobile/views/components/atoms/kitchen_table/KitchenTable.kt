package com.example.kdsmobile.views.components.atoms.kitchen_table


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun KitchenTable(
    items: List<@Composable () -> Unit>,
    columns: Int = 3, fullwidth: Boolean = false,
    onReachEnd: () -> Unit = {}
) {

    val chunkedItems = items.chunked(columns)
    var mods = Modifier.padding(4.dp)
    if (fullwidth) mods = mods.fillMaxWidth()

    val listState = rememberLazyListState()

    LazyColumn(state = listState) {
        itemsIndexed(chunkedItems) { index, rowItems ->
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = mods,
            ) {
                for (item in rowItems) {
                    item()
                }

                if (index == chunkedItems.lastIndex && listState.isScrollInProgress) {
                    // Executando a ação quando chegar ao final da lista
                    onReachEnd.invoke()
                }
            }
        }
    }
}