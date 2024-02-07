package com.example.kdsmobile.views.screens.private.carrinho

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import com.example.kdsmobile.viewmodels.kitchen_fragment.KitchenFragmentSlider
import com.example.kdsmobile.viewmodels.kitchen_fragment.KitchenFragmentsViewModel
import com.example.kdsmobile.viewmodels.kitchen_pedido.KitchenPedidoViewModel
import com.example.kdsmobile.views.components.atoms.kitchen_paper.KitchenMargin

import com.example.kdsmobile.views.components.molecules.kitchen_list_carrinho.KitchenCarrinhoList


@Composable
fun KitchenCarrinhoFragment(
    viewModel: KitchenFragmentsViewModel,
    pedidoViewModel: KitchenPedidoViewModel,

) {
    KitchenFragmentSlider(viewModel.carrinhoFragmentView.observeAsState()){
        KitchenMargin {
            KitchenCarrinhoList(pedidoViewModel);
        }
    }
}