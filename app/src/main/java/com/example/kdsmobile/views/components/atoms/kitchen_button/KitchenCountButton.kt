package com.example.kdsmobile.views.components.atoms.kitchen_button

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Remove
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kdsmobile.config.theme.KitchenTheme
import com.example.kdsmobile.config.theme.theme
import com.example.kdsmobile.views.components.atoms.kitchen_typography.KitchenTypography


@Composable
fun KitchenCountButton(
    count: Int,
    onIncrement: () -> Unit,
    onDecrement: () -> Unit,
    onRemove: () -> Unit
) {

    Box(modifier = Modifier.width(85.dp).height(28.dp)) {

        Card(backgroundColor = theme.colors.branco, contentColor = theme.colors.vermelho01,
            modifier = Modifier.matchParentSize(),
            shape = RoundedCornerShape(12), elevation = 4.dp) {

            Row(verticalAlignment = Alignment.CenterVertically,horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxSize()) {

                Column(modifier = Modifier.width(20.dp), horizontalAlignment = Alignment.CenterHorizontally) {

                    Icon(imageVector = if(count > 1) Icons.Default.Remove else Icons.Default.Delete, modifier = Modifier
                        .size(16.dp).clickable(onClick = if(count > 1) onDecrement else onRemove),
                        contentDescription = "Decrementar")
                }

                Column(modifier = Modifier.width(20.dp), horizontalAlignment = Alignment.CenterHorizontally) {

                    KitchenTypography(text = "$count",
                        color = theme.colors.cinza01, size = 14.sp)
                }

                Column(modifier = Modifier.width(20.dp), horizontalAlignment = Alignment.CenterHorizontally) {

                    Icon(imageVector = Icons.Default.Add, modifier = Modifier
                        .size(16.dp).clickable(onClick = onIncrement),
                        contentDescription = "Incrementar")
                }

            }
        }
    }
}