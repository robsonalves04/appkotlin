package com.example.kdsmobile.models.pedidos


// --== Usado para enviar um pagamento de um pedido
data class KitchenPagamentoModel(
    var cliHashCode : String? = null,
    var cliFidId : String? = null,
    var valor : Double? = null,
    var pedidoId : String? = null,
    var appKey : String? = null,
){

    fun authorize(cliFidId: String?, pedidoId: String?){
        this.cliFidId = cliFidId;
        this.pedidoId = pedidoId;
    }

    fun pagar(valor: Double?){
        this.valor = valor;
    }
}

// --== Responsável por obter os pedidos para impressão
// GET:: /api/v1/clientes/{clienteId}/pedidos
data class KitchenPedidoProdutoModel(
    val clienteId : String? = null,
    val cliente : String? = null,
    val cpf : String? = null,
    val produtos : List<KitchenPedidoProdutoCompraModel>? = null,
)