package com.example.kdsmobile.views.components.atoms.kitchen_checkbox

import android.util.Log
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kdsmobile.config.theme.theme
import com.example.kdsmobile.extensions.utils.toBRL
import com.example.kdsmobile.models.produto.KitchenProdutoModel
import com.example.kdsmobile.viewmodels.kitchen_pedido.KitchenPedidoViewModel
import com.example.kdsmobile.views.components.atoms.kitchen_container.KitchenWrapper
import com.example.kdsmobile.views.components.atoms.kitchen_container.KitchenWrapperAlignment
import com.example.kdsmobile.views.components.atoms.kitchen_paper.KitchenColumnSpace
import com.example.kdsmobile.views.components.atoms.kitchen_paper.KitchenLineDivider
import com.example.kdsmobile.views.components.atoms.kitchen_paper.KitchenLineSpace
import com.example.kdsmobile.views.components.atoms.kitchen_typography.KitchenTypography

@Composable
fun KitchenCheckList(titulo:String, descricao: String, valor: String, unchecked: Color? = theme.colors.branco, corTexto: Color? = theme.colors.branco, pedidoViewModel: KitchenPedidoViewModel, model: KitchenProdutoModel = KitchenProdutoModel()) {

    var checked by remember { mutableStateOf(false) }

    val carrinho = pedidoViewModel.pedido.observeAsState()

    val ordem = pedidoViewModel.carrinho.observeAsState()

    val quantidadeNoCarrinho = ordem.value?.count { it.id == model.id } ?: 0

    KitchenWrapper {
        KitchenWrapper(inline = true, vertical = KitchenWrapperAlignment.Center) {
            Log.d("ajuda", "ajuda")

            KitchenTypography(text = titulo, color = corTexto!!, weight = FontWeight.SemiBold, tachado = checked, resposivel = true, style = TextStyle(fontSize = 16.sp), maxLines = 2)
            KitchenWrapper(fullWidth = true, horizontal = KitchenWrapperAlignment.End) {
                quantidadeNoCarrinho.let {
                    Checkbox(
                        checked = checked, onCheckedChange = { checked = it;

                            },
                        modifier = Modifier
                            .size(24.dp)
                            .clip(CircleShape),
                        colors = CheckboxDefaults.colors(
                            uncheckedColor = unchecked!!,
                            checkedColor = theme.colors.verdeVibe01,
                            checkmarkColor = theme.colors.preto01
                        )
                    )
                }
            }
        }

//        subItens.map { complementos ->
        KitchenWrapper(inline = true) {
            KitchenColumnSpace(size = theme.width / 10)
//                complementos.complemento.let {
//                    if (it != null) {
            KitchenTypography(text = descricao, color = theme.colors.cinza04, style = TextStyle(fontSize = 16.sp), resposivel = true, maxLines = 3)

//                    }
//                }
        }
//        }
        KitchenLineSpace(size = theme.height / 160)
        KitchenTypography(text = valor, color = theme.colors.preto00, style = TextStyle(fontSize = 16.sp))
    }
    KitchenLineSpace(size = 5.dp)
    KitchenLineDivider(color = theme.colors.cinza03, fullLargura = true, grossura = 1.dp)
    KitchenLineSpace(size = 5.dp)
}
