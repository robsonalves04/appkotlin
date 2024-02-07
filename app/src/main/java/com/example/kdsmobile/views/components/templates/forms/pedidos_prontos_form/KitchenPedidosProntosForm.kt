package com.example.kdsmobile.views.components.templates.forms.pedidos_prontos_form

import androidx.compose.runtime.Composable
import com.example.kdsmobile.config.theme.theme
import com.example.kdsmobile.views.components.atoms.kitchen_container.KitchenWrapper
import com.example.kdsmobile.views.components.atoms.kitchen_paper.KitchenMargin
import com.example.kdsmobile.views.components.molecules.topbar.KitchenTopBar
import com.example.kdsmobile.views.components.organisms.pedido_pronto.KitchenPedidoPronto

@Composable
fun KitchenPedidosProntosForm() {

    KitchenWrapper {
        KitchenTopBar(title = "Pedidos Finalizados")
        KitchenWrapper(fullWidth = true, backgroundColor = theme.colors.preto00) {
            KitchenMargin {
                KitchenPedidoPronto()
            }
        }
    }
}
