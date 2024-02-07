package com.example.kdsmobile.views.screens.private.historico

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.kdsmobile.config.routes.KitchenRoutesConfig
import com.example.kdsmobile.extensions.activity.KitchenActivity
import com.example.kdsmobile.views.components.templates.forms.historico_form.KitchenHistoricoForm
import com.example.kdsmobile.views.components.templates.forms.perfil_form.KitchenPerfilForm

class KitchenHistoricoScreen: KitchenActivity(KitchenRoutesConfig.AuthOnly) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KitchenHistoricoForm()
        }
    }
}