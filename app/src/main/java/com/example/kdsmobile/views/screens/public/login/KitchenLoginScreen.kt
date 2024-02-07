package com.example.kdsmobile.views.screens.public.login

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.kdsmobile.config.routes.KitchenRoutesConfig
import com.example.kdsmobile.extensions.activity.KitchenActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.example.kdsmobile.viewmodels.login.KitchenLoginViewModel
import com.example.kdsmobile.views.components.templates.forms.onboarding_form.KitchenLoginForm

class KitchenLoginScreen : KitchenActivity(KitchenRoutesConfig.AnonymousOnly) {

    private val kitchenLoginViewModel: KitchenLoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KitchenLoginForm(viewModel = kitchenLoginViewModel )
        }
    }
}