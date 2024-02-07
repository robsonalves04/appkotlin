package com.example.kdsmobile.models.status_pedido

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.kdsmobile.models.pedidos.KitchenTipoPedidosModel

data class KitchenStatusPedidoModel(
    var icone: ImageVector? = null,
    var texto: String? = null,
    var qtd: String? = null,
    var corQtd: Color? = null,
){}