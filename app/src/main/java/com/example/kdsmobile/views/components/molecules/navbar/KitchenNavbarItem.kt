package com.example.kdsmobile.views.components.molecules.navbar

import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.example.kdsmobile.config.theme.theme
import com.example.kdsmobile.views.components.atoms.kitchen_container.KitchenWrapper

@Composable
fun KitchenNavbarItem(
    icon: ImageVector? = null,
    onClick: (() -> Unit) ?= null,
    active : Boolean ? = false,
) {

    KitchenWrapper(clickable = true, onClick = { onClick?.invoke() }) {
        icon?.let { Icon(imageVector = it, contentDescription = "Teste", modifier = Modifier.size(28.dp),
            tint = if (active == true) theme.colors.verdeVibe01 else Color.White)
        }
    }
}