package com.example.kdsmobile.models.pedidos

data class KitchenPedidoProdutoCompraModel(
    val id : String ? = null,
    val titulo : String ? = null,
    val descricao : String ? = null,
    val valor : Double ? = null,
    val refImg : String ? = null,
    val codigo : String ? = null,
    val categoriaId : String ? = null,
    val impresso : Boolean ? = null,
    val pedidoId : String ? = null
)