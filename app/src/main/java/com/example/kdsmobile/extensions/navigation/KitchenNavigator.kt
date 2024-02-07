package com.example.kdsmobile.extensions.navigation

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.example.kdsmobile.views.screens.private.grade.KitchenGradeScreen
import com.example.kdsmobile.views.screens.private.historico.KitchenHistoricoScreen
import com.example.kdsmobile.views.screens.private.home_screen.KitchenHomeScreen
import com.example.kdsmobile.views.screens.private.pedidos_prontos.KitchenPedidosProntosScreen
import com.example.kdsmobile.views.screens.private.perfil.KitchenPerfilScreen
import com.example.kdsmobile.views.screens.private.produtos.KitchenProdutosScreen
import com.example.kdsmobile.views.screens.public.login.KitchenLoginScreen
import com.google.gson.Gson


enum class KitchenScreens {
    Login,GradePedidos,Home,PedidosProntos,Grade,  Perfil, Historico,
}

private const val defExtra = "EXTID"

fun Context.navigate(dest: KitchenScreens, data: Any? = null, finalizarAtividade: Boolean = true) {
    val className = if (data == null) "" else data::class.qualifiedName
    val extra: String = if (className != null)
        if (className.startsWith("kotlin.") || className.startsWith("java."))
            Gson().toJson(data) else data.toString() else ""

    // -- Criando Intent com base no destino
    val intent = when (dest) {
        KitchenScreens.Login -> Intent(this, KitchenLoginScreen::class.java)
        KitchenScreens.GradePedidos-> Intent(this, KitchenProdutosScreen::class.java)
        KitchenScreens.PedidosProntos -> Intent(this, KitchenPedidosProntosScreen::class.java)
        KitchenScreens.Home -> Intent(this, KitchenHomeScreen::class.java)
        KitchenScreens.Grade -> Intent(this, KitchenGradeScreen::class.java)
        KitchenScreens.Perfil -> Intent(this, KitchenPerfilScreen::class.java)
        KitchenScreens.Historico -> Intent(this, KitchenHistoricoScreen::class.java)
    }

    // -- Adicionando extras
    intent.putExtra(defExtra, extra)

    // -- Iniciando tela
    startActivity(intent)

    // --== Finalizando a tela atual
    if (finalizarAtividade) {
        (this as? Activity)?.finish()
    }

    fun <D> navigate(context : Context, to : Class<D>) {

        val intent = Intent(context, to)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        ContextCompat.startActivity(context, intent, Bundle());
    }
}