package com.example.kdsmobile.views.components.atoms.kitchen_animation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex

@Composable
fun KitchenOffsetContainer(value : Float, children : (@Composable () -> Unit) ? = null) {

    Box(modifier = Modifier
        .fillMaxSize()
        .zIndex(0f)
        .offset(y = value.dp)) {
        Box(
            modifier = Modifier
                .zIndex(0f)
                .fillMaxSize()
        ) {
            children?.invoke()
        }
    }
}