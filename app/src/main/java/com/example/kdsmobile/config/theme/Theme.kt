package com.example.kdsmobile.config.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

data class KitchenThemeColors(

    // --== Cores neutras
    val preto00: Color = Color(0xFF000000),
    val preto01: Color = Color(0xFF171717),
    val pretoSemiTransparente: Color = Color(0xA41E1E1E),

    val blur: Color = Color(0x94646363),

    val branco: Color = Color(0xFFFFFFFF),
    val paper: Color = Color(0xFFF4F4F4),
    val paper01: Color = Color(0xFFF8F9FA),
    val transparente: Color = Color(0x00000000),

    val vermelho00: Color = Color(0xEDAD0101),
    val vermelho01: Color = Color(0xFFBA0000),
    val vermelho02: Color = Color(0xFFF02727),
    val vermelhoOcupado: Color = Color(0xEDD76B6B),
    val rosa00: Color = Color(0xFFFBE4E4),

    val laranja: Color = Color(0xFFFF9900),

    val verdeVibe00: Color = Color(0xFFD3F41F),
    val verdeVibe01: Color = Color(0xFFD0F11C),
    val verdeVibe02: Color = Color(0xFFB1D100),
    val verdeConfirmar: Color = Color(0xFF346E26),
    val verdeConfirmarBorda: Color = Color(0xFF397C29),
    val verdeCadastro: Color = Color(0xFF00C1A2),
    val verdeLivre: Color = Color(0xFFC9E7CB),

    // --== Tons de cinza
    val cinza01: Color = Color(0xFFE6E6E6),
    val cinza02: Color = Color(0xFFD1D1D1),
    val cinza03: Color = Color(0xFFBDBDBD),
    val cinza04: Color = Color(0xFFA8A8A8),
    val cinza05: Color = Color(0xFF949494),
    val cinza06: Color = Color(0xFF868686),
    val cinza07: Color = Color(0xFF6B6B6B),
    val cinza08: Color = Color(0xFF545454),
    val cinza09: Color = Color(0xFF3D3D3D),
    val cinza10: Color = Color(0xFF313131),
    val cinza11: Color = Color(0xFF292929),
    val cinza12: Color = Color(0xFF202020),
    val cinza13: Color = Color(0xFF0D0D0D),
    val cinza14: Color = Color(0xFF868E96),
    val cinza15: Color = Color(0xFF676767),
)

// --== Faz a mudança entre duas cores
fun Boolean.colorChange(
    color1: Color? = theme.colors.preto00,
    color2: Color? = theme.colors.branco
): Color {
    return if (this) color1!! else color2!!
}


// --== Tema geral do Menu
data class KitchenTheme(
    val colors: KitchenThemeColors = KitchenThemeColors(),
    var height: Dp = 0.dp,
    var width: Dp = 0.dp
) : KoinComponent {

    private val dimensions: Dimensions by inject()

    init {
        this.height = dimensions.height
        this.width = dimensions.width
    }
}

data class Dimensions(
    val height: Dp,
    val width: Dp
)

// -- Variável de acesso
val theme = KitchenTheme()