package com.example.kdsmobile.services.identidade

import android.content.Context
import com.example.kdsmobile.BuildConfig
import com.example.kdsmobile.extensions.http.serializeAndResolve
import com.example.kdsmobile.models.identidade.KitchenAutenticadoModel
import com.example.kdsmobile.models.identidade.KitchenCredenciaisModel
import com.example.kdsmobile.services.httpclient.HttpClient
import com.example.kdsmobile.services.httpclient.KitchenAPICallback

class KitchenIdentidadeService : IKitchenIdentidadeService {

    private var _httpClient: HttpClient = HttpClient(BuildConfig.PODSMART_API_URL)

    override suspend fun autenticar(
        context: Context,
        model: KitchenCredenciaisModel,
        options: KitchenAPICallback<KitchenAutenticadoModel>
    ) {
        return _httpClient.postAsync<KitchenAutenticadoModel>(
            context = context, path = "api/v1/identidade/login",
            payload = model, dataType = KitchenAutenticadoModel::class.java
        ).serializeAndResolve(options)
    }
}