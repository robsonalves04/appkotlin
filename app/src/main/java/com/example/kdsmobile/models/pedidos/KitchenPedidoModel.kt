package com.example.kdsmobile.models.pedidos


data class KitchenPedidoModel(
    val id : String? = null,
    val eventoId : String? = null,
    val valor : Double ? = null,
)


// --== Retorno de pagamento de pedido
data class KitchenPedidoClientePagoModel(
    val clienteId : String? = null,
    val pedidoId : String? = null,
)