package com.example.kdsmobile.views.screens.private.home_screen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.kdsmobile.config.routes.KitchenRoutesConfig
import com.example.kdsmobile.extensions.activity.KitchenActivity
import com.example.kdsmobile.viewmodels.kitchen_grade.KitchenGradeViewModel
import com.example.kdsmobile.viewmodels.login.KitchenLoginViewModel
import com.example.kdsmobile.viewmodels.status_pedido.StatusPedidoViewModel

import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class KitchenHomeScreen : KitchenActivity(KitchenRoutesConfig.AuthOnly) {

    private val kitchenLoginViewModel: KitchenLoginViewModel by viewModel()
    private val _status: StatusPedidoViewModel by inject()
    private val _gradeProdutosViewModel: KitchenGradeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

        }
    }
}