package com.example.kdsmobile.views.components.molecules.kitchen_loading

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kdsmobile.config.theme.theme
import com.example.kdsmobile.views.components.atoms.kitchen_container.KitchenWrapper
import com.example.kdsmobile.views.components.atoms.kitchen_container.KitchenWrapperAlignment
import com.example.kdsmobile.views.components.atoms.kitchen_paper.KitchenLineSpace
import com.example.kdsmobile.views.components.atoms.kitchen_typography.KitchenTypography


// --== Composição padronizada para consumo do Loader
@Composable
fun KitchenLoader(
    title: String? = null,
    description: String? = null,
) {
    // --==  para alinhar os elementos
    KitchenWrapper(horizontal = KitchenWrapperAlignment.Center) {
        KitchenLineSpace(size = 24.dp)
        KitchenTypography(text = title ?: "", size = 32.sp, weight = FontWeight.SemiBold)
        KitchenLineSpace(size = 48.dp)
        KitchenWrapper(inline = true, horizontal = KitchenWrapperAlignment.Center, fullWidth = true)
        {
            // --== LauchedEffect para fazer a animação funcionar
            val animatedProgress = remember { Animatable(0f) }

            LaunchedEffect(Unit) {
                animatedProgress.animateTo(
                    targetValue = 1f,
                    animationSpec = infiniteRepeatable(
                        animation = tween(durationMillis = 1500),
                        repeatMode = RepeatMode.Restart
                    )
                )
            }

            // --== Canvas para fazer colocar a animação
            Canvas(modifier = Modifier.size(120.dp)) {
                drawArc(
                    color = theme.colors.cinza04,
                    startAngle = (animatedProgress.value * 360) - 88f,
                    sweepAngle = 360f, useCenter = false, style = Stroke(32f)
                )
                drawArc(
                    color = theme.colors.verdeVibe00,
                    startAngle = (animatedProgress.value * 360) - 88f,
                    sweepAngle = animatedProgress.value * 360,
                    useCenter = false,
                    style = Stroke(width = 32f, cap = StrokeCap.Round)
                )
            }
        }
        // --== Espaçamento e Descrição
        KitchenLineSpace(size = 56.dp)
        KitchenWrapper(inline = true) {
            KitchenTypography(text = description ?: "",
                size = 22.sp,weight = FontWeight.SemiBold,
                textAlign = TextAlign.Center)
        }

        // --== Botão de Cancelar
        KitchenLineSpace(size = 56.dp)

        //TicketButton(borderRadius = 90.dp, height = 48.dp, fullWidth = true, backgroundColor = theme.colors.paper,
        //    borderColor = theme.colors.black01, borderSize = 1.dp, text = "Cancelar")

    }
}

@Composable
fun TicketBaseLoader(){
    val animatedProgress = remember { Animatable(0f) }

    LaunchedEffect(Unit) {
        animatedProgress.animateTo(
            targetValue = 1f,
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = 1500),
                repeatMode = RepeatMode.Restart
            )
        )
    }

    Canvas(modifier = Modifier.size(120.dp)) {
        drawArc(
            color = theme.colors.cinza04,
            startAngle = (animatedProgress.value * 360) - 88f,
            sweepAngle = 360f, useCenter = false, style = Stroke(32f)
        )
        drawArc(
            color = theme.colors.verdeVibe00,
            startAngle = (animatedProgress.value * 360) - 88f,
            sweepAngle = animatedProgress.value * 360,
            useCenter = false,
            style = Stroke(width = 32f, cap = StrokeCap.Round)
        )
    }
}