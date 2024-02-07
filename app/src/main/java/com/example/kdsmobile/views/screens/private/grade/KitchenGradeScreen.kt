package com.example.kdsmobile.views.screens.private.grade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.kdsmobile.config.routes.KitchenRoutesConfig
import com.example.kdsmobile.extensions.activity.KitchenActivity
import com.example.kdsmobile.viewmodels.login.KitchenLoginViewModel
import com.example.kdsmobile.views.components.atoms.kitchen_paper.KitchenMargin
import com.example.kdsmobile.views.components.molecules.topbar.KitchenTopBar
import com.example.kdsmobile.views.components.templates.forms.grade_form.KitchenSearchForm


import org.koin.androidx.viewmodel.ext.android.viewModel

class KitchenGradeScreen  : KitchenActivity(KitchenRoutesConfig.AuthOnly) {

    private val kitchenLoginViewModel: KitchenLoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            
            KitchenTopBar(title = "SDAisndva9", )

            KitchenMargin {

            KitchenSearchForm()
            }
        }
    }
}