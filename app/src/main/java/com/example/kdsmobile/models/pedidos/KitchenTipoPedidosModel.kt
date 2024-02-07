package com.example.kdsmobile.models.pedidos



data class KitchenPedidoModelMockTeste(
    var pedido : List<KitchenTipoPedidosModel>? = null
)

data class KitchenTipoPedidosModel(
    var tipo: String? = null,
    var itens: List<KitchenItensPedidoModel>? = null,
)

data class KitchenItensPedidoModel(
    var nome: String? = null,
    var valor:Double? = null,
    var complementos: List<KitchenComplementoPedidoModel>? = null,
)

data class KitchenComplementoPedidoModel(
    var complemento: String? = "",
)