package com.example.kdsmobile.config.theme

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp


// --== Valor de referência para conteúdo escondido à esquerda
const val offsetEscondidoAEsquerda = -500f

// --== Valor de referência para conteúdo escondido à direta
const val offsetEscondidoADireita = 500f

// --== Valor de referência para conteúdo visível
const val offsetVisivel = 0f

// --==
const val offsetTopBarEscondidoPraCima = -70f;

// --== Valor de referência para conteúdo visível de topbar
const val offsetTopBarVisivel = 0f;

// --
const val offsetNavbarEscondidoPraBaixo = -70f;

// --== Valor de referência para conteúdo visível de navbar
const val offsetNavbarVisivel = 750f;


data class WindowSize(
    val width: WindowType,
    val height: WindowType
)

enum class WindowType { Compact, Medium, Expanded }

@Composable
fun KitchenWindowSize(): WindowSize {
    val configuration = LocalConfiguration.current
    val screenWidth by remember(key1 = configuration) {
        mutableStateOf(configuration.screenWidthDp)
    }
    val screenHeight by remember(key1 = configuration) {
        mutableStateOf(configuration.screenHeightDp)
    }

    return WindowSize(
        width = getScreenWidth(screenWidth),
        height = getScreenHeight(screenHeight)
    )
}

fun getScreenWidth(width: Int): WindowType = when {
    width < 600 -> WindowType.Compact
    width < 840 -> WindowType.Medium
    else -> WindowType.Expanded
}

fun getScreenHeight(height: Int): WindowType = when {
    height < 720 -> WindowType.Compact
    height < 900 -> WindowType.Medium
    else -> WindowType.Expanded
}