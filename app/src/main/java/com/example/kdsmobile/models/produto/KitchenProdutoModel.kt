package com.example.kdsmobile.models.produto


data class KitchenProdutoModel(
    val id: String? = null,
    val titulo : String? = "",
    val descricao : String? = null,
    val valor : Double = 0.0,
    val docImg : String? = "",
    val codigo : String? = null,
    val ativo : Boolean? = null,
    val ean : String?= null,
    val unidade : Double = 0.0,
    val marca : String?= null,
    val fabricante : String?= null,
    val produto : String?= null,
    val validade : String?= null,
    //    val cardapioId : String? = null,
    val categoriaId : String? = null,
    val empresaId : String? = null,
)

data class KitchenProdutoPedirModel(
    val produtos : List<String>? = null
)

fun List<KitchenProdutoModel>.mapper() : KitchenProdutoPedirModel {
    return KitchenProdutoPedirModel(
        produtos = this.filter { item -> item.id != null }
            .map { item -> item.id!! }
    )
}