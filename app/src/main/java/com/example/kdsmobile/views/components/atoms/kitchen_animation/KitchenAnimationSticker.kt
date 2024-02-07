package com.example.kdsmobile.views.components.atoms.kitchen_animation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun KitchenAnimationSticker(
    animation : Int,
    speed : Float = 1f,
    repeat : Boolean = true
) {
    // --== Encontrando objeto de animação
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(animation))

    // -- Controlando animação
    val progress by animateLottieCompositionAsState(composition,
        iterations = if(repeat) LottieConstants.IterateForever else 1, isPlaying = true, speed = speed,
        ignoreSystemAnimatorScale = true)

    // -- Renderizando animação
    LottieAnimation(composition, progress)
}