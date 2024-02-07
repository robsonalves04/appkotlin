package com.example.kdsmobile.services.status_pedidos

import android.content.Context
import com.example.kdsmobile.models.status_pedido.KitchenStatusPedidoModel
import com.example.kdsmobile.services.httpclient.KitchenAPICallback

interface IKitchenStatusPedidoService {

    suspend fun obterStatus(
        context: Context,
        options: KitchenAPICallback<List<KitchenStatusPedidoModel>>
    )
}