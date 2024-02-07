package com.example.kdsmobile.views.components.molecules.produtos

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kdsmobile.config.theme.KitchenWindowSize
import com.example.kdsmobile.config.theme.WindowType
import com.example.kdsmobile.config.theme.theme
import com.example.kdsmobile.models.produto.KitchenProdutoModel
import com.example.kdsmobile.views.components.atoms.kitchen_container.KitchenWrapper
import com.example.kdsmobile.views.components.atoms.kitchen_container.KitchenWrapperAlignment
import com.example.kdsmobile.views.components.atoms.kitchen_image.KitchenImage
import com.example.kdsmobile.views.components.atoms.kitchen_paper.KitchenLineSpace
import com.example.kdsmobile.views.components.atoms.kitchen_paper.KitchenMargin
import com.example.kdsmobile.views.components.atoms.kitchen_typography.KitchenTypography

@Composable
fun KitchenCarouselCard(imagem: String, titulo: String, valor: Double, page: Int, onClick: ((model: KitchenProdutoModel) -> Unit)) {
    KitchenWrapper() {
        val window = KitchenWindowSize()

        KitchenWrapper(
            width = when (window.height) {
                WindowType.Expanded -> 250.dp
                WindowType.Medium -> 250.dp
                else -> 180.dp
            },
            height = when (window.height) {
                WindowType.Expanded -> 300.dp
                WindowType.Medium -> 300.dp
                else -> 120.dp
            },
            clickable = true,
            onClick = { onClick?.invoke(KitchenProdutoModel()) }) {
            // --== Imagem do produto
            Box {

                KitchenImage(imagem, true)

                //
                KitchenWrapper(
                    fullHeight = true, fullWidth = true, vertical = KitchenWrapperAlignment.Bottom, backgroundDirection = Brush.verticalGradient(
                        listOf(theme.colors.transparente, theme.colors.preto00)
                    )
                ) {
                    KitchenMargin {
                        KitchenLineSpace(size = 4.dp)
                        // --== Nome do produto
                        KitchenTypography(
                            text = "${titulo}", color = theme.colors.cinza01, style = TextStyle(fontSize = 18.sp), maxLines = 2, resposivel = true,
                            weight = FontWeight.SemiBold, lineHeight = 15.sp
                        )
                        // --== Categoria, escrita por extenso
                        KitchenTypography(text = "Produto", color = theme.colors.cinza01)

                        // --== Valor do produto
                        KitchenTypography(text = "R$ ${valor}", style = TextStyle(fontSize = 18.sp), weight = FontWeight.Bold, color = theme.colors.cinza01)
                    }
                }
            }
        }
    }
}

