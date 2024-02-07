package com.example.kdsmobile.views.components.atoms.kitchen_button

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kdsmobile.config.theme.theme
import com.example.kdsmobile.views.components.atoms.kitchen_container.KitchenWrapper
import com.example.kdsmobile.views.components.atoms.kitchen_container.KitchenWrapperAlignment
import com.example.kdsmobile.views.components.atoms.kitchen_icon.KitchenIcon
import com.example.kdsmobile.views.components.atoms.kitchen_typography.KitchenTypography

@Composable
fun KitchenButton(
    text: String? = "",
    onClick: (() -> Unit)? = {},
    textSize: TextUnit = 14.sp,
    textWeight: FontWeight? = FontWeight.Bold,
    textColor: Color? = theme.colors.preto01,
    borderColor: Color? = theme.colors.transparente,
    backgroundColor: Color? = theme.colors.transparente,
    backgroundColorDegrade: Color? = theme.colors.transparente,
    borderRadius: Dp? = (90.dp),
    fullWidth: Boolean? = true,
    fullHeight: Boolean? = true,
    heightFloat: Float ?= 1f,
    width: Dp? = 80.dp,
    height: Dp? = 48.dp,
    borderSize: Dp? = 1.dp,
    hasBorder: Boolean? = false,
    enabled: Boolean? = true,
    paddingText: PaddingValues? = PaddingValues(0.dp),
    hasIcon: Boolean? = false,
    icon: ImageVector? = Icons.Default.ArrowBack,
    iconSize: Dp? = 20.dp,
    iconColor: Color? = theme.colors.branco,
    children: (@Composable () -> Unit)? = null,
) {
    //--== Pra fazer a borda
    var border = 0.dp
    if (hasBorder == true) { border = borderSize!! }

    KitchenWrapper(
        inline = true, fullWidth = fullWidth!!, height = height!!, heightFloat = heightFloat!!,
        backgroundColor = borderColor, fullHeight = fullHeight!!, width = width!!,
        horizontal = KitchenWrapperAlignment.Center, vertical = KitchenWrapperAlignment.Center, borderRadius = borderRadius,
    ) {

        KitchenWrapper(
            fullHeight = true, clickable = enabled!!, onClick = onClick,
            backgroundColor = backgroundColor, padding = (PaddingValues(border)), fullWidth = true, inline = true,
            vertical = KitchenWrapperAlignment.Center, horizontal = KitchenWrapperAlignment.Center,
            backgroundColorDegrade = backgroundColorDegrade, borderRadius = borderRadius,
        ) {

            if (hasIcon == true) {
                KitchenWrapper {
                    KitchenIcon(
                        icon = icon!!, iconColor = iconColor,
                        size = iconSize, onClick = onClick
                    )
                }
            }
            KitchenTypography(text = text!!, style = TextStyle(fontSize = textSize), weight = textWeight, color = textColor!!, padding = paddingText)
            children?.invoke()
        }
    }
}