package com.example.kdsmobile.views.components.molecules.ordenarea

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kdsmobile.config.theme.KitchenWindowSize
import com.example.kdsmobile.config.theme.WindowType
import com.example.kdsmobile.config.theme.theme
import com.example.kdsmobile.views.components.atoms.kitchen_container.KitchenWrapper
import com.example.kdsmobile.views.components.atoms.kitchen_container.KitchenWrapperAlignment
import com.example.kdsmobile.views.components.atoms.kitchen_paper.KitchenLineSpace
import com.example.kdsmobile.views.components.atoms.kitchen_typography.KitchenTypography

@Composable
fun KitchenPedidosForm(
    icone: ImageVector? = null,
    texto: String? = null,
    qtd: String? = null,
    corQtd: Color? = theme.colors.transparente,
    borderNum: Color? = theme.colors.transparente,
    onClick: () -> Unit
) {
    val window = KitchenWindowSize()
    KitchenWrapper(clickable = true, onClick = {onClick.invoke()}, horizontal = KitchenWrapperAlignment.Center, vertical = KitchenWrapperAlignment.Center) {
        KitchenWrapper(
            width =when(window.height){
                WindowType.Expanded -> 100.dp
                WindowType.Medium -> 70.dp
                else -> 50.dp},
            height = when(window.height){
                WindowType.Expanded -> 100.dp
                WindowType.Medium -> 70.dp
                else -> 50.dp}, borderRadius = 100.dp,
            horizontal = KitchenWrapperAlignment.Center, vertical = KitchenWrapperAlignment.Center,
            borderColor = theme.colors.preto01, borderSize = 2.dp, backgroundColor = theme.colors.preto01
        ) {
            Icon(
                imageVector = icone!!, contentDescription = "menu", tint = theme.colors.verdeVibe02,
                modifier = Modifier.size(30.dp)
            )
        }
        KitchenLineSpace(size = 5.dp)
        KitchenWrapper() {
            KitchenTypography(text = texto!!, color = theme.colors.preto01, style = TextStyle(fontSize = 16.sp), resposivel = true, maxLines = 1, weight = FontWeight.SemiBold)
        }
    }
}




