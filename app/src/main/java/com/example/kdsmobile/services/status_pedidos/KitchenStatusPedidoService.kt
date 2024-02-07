package com.example.kdsmobile.services.status_pedidos

import android.content.Context
import com.example.kdsmobile.config.mocks.status_pedido.statusPedidoMock
import com.example.kdsmobile.models.status_pedido.KitchenStatusPedidoModel
import com.example.kdsmobile.services.httpclient.KitchenAPICallback

class KitchenStatusPedidoService: IKitchenStatusPedidoService  {

    override suspend fun obterStatus(
        context: Context,
        options: KitchenAPICallback<List<KitchenStatusPedidoModel>>
    ){
        options.onSuccess(statusPedidoMock)
    }
}