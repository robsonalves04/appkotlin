package com.example.kdsmobile.views.components.templates.forms.home_form

import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.example.kdsmobile.config.theme.theme
import com.example.kdsmobile.viewmodels.kitchen_fragment.KitchenFragmentsViewModel
import com.example.kdsmobile.views.components.atoms.kitchen_button.KitchenButton
import com.example.kdsmobile.views.components.atoms.kitchen_container.KitchenWrapper
import com.example.kdsmobile.views.components.atoms.kitchen_container.KitchenWrapperAlignment
import kotlin.math.roundToInt

@Composable
fun KitchenDrawerTouch(viewModel: KitchenFragmentsViewModel,) {
    val context = LocalContext.current
    Box(modifier = Modifier.fillMaxSize()) {
        var offsetX by remember { mutableStateOf(0f) }
        var offsetY by remember { mutableStateOf(0f) }

        KitchenWrapper(fullWidth = true, fullHeight = true, vertical = KitchenWrapperAlignment.Bottom, horizontal = KitchenWrapperAlignment.End) {
            Box(
                Modifier
                    .offset { IntOffset(offsetX.roundToInt(), offsetY.roundToInt()) }
                    .pointerInput(Unit) {
                        detectDragGestures { change, dragAmount ->
                            change.consume()
                            val newX = (offsetX + dragAmount.x).coerceIn(0f, size.width - 100f)
                            val newY = (offsetY + dragAmount.y).coerceIn(0f, size.height - 100f)
                            offsetX = newX
                            offsetY = newY
                        }
                    }
            ) {
                KitchenButton(
                    onClick={viewModel.verGrade()},
                    backgroundColor = theme.colors.verdeVibe01.copy(alpha = 0.5f), backgroundColorDegrade = theme.colors.verdeVibe02.copy(alpha = 0.5f), borderRadius = 100.dp, width = 70.dp, height = 70.dp,
                    icon = Icons.Default.Add, iconSize = 35.dp, iconColor = theme.colors.preto00, hasIcon = true, fullHeight = false, fullWidth = false, hasBorder = true
                ) {}

            }
        }
    }
}