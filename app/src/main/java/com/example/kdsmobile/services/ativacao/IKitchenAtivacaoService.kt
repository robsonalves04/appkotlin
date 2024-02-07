package com.example.kdsmobile.services.ativacao

import android.content.Context
import com.example.kdsmobile.models.ativacao.KitchenAtivacaoAppKeyModel
import com.example.kdsmobile.models.ativacao.KitchenAtivarModel
import com.example.kdsmobile.models.ativacao.KitchenProntoParaAtivarModel
import com.example.kdsmobile.services.httpclient.KitchenAPICallback

interface IKitchenAtivacaoService {
    suspend fun prontoParaAtivar(
        context: Context, model: KitchenProntoParaAtivarModel,
        options: KitchenAPICallback<Any>
    );

    suspend fun ativar(
        context: Context, model: KitchenAtivarModel,
        options: KitchenAPICallback<KitchenAtivacaoAppKeyModel>
    );
}
