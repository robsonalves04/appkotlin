package com.example.kdsmobile.models.grade

import com.example.kdsmobile.models.categoria.KitchenCategoriaModel
import com.example.kdsmobile.models.paged.KitchenPagedResult
import com.example.kdsmobile.models.produto.KitchenProdutoModel

data class KitchenGradeModel (

//    val categoriaEscolhida : String ? = "",
    val produtos : KitchenPagedResult<KitchenProdutoModel>,
//    val categorias : KitchenPagedResult<KitchenCategoriaModel> ,

)