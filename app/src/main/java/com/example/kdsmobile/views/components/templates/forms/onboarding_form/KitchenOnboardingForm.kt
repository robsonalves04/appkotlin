package com.example.kdsmobile.views.components.templates.forms.onboarding_form

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import com.example.kdsmobile.R
import com.example.kdsmobile.config.theme.theme
import com.example.kdsmobile.viewmodels.login.KitchenLoginViewModel
import com.example.kdsmobile.views.components.atoms.kitchen_container.KitchenWrapper
import com.example.kdsmobile.views.components.atoms.kitchen_container.KitchenWrapperAlignment
import com.example.kdsmobile.views.components.atoms.kitchen_sticker.ImageSource
import com.example.kdsmobile.views.components.atoms.kitchen_sticker.KitchenSticker
import com.example.kdsmobile.views.components.organisms.onboarding.KitchenOnboarding

@Composable
fun KitchenLoginForm(viewModel: KitchenLoginViewModel) {

    KitchenWrapper(fullHeight = true, fullWidth = true, horizontal = KitchenWrapperAlignment.Center) {
        Box(modifier = Modifier.fillMaxWidth()) {
            KitchenSticker(source = ImageSource.Drawable(R.drawable.onboarding1))
                KitchenOnboarding(viewModel)
            }
        }
    }
