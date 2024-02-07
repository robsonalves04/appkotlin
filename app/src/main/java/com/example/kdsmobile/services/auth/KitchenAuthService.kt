package com.example.kdsmobile.services.auth

import android.content.Context
import com.example.kdsmobile.config.keys.KitchenMemoChunks
import com.example.kdsmobile.config.keys.KitchenMemoKeys
import com.example.kdsmobile.extensions.jwt.decodeToken
import com.example.kdsmobile.extensions.navigation.KitchenScreens
import com.example.kdsmobile.extensions.navigation.navigate
import com.example.kdsmobile.models.identidade.KitchenAutenticadoModel
import com.example.kdsmobile.models.identidade.KitchenIdentidadeTokenModel
import com.example.kdsmobile.services.memo.IKitchenMemo

class KitchenAuthService(
    private val _memoService: IKitchenMemo
) : IKichentAuthService {

    // --== Modelo de token de usuário
    private var kitchenTokenModel: KitchenIdentidadeTokenModel? = null

    // --== Armazenando token de usuário
    private var kitchenRawToken: String? = null

    override fun create(context: Context) {
        // --== Encontrando token na memória
        val token = _memoService.find(
            context, key = KitchenMemoKeys.token,
            chunk = KitchenMemoChunks().identidade
        )
        this.kitchenRawToken = token
    }

    override fun isValidSession(): Boolean {
        // --== O token não é nulo, mas o modelo de desserialização é, então, farei a leitura
        if (kitchenTokenModel == null && !kitchenRawToken.isNullOrEmpty())
            this.kitchenTokenModel = kitchenRawToken!!.decodeToken()

        kitchenTokenModel?.let {
            return !it.isTokenExpired()
        }
        return false
    }

    override fun autenticar(context: Context, model: KitchenAutenticadoModel) {
        // --== Persistindo usuário em memória
        _memoService.save(
            context, chunk = KitchenMemoChunks().identidade,
            key = KitchenMemoKeys.token, value = model.accessToken
        )
        context.navigate(KitchenScreens.PedidosProntos)
    }

    override fun logout(context: Context) {
        context.navigate(KitchenScreens.Login)
    }
}
