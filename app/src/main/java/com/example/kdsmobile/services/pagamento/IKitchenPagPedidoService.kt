package com.example.kdsmobile.services.pagamento

import android.content.Context
import com.example.kdsmobile.models.pedidos.KitchenPagamentoModel
import com.example.kdsmobile.models.pedidos.KitchenPedidoClientePagoModel
import com.example.kdsmobile.models.pedidos.KitchenPedidoModel
import com.example.kdsmobile.models.produto.KitchenProdutoPedirModel
import com.example.kdsmobile.services.httpclient.KitchenAPICallback

interface IKitchenPagPedidoService {
    suspend fun pagar(context: Context, model: KitchenPagamentoModel,
                      options: KitchenAPICallback<KitchenPedidoClientePagoModel>
    );

    suspend fun pedir(context: Context, model: KitchenProdutoPedirModel,
                      options: KitchenAPICallback<KitchenPedidoModel>)

}