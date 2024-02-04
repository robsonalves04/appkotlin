package com.example.portfolioapp

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

enum class AppWrapperAlignment {
    Center,End,Start,SpaceBetween,SpaceEvenly,SpaceAround,TopStart,Bottom,Top
}

@Composable
fun AppWrapper(
    fullMaxSize: Boolean = false,
    fullWidth: Boolean = false,
    fullHeight: Boolean = false,
    margin: PaddingValues = PaddingValues(0.dp),
    widthFloat: Float = 1.0f,
    heightFloat: Float = 1.0f,
    height: Dp? = null,
    width: Dp? = null,
    vertical: AppWrapperAlignment? = null,
    horizontal: AppWrapperAlignment? = null,
    box: Boolean = false,
    inline: Boolean = false,
    boxAlignment: Alignment? = Alignment.TopStart,
    backgroundColor: Color? = Color.Transparent,
    backgroundColorDegrade: Color? = backgroundColor,
    borderRadius: Dp? = 0.dp,
    borderColor: Color? = Color.Transparent,
    borderSize: Dp? = 0.dp,
    padding: PaddingValues = PaddingValues(0.dp),
    horizontalScroll: Boolean? = false,
    verticalScroll: Boolean? = false,
    clip: RoundedCornerShape? = RoundedCornerShape (0.dp),
    circleShape: Boolean = false,

    elevation: Dp = 0.dp,
    clickable: Boolean = false,
    onClick: (() -> Unit)? = null,

    content: @Composable () -> Unit,


    ) {
    val shadowModifier = if (elevation > 0.dp) {
        Modifier.shadow(elevation, RoundedCornerShape(borderRadius ?: 0.dp))
    } else {
        Modifier
    }
    val scrollState = rememberScrollState()
    var boxGuardianModifier = Modifier as Modifier;


    Box(
        modifier = if (clickable) {
            boxGuardianModifier
                .padding(padding)
                .clickable { onClick?.invoke() }
                .then(
                    if (backgroundColor != null) boxGuardianModifier.background(
                        brush = Brush.horizontalGradient(colors = listOf(backgroundColor, backgroundColorDegrade!!)),
                        shape = RoundedCornerShape(borderRadius ?: 0.dp)
                    ) else boxGuardianModifier
                )
                .then(shadowModifier)
        } else {
            boxGuardianModifier
                .padding(padding)
                .then(
                    if (backgroundColor != null) boxGuardianModifier.background(
                        brush = Brush.horizontalGradient(colors = listOf(backgroundColor, backgroundColorDegrade!!)),
                        shape = RoundedCornerShape(borderRadius ?: 0.dp)
                    ) else boxGuardianModifier
                )
                .then(shadowModifier)
        }
    )
    {
        boxGuardianModifier = boxGuardianModifier
            .wrapContentSize()
            .padding(margin)
            .clip(clip!!)
            .then(if (fullMaxSize) Modifier.fillMaxSize() else Modifier)
            .then(if (fullHeight) Modifier.fillMaxHeight(heightFloat) else Modifier)
            .then(if (fullWidth) Modifier.fillMaxWidth(widthFloat) else Modifier)
            .then(if (height != null) Modifier.height(height) else Modifier)
            .then(if (width != null) Modifier.width(width) else Modifier)
            .then(if (horizontalScroll == true) Modifier.horizontalScroll(scrollState) else Modifier)
            .then(if (verticalScroll == true) Modifier.verticalScroll(scrollState) else Modifier)
            .then(if (borderColor != null) Modifier.border(borderSize ?: 0.dp, borderColor, RoundedCornerShape(borderRadius ?: 0.dp)) else Modifier)

            .then(
                if (backgroundColor != null) Modifier.background(
                    brush = Brush.horizontalGradient(colors = listOf(backgroundColor, backgroundColorDegrade!!)),
                    shape = RoundedCornerShape(borderRadius ?: 0.dp)
                ) else Modifier
            )
            .then(if (circleShape) Modifier.clip(CircleShape) else Modifier)

        val verticalAlignment = when (vertical) {
            AppWrapperAlignment.Center -> Alignment.CenterVertically
            AppWrapperAlignment.Bottom -> Alignment.Bottom
            AppWrapperAlignment.Top -> Alignment.Top
            else -> Alignment.Top
        }

        val horizontalAlignment = when (horizontal) {
            AppWrapperAlignment.Center -> Alignment.CenterHorizontally
            AppWrapperAlignment.End -> Alignment.End
            AppWrapperAlignment.Start -> Alignment.Start
            else -> Alignment.Start
        }

        if (box) {
            Box(
                modifier = boxGuardianModifier,
                contentAlignment = boxAlignment!!
            ) {
                content.invoke()
            }
        } else {
            if (inline) {
                Row(
                    modifier = boxGuardianModifier,
                    verticalAlignment = verticalAlignment,
                    horizontalArrangement = when (horizontal) {
                        AppWrapperAlignment.Center -> Arrangement.Center
                        AppWrapperAlignment.End -> Arrangement.Center
                        AppWrapperAlignment.SpaceBetween -> Arrangement.SpaceBetween
                        AppWrapperAlignment.SpaceEvenly -> Arrangement.SpaceEvenly
                        AppWrapperAlignment.SpaceAround -> Arrangement.SpaceAround
                        AppWrapperAlignment.Start, null -> Arrangement.Start
                        AppWrapperAlignment.TopStart -> Arrangement.Start
                        else -> Arrangement.Start
                    }
                ) {
                    content.invoke()
                }
            } else {
                Column(
                    modifier = boxGuardianModifier,
                    verticalArrangement = when (vertical) {
                        AppWrapperAlignment.Center -> Arrangement.Center
                        AppWrapperAlignment.Bottom -> Arrangement.Bottom
                        AppWrapperAlignment.SpaceBetween -> Arrangement.SpaceBetween
                        AppWrapperAlignment.SpaceEvenly -> Arrangement.SpaceEvenly
                        AppWrapperAlignment.SpaceAround -> Arrangement.SpaceAround
                        AppWrapperAlignment.Top, null -> Arrangement.Top
                        AppWrapperAlignment.TopStart -> Arrangement.Top
                        else -> Arrangement.Top
                    },
                    horizontalAlignment = horizontalAlignment,
                ) {
                    content.invoke()
                }
            }
        }
    }
}
