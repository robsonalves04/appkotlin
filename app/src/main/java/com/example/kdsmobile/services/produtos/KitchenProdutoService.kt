package com.example.kdsmobile.services.produtos

import android.content.Context
import com.example.kdsmobile.BuildConfig
import com.example.kdsmobile.config.mocks.carousel.carouselMock

import com.example.kdsmobile.config.mocks.status_pedido.statusPedidoMock
import com.example.kdsmobile.extensions.http.serializeAndResolve
import com.example.kdsmobile.extensions.utils.getTypeToken

import com.example.kdsmobile.models.grade.KitchenGradeModel
import com.example.kdsmobile.models.paged.KitchenPagedResult
import com.example.kdsmobile.models.produto.KitchenProdutoModel
import com.example.kdsmobile.models.status_pedido.KitchenStatusPedidoModel
import com.example.kdsmobile.services.httpclient.HttpClient
import com.example.kdsmobile.services.httpclient.KitchenAPICallback

class KitchenProdutoService : IKitchenProdutoService {
    private val _httpClient : HttpClient = HttpClient(BuildConfig.PODSMART_API_URL);

    override suspend fun grade(context : Context, options : KitchenAPICallback<KitchenGradeModel>, page: Int,) {



        _httpClient.getAsync<KitchenGradeModel>(context, "api/v1/cardapio/grade?page=1",
            true, dataType = KitchenGradeModel::class.java ).serializeAndResolve(options)
    }
//    "api/v1/qrmenu/${menuId}/grade?page=1&pageSize=0"
//
    override suspend fun obterGrades(
        context: Context,
        options: KitchenAPICallback<List<KitchenProdutoModel>>
    ){
        options.onSuccess(carouselMock)
    }
}