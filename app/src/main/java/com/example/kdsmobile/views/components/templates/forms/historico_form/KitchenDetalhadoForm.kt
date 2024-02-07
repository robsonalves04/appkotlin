package com.example.kdsmobile.views.components.templates.forms.historico_form

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.kdsmobile.config.theme.theme
import com.example.kdsmobile.views.components.atoms.kitchen_container.KitchenWrapper
import com.example.kdsmobile.views.components.atoms.kitchen_typography.KitchenTypography

@Composable
fun KitchenDetalhadoFormEROO() {
    val context = LocalContext.current
    KitchenWrapper(fullWidth = true, ) {
        KitchenWrapper {
            KitchenTypography(text = "Resumo do Pedido", color = theme.colors.preto01, size = 18.sp, weight = FontWeight.SemiBold)
        }
        
    }

}