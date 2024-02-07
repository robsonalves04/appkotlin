package com.example.kdsmobile.models.categoria

import com.example.kdsmobile.models.paged.KitchenPagedResult
import com.example.kdsmobile.models.produto.KitchenProdutoModel

data class KitchenCategoriaModel (
    var id: String,
    var titulo: String,
    var descricao: String? = null,
    var cor: String? = null,
    var refImg: String? = null,
    var empresaId: String? = null,
    val produtos : List<KitchenProdutoModel>,
)