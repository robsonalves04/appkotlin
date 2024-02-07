package com.example.kdsmobile.views.components.molecules.kitchen_list_carrinho

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.kdsmobile.models.produto.KitchenProdutoModel
import com.example.kdsmobile.viewmodels.kitchen_pedido.KitchenPedidoViewModel
import com.example.kdsmobile.views.components.atoms.kitchen_container.KitchenWrapper
import com.example.kdsmobile.views.components.molecules.produtos.KitchenCarrinhoProdutoCard


@Composable
fun KitchenCarrinhoList(pedidoViewModel: KitchenPedidoViewModel) {

    // --== Observando o carrinho
    val carrinho = pedidoViewModel.carrinho.observeAsState()

    // --== Armazenando itens locais do carrinho
    val _carrinho = remember {
        mutableStateOf<List<KitchenProdutoModel>>(listOf())
    }
    // --== Contexto atual de trabalho
    val context = LocalContext.current;

    LaunchedEffect(carrinho.value) {
        val variedadeDeProdutos = carrinho.value?.groupBy { it.id }?.count();
        variedadeDeProdutos?.let { variedade ->
            carrinho.value?.let { prods ->
                if (variedade > _carrinho.value.count()) {
                    _carrinho.value = prods.groupBy { it.id }.map { entry -> entry.value.first() }
                }
            }
        }
    }

        KitchenWrapper(fullHeight = true , verticalScroll = true,  margin = PaddingValues(bottom = 80.dp)) {
    _carrinho.value.sortedBy { it.titulo }.groupBy { it.id }.map { entry ->
        // --== Cartão de produto do carrinho
        KitchenCarrinhoProdutoCard(pedidoViewModel, entry.value.first())
        }
    }

}