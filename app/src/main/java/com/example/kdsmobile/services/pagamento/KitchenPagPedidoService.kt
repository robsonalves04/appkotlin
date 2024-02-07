package com.example.kdsmobile.services.pagamento

import android.content.Context
import com.example.kdsmobile.BuildConfig
import com.example.kdsmobile.extensions.http.serializeAndResolve
import com.example.kdsmobile.models.pedidos.KitchenPagamentoModel
import com.example.kdsmobile.models.pedidos.KitchenPedidoClientePagoModel
import com.example.kdsmobile.models.pedidos.KitchenPedidoModel
import com.example.kdsmobile.models.produto.KitchenProdutoPedirModel
import com.example.kdsmobile.services.httpclient.HttpClient
import com.example.kdsmobile.services.httpclient.KitchenAPICallback

class KitchenPagPedidoService : IKitchenPagPedidoService {

    // --== Integrador HTTP
    private val _httpClient: HttpClient = HttpClient(BuildConfig.PODSMART_API_URL)

    // --== Abrindo pedi
    override suspend fun pedir(context: Context, model: KitchenProdutoPedirModel,
                               options: KitchenAPICallback<KitchenPedidoModel>
    ) {
        _httpClient.postAsync<KitchenPedidoModel>(context = context, payload = model,
            dataType = KitchenPedidoModel::class.java, path = "api/v1/pedidos/pedir", auth = true
        ).serializeAndResolve(options);
    }

    override suspend fun pagar(context: Context, model: KitchenPagamentoModel,
                               options: KitchenAPICallback<KitchenPedidoClientePagoModel>
    ) {
        _httpClient.postAsync<KitchenPedidoClientePagoModel>(context = context, payload = model,
            dataType = KitchenPedidoClientePagoModel::class.java, path = "api/v1/pedidos/pagar", auth = true
        ).serializeAndResolve(options);
    }
}
