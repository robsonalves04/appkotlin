package com.example.kdsmobile.views.components.atoms.kitchen_icon

import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.kdsmobile.config.theme.theme
import com.example.kdsmobile.views.components.atoms.kitchen_container.KitchenWrapper
import com.example.kdsmobile.views.components.atoms.kitchen_container.KitchenWrapperAlignment
import java.util.UUID

@Composable
fun KitchenIcon(
    icon: ImageVector,
    iconColor: Color? = theme.colors.branco,
    alt: String? = UUID.randomUUID().toString(),
    containerColor: Color? = theme.colors.transparente,
    onClick: (() -> Unit)? = null,
    size: Dp? = 1.dp,
    sizeMap: Map<IntRange, Dp> = mapOf(
        0..599 to 5.dp,
        600..719 to 5.dp,
        720..839 to 6.dp,
        840..959 to 6.dp,
        960..1079 to 10.dp,
        1080..1199 to 12.dp,
        1200..1319 to 14.dp,
        1320..1439 to 16.dp,
        1440..1559 to 17.dp,
        1560..Int.MAX_VALUE to 18.dp
    ),
) {

    // --== Primeira parte da resposividade
    val configuration = LocalContext.current.resources.configuration
    val screenWidthDp = configuration.screenWidthDp

    val baseFontSize = sizeMap.entries.firstOrNull { it.key.contains(screenWidthDp) }?.value
        ?: sizeMap.entries.last().value

    val adjustedIconSize = (baseFontSize.value) + size!!.value

    val iconsize = remember { mutableStateOf(adjustedIconSize.dp) }

    KitchenWrapper(
        onClick = { onClick?.invoke() }, vertical = KitchenWrapperAlignment.Center, borderRadius = 8.dp,
        clickable = true, backgroundColor = containerColor, horizontal = KitchenWrapperAlignment.Center
    ) {
        KitchenWrapper(height = iconsize.value, width = iconsize.value) {
            Icon(
                imageVector = icon, contentDescription = alt, tint = iconColor!!,
                modifier = Modifier.then(if (size != null) Modifier.size(iconsize.value) else Modifier)
            )
        }
    }
}