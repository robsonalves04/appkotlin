package com.example.kdsmobile.views.screens.private.pedidos_prontos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.kdsmobile.config.routes.KitchenRoutesConfig
import com.example.kdsmobile.extensions.activity.KitchenActivity
import com.example.kdsmobile.viewmodels.login.KitchenLoginViewModel
import com.example.kdsmobile.views.components.templates.forms.pedidos_prontos_form.KitchenPedidosProntosForm
import org.koin.androidx.viewmodel.ext.android.viewModel

class KitchenPedidosProntosScreen : KitchenActivity(KitchenRoutesConfig.AuthOnly) {

    private val kitchenLoginViewModel: KitchenLoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KitchenPedidosProntosForm()
        }
    }
}