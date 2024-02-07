package com.example.kdsmobile.config.screen

import com.example.kdsmobile.viewmodels.kitchen_fragment.KitchenFragmentsNavigation


data class KitchenFragmentConfig(
    val id : KitchenFragmentsNavigation,
    val title : String,
    val onBack : () -> Unit
)
// --== Opções de tela de produtos
val homeFragmentConfig = KitchenFragmentConfig(
    id = KitchenFragmentsNavigation.Home,
    title = "Home",
    onBack = { }
);

// --== Opções de tela de produtos
val gradeFragmentConfig = KitchenFragmentConfig(
    id = KitchenFragmentsNavigation.Grade,
    title = "Grade de itens",
    onBack = { }
);

// --== Opções de tela de carrinho
val carrinhoFragmentConfig = KitchenFragmentConfig(
    id = KitchenFragmentsNavigation.Carrinho,
    title = "Carrinho de compras",
    onBack = { }
);

// --== Opções de tela de produtos
val selecionarMesaFragmentConfig = KitchenFragmentConfig(
    id = KitchenFragmentsNavigation.SelecionarMesa,
    title = "Mesas",
    onBack = { }
);

// --== Opções de tela de confirmar pedido
val confirmarPedidoFragmentConfig = KitchenFragmentConfig(
    id = KitchenFragmentsNavigation.ConfirmarPedido,
    title = "Confirmação de Pedido",
    onBack = { }
);