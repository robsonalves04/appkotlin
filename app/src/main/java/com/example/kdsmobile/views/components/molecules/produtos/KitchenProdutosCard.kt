package com.example.kdsmobile.views.components.molecules.produtos

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kdsmobile.config.theme.theme
import com.example.kdsmobile.models.produto.KitchenProdutoModel
import com.example.kdsmobile.views.components.atoms.kitchen_container.KitchenWrapper
import com.example.kdsmobile.views.components.atoms.kitchen_image.KitchenImage
import com.example.kdsmobile.views.components.atoms.kitchen_paper.KitchenLineSpace
import com.example.kdsmobile.views.components.atoms.kitchen_typography.KitchenTypography

@Composable
fun KitchenProdutoCard(model: KitchenProdutoModel, onClick: ((model: KitchenProdutoModel) -> Unit)? = null) {
    KitchenWrapper(width = 100.dp, height = 180.dp, clickable = true, onClick = { onClick?.invoke(model) }) {
        // --== Imagem do produto
        KitchenImage(model.docImg, fillMaxSize = false)

        KitchenLineSpace(size = 4.dp)

        // --== Nome do produto
        KitchenTypography(
            text = "${model.titulo}", color = theme.colors.cinza10, style = TextStyle(fontSize = 12.sp), maxLines = 2, resposivel = true,
            weight = FontWeight.SemiBold, lineHeight = 15.sp
        )

        // --== Categoria, escrita por extenso
        KitchenTypography(text = "Produto", color = theme.colors.cinza04)

        // --== Valor do produto
        KitchenTypography(text = "R$ ${model.valor}", size = 14.sp, weight = FontWeight.Bold)
    }
}