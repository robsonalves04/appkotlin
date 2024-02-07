package com.example.kdsmobile.services.ativacao

import android.content.Context
import com.example.kdsmobile.BuildConfig
import com.example.kdsmobile.extensions.http.serializeAndResolve
import com.example.kdsmobile.extensions.http.serializeAndResponse
import com.example.kdsmobile.models.ativacao.KitchenAtivacaoAppKeyModel
import com.example.kdsmobile.models.ativacao.KitchenAtivarModel
import com.example.kdsmobile.models.ativacao.KitchenProntoParaAtivarModel
import com.example.kdsmobile.services.httpclient.HttpClient
import com.example.kdsmobile.services.httpclient.KitchenAPICallback

class KitchenAtivacaoService : IKitchenAtivacaoService{

    private var _httpClient: HttpClient = HttpClient(BuildConfig.PODSMART_API_URL);

    override suspend fun prontoParaAtivar(
        context: Context, model: KitchenProntoParaAtivarModel,
        options: KitchenAPICallback<Any>
    ) {
        // --== Realizando requisição para confirmar entrada em modo de ativação
        _httpClient.patchAsync<Any>(context = context,
            path = "api/v1/terminais/ativacao/feedback/pronto-para-ativar",
            payload = model, auth = true, Any::class.java
        ).serializeAndResponse(options);
    }

    override suspend fun ativar(
        context: Context, model: KitchenAtivarModel,
        options: KitchenAPICallback<KitchenAtivacaoAppKeyModel>
    ) {
        // --== Realizando requisição para confirmar entrada em modo de ativação
        _httpClient.patchAsync<KitchenAtivacaoAppKeyModel>(context = context,
            path = "api/v1/terminais/ativacao/ativar",
            payload = model, auth = true, KitchenAtivacaoAppKeyModel::class.java
        ).serializeAndResolve(options);
    }
}