package com.example.kdsmobile.views.components.atoms.kitchen_animation

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.kdsmobile.views.components.atoms.kitchen_container.KitchenWrapper


@Composable
fun KitchenPaginationContainer(
    initial: Float, targetOffsetY: Float,
    orientation: KitchenPaginationContainerType? = KitchenPaginationContainerType.Horizontal,
    children: @Composable () -> Unit
) {

    val offsetY = remember { Animatable(initial) }
    LaunchedEffect(targetOffsetY) {
        offsetY.animateTo(
            targetValue = targetOffsetY,
            animationSpec = tween(
                durationMillis = 500,
                easing = FastOutSlowInEasing
            )
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .zIndex(1f)
            .then(
                if (orientation == KitchenPaginationContainerType.Horizontal)
                    Modifier.offset(x = offsetY.value.dp) else Modifier.offset(y = offsetY.value.dp)
            )
    ) {
        KitchenWrapper(fullWidth = true, fullHeight = true) {
            children()
        }
    }
}



@Composable
fun KitchenHomePaginationContainer(
    initial: Float, targetOffsetY: Float,
    orientation: KitchenPaginationContainerType? = KitchenPaginationContainerType.Horizontal,
    children: @Composable () -> Unit
) {

    val offsetY = remember { Animatable(initial) }
    LaunchedEffect(targetOffsetY) {
        offsetY.animateTo(
            targetValue = targetOffsetY,
            animationSpec = tween(
                durationMillis = 500,
                easing = FastOutSlowInEasing
            )
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .zIndex(1f)
            .then(
                if (orientation == KitchenPaginationContainerType.Horizontal)
                    Modifier.offset(x = offsetY.value.dp) else Modifier.offset(y = offsetY.value.dp)
            )
    ) {
        KitchenWrapper(fullWidth = true, fullHeight = true) {
            children()
        }
    }
}

enum class KitchenPaginationContainerType {
    Horizontal, Vertical
}
