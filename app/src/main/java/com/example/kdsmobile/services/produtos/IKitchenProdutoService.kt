package com.example.kdsmobile.services.produtos

import android.content.Context
import com.example.kdsmobile.models.grade.KitchenGradeModel
import com.example.kdsmobile.models.produto.KitchenProdutoModel
import com.example.kdsmobile.models.status_pedido.KitchenStatusPedidoModel
import com.example.kdsmobile.services.httpclient.KitchenAPICallback

interface IKitchenProdutoService {
    suspend fun grade(context : Context, options : KitchenAPICallback<KitchenGradeModel>, page: Int,)

    suspend fun obterGrades(
        context: Context,
        options: KitchenAPICallback<List<KitchenProdutoModel>>
    )
}