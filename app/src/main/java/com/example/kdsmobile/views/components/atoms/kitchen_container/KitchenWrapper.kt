package com.example.kdsmobile.views.components.atoms.kitchen_container

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

enum class KitchenWrapperAlignment {
    Center,End,Start,SpaceBetween,SpaceEvenly,SpaceAround,TopStart,Bottom,Top
}
@Composable
fun KitchenWrapper(
    fullWidth: Boolean = false,
    widthFloat: Float = 1.0f,
    heightFloat: Float = 1.0f,
    fullHeight: Boolean = false,
    clickable: Boolean = false,
    onClick: (() -> Unit)? = null,
    inline: Boolean = false,
    margin: PaddingValues = PaddingValues(0.dp),
    vertical: KitchenWrapperAlignment? = null,
    horizontal: KitchenWrapperAlignment? = null,
    backgroundColor: Color? = Color.Transparent,
    backgroundColorDegrade: Color? = backgroundColor,
    backgroundDirection: Brush? = Brush.horizontalGradient(colors = listOf(backgroundColor!!, backgroundColorDegrade!!)),
    borderRadius: Dp? = 0.dp,
    borderRadiusTopRight: Dp? = 0.dp,
    borderRadiusBottomRight: Dp? = 0.dp,
    borderRadiusTopLeft: Dp? = 0.dp,
    borderRadiusBottomLeft: Dp? = 0.dp,
    height: Dp? = null,
    horizontalScroll: Boolean? = false,
    verticalScroll: Boolean? = false,
    width: Dp? = null,
    offSetX: Dp? = 0.dp,
    offSetY: Dp? = 0.dp,
    padding: PaddingValues = PaddingValues(0.dp),
    borderColor: Color? = Color.Transparent,
    borderSize: Dp? = 0.dp,
    rippleAnimationClick: Boolean? = false,
    elevation: Dp = 0.dp,

    content: @Composable () -> Unit
) {
    val shadowModifier = if (elevation > 0.dp) {
        Modifier.shadow(elevation, RoundedCornerShape(borderRadius ?: 0.dp))
    } else {
        Modifier
    }
    val interactionSource = remember { MutableInteractionSource() }
    val indication = rememberRipple()
    val scrollState = rememberScrollState()

    var boxBaseModifier = Modifier.padding()

    // --== Nescessario para poder arrendondar um canto de cada vez
    var vborderRadiusBottomLeft = borderRadiusBottomLeft;
    var vborderRadiusTopLeft = borderRadiusTopLeft
    var vborderRadiusBottomRight = borderRadiusBottomRight;
    var vborderRadiusTopRight = borderRadiusTopRight

    if (borderRadiusBottomLeft == 0.dp && borderRadiusBottomRight == 0.dp && borderRadiusTopLeft == 0.dp && borderRadiusTopRight == 0.dp) {
        vborderRadiusBottomLeft = borderRadius; vborderRadiusTopLeft = borderRadius
        vborderRadiusTopRight = borderRadius; vborderRadiusBottomRight = borderRadius
    }

    if (clickable) {
        boxBaseModifier = if (rippleAnimationClick == true) {
            boxBaseModifier.clickable(
                onClick = { onClick?.invoke() }, indication = indication, interactionSource = interactionSource
            )
        } else {
            boxBaseModifier.clickable(
                onClick = { onClick?.invoke() }, indication = null, interactionSource = interactionSource
            )
        }
    }
    Box(
        modifier = if (clickable) {
            boxBaseModifier
                .padding(padding)
                .then(
                    if (backgroundColor != null) boxBaseModifier.background(
                        brush = backgroundDirection!!,
                        shape = RoundedCornerShape(
                            vborderRadiusTopLeft!!, vborderRadiusTopRight!!,
                            vborderRadiusBottomRight!!, vborderRadiusBottomLeft ?: 0.dp
                        )
                    ) else boxBaseModifier
                )
                .then(shadowModifier)
        } else {
            boxBaseModifier
                .padding(padding)
                .then(
                    if (backgroundColor != null) boxBaseModifier.background(
                        brush = backgroundDirection!!,
                        shape = RoundedCornerShape(
                            vborderRadiusTopLeft!!,
                            vborderRadiusTopRight!!,
                            vborderRadiusBottomRight!!,
                            vborderRadiusBottomLeft ?: 0.dp
                        )
                    ) else boxBaseModifier
                )
                .then(shadowModifier)
        }
    ) {
        val baseModifier = Modifier
            .offset(x = offSetX!!, y = offSetY!!)
            .wrapContentSize()

            .padding(margin)
            .then(if (fullHeight) Modifier.fillMaxHeight(heightFloat) else Modifier)
            .then(if (fullWidth) Modifier.fillMaxWidth(widthFloat) else Modifier)
            .then(if (height != null) Modifier.height(height) else Modifier)
            .then(if (width != null) Modifier.width(width) else Modifier)
            .then(if (horizontalScroll == true) Modifier.horizontalScroll(scrollState) else Modifier)
            .then(if (verticalScroll == true) Modifier.verticalScroll(scrollState) else Modifier)
            .then(if (borderColor != null) Modifier.border(borderSize?: 0.dp,borderColor, RoundedCornerShape(borderRadius?:0.dp) ) else Modifier)
            .then(
                if (backgroundColor != null) Modifier.background(
                    brush = Brush.horizontalGradient(colors = listOf(backgroundColor, backgroundColorDegrade!!)),
                    shape = RoundedCornerShape(
                        vborderRadiusTopLeft!!,
                        vborderRadiusTopRight!!,
                        vborderRadiusBottomRight!!,
                        vborderRadiusBottomLeft ?: 0.dp
                    )
                ) else Modifier
            )

        val verticalAlignment = when (vertical) {
            KitchenWrapperAlignment.Center -> Alignment.CenterVertically
            KitchenWrapperAlignment.Bottom -> Alignment.Bottom
            KitchenWrapperAlignment.Top -> Alignment.Top
            else -> Alignment.Top
        }

        val horizontalAlignment = when (horizontal) {
            KitchenWrapperAlignment.Center -> Alignment.CenterHorizontally
            KitchenWrapperAlignment.End -> Alignment.End
            KitchenWrapperAlignment.Start -> Alignment.Start
            else -> Alignment.Start
        }

        if (inline) {
            Row(
                modifier = baseModifier,
                verticalAlignment = verticalAlignment,
                horizontalArrangement = when (horizontal) {
                    KitchenWrapperAlignment.Center -> Arrangement.Center
                    KitchenWrapperAlignment.End -> Arrangement.Center
                    KitchenWrapperAlignment.SpaceBetween -> Arrangement.SpaceBetween
                    KitchenWrapperAlignment.SpaceEvenly -> Arrangement.SpaceEvenly
                    KitchenWrapperAlignment.SpaceAround -> Arrangement.SpaceAround
                    KitchenWrapperAlignment.Start, null -> Arrangement.Start
                    KitchenWrapperAlignment.TopStart -> Arrangement.Start
                    else -> Arrangement.Start
                }
            ) {
                content()
            }
        } else {
            Column(
                modifier = baseModifier,
                verticalArrangement = when (vertical) {
                    KitchenWrapperAlignment.Center -> Arrangement.Center
                    KitchenWrapperAlignment.Bottom -> Arrangement.Bottom
                    KitchenWrapperAlignment.SpaceBetween -> Arrangement.SpaceBetween
                    KitchenWrapperAlignment.SpaceEvenly -> Arrangement.SpaceEvenly
                    KitchenWrapperAlignment.SpaceAround -> Arrangement.SpaceAround
                    KitchenWrapperAlignment.Top, null -> Arrangement.Top
                    KitchenWrapperAlignment.TopStart -> Arrangement.Top
                    else -> Arrangement.Top
                },
                horizontalAlignment = horizontalAlignment
            ) {
                content()
            }
        }
    }
}