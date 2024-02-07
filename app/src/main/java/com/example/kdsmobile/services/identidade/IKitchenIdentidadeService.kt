package com.example.kdsmobile.services.identidade

import android.content.Context
import com.example.kdsmobile.models.identidade.KitchenAutenticadoModel
import com.example.kdsmobile.models.identidade.KitchenCredenciaisModel
import com.example.kdsmobile.services.httpclient.KitchenAPICallback

interface IKitchenIdentidadeService {

    // --== Responsável por autenticar o usuário
    suspend fun autenticar(
        context: Context, model: KitchenCredenciaisModel,
        options: KitchenAPICallback<KitchenAutenticadoModel>,
    )
}