package com.example.kdsmobile.views.components.molecules.produtos

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import com.example.kdsmobile.config.theme.theme
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kdsmobile.extensions.utils.toBRL
import com.example.kdsmobile.models.produto.KitchenProdutoModel
import com.example.kdsmobile.viewmodels.kitchen_pedido.KitchenPedidoViewModel
import com.example.kdsmobile.views.components.atoms.kitchen_button.KitchenCountButton
import com.example.kdsmobile.views.components.atoms.kitchen_image.KitchenImage
import com.example.kdsmobile.views.components.atoms.kitchen_typography.KitchenTypography


@Composable
fun KitchenCarrinhoProdutoCard(pedidoViewModel: KitchenPedidoViewModel, model: KitchenProdutoModel) {

    val carrinho = pedidoViewModel.carrinho.observeAsState()

    val quantidadeNoCarrinho = carrinho.value?.count { it.id == model.id } ?: 0

    Row(modifier = Modifier
        .height(85.dp)
        .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        KitchenImage(model.docImg,false)
        /*Image(
            painter = painterResource(id = R.drawable.drink),
            contentDescription = "",
            modifier = Modifier
                .size(85.dp)
                .clip(RoundedCornerShape(8.dp))
        )*/

        Spacer(modifier = Modifier.width(10.dp))

        Column(modifier = Modifier
            .height(75.dp)
            .weight(1f),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            KitchenTypography(text = "${model.titulo}", style = TextStyle(fontSize = 14.sp), maxLines = 2, resposivel = true,weight = FontWeight.Medium)
            KitchenTypography(text = "R$ ${model.valor.toBRL()}", size = 14.sp, weight = FontWeight.Medium)
        }

        Column(modifier = Modifier
            .height(85.dp)
            .width(85.dp),
            verticalArrangement = Arrangement.Bottom, horizontalAlignment = Alignment.End) {

            Column(modifier = Modifier
                .height(28.dp)
                .width(85.dp)) {
                KitchenCountButton(quantidadeNoCarrinho,
                    onIncrement = {
                        pedidoViewModel.adicionarAoCarrinho(model)
                    },
                    onDecrement = {
                        model.id?.let {pedidoViewModel.removerDoCarrinho(it)}
                    },
                    onRemove = {
                        model.id?.let { pedidoViewModel.removerDoCarrinho(it) }
                    }
                )
            }
        }
    }
    Spacer(modifier = Modifier.height((8).dp))

    Row(modifier = Modifier
        .height((1.5).dp)
        .fillMaxWidth()
        .background(color = theme.colors.cinza11)) {
    }

    Spacer(modifier = Modifier.height((8).dp))

}
