package com.example.kdsmobile.views.components.molecules.produtos

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.TableRestaurant
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kdsmobile.config.theme.theme
import com.example.kdsmobile.extensions.utils.toBRL
import com.example.kdsmobile.models.produto.KitchenProdutoModel
import com.example.kdsmobile.viewmodels.kitchen_grade.KitchenGradeViewModel
import com.example.kdsmobile.viewmodels.kitchen_pedido.KitchenPedidoViewModel
import com.example.kdsmobile.views.components.atoms.kitchen_checkbox.KitchenCheckList
import com.example.kdsmobile.views.components.atoms.kitchen_container.KitchenWrapper
import com.example.kdsmobile.views.components.atoms.kitchen_container.KitchenWrapperAlignment
import com.example.kdsmobile.views.components.atoms.kitchen_icon.KitchenIcon
import com.example.kdsmobile.views.components.atoms.kitchen_paper.KitchenColumnSpace
import com.example.kdsmobile.views.components.atoms.kitchen_paper.KitchenLineDivider
import com.example.kdsmobile.views.components.atoms.kitchen_paper.KitchenLineSpace
import com.example.kdsmobile.views.components.atoms.kitchen_paper.KitchenMargin
import com.example.kdsmobile.views.components.atoms.kitchen_typography.KitchenTypography

@Composable
fun KitchenConfirmacaoPedido(
    pedidoViewModel: KitchenPedidoViewModel, gradeViewModel: KitchenGradeViewModel, pedidoViewModel1: KitchenPedidoViewModel
) {


    // --== Observando o carrinho
    val carrinho = pedidoViewModel.carrinho.observeAsState()

    // --== Armazenando itens locais do carrinho
    val _carrinho = remember {
        mutableStateOf<List<KitchenProdutoModel>>(listOf())
    }


    val context = LocalContext.current

    LaunchedEffect(carrinho.value) {
        val variedadeDeProdutos = carrinho.value?.groupBy { it.id }?.count();
        variedadeDeProdutos?.let { variedade ->
            carrinho.value?.let { prods ->
                if (variedade > _carrinho.value.count()) {
                    _carrinho.value = prods.groupBy { it.id }.map { entry -> entry.value.first() }
                }
            }
        }
    }
    //Cabeçalho do pagina
    KitchenWrapper {
        //-- Icone com o numero da mesa
        KitchenWrapper(fullWidth = true, horizontal = KitchenWrapperAlignment.Center, vertical = KitchenWrapperAlignment.Center) {
            KitchenLineSpace(size = 10.dp)
            KitchenWrapper(
                height = 46.dp, width = 46.dp, backgroundColor = theme.colors.verdeVibe01,
                horizontal = KitchenWrapperAlignment.Center, borderRadius = 8.dp,
                vertical = KitchenWrapperAlignment.Center
            ) {
                KitchenIcon(icon = Icons.Outlined.TableRestaurant, iconColor = theme.colors.preto01, size = 32.dp)
            }
            KitchenLineSpace(size = 10.dp)
            KitchenTypography(text = "aaaa", style = TextStyle(fontSize = 16.sp), color = theme.colors.preto01, weight = FontWeight.SemiBold)
        }
        KitchenLineSpace(size = 20.dp)
        //-- Primeira parte
        KitchenMargin {

            KitchenWrapper(fullWidth = true, verticalScroll = true, margin = PaddingValues(bottom = 80.dp)) {
                KitchenTypography(text = "Seg, 25 setembro 2023 10:50", style = TextStyle(fontSize = 15.sp), weight = FontWeight.Normal, color = theme.colors.cinza05)
                KitchenLineSpace(size = 5.dp)
                KitchenTypography(text = "Resumo do pedido", style = TextStyle(fontSize = 22.sp), weight = FontWeight.SemiBold, color = theme.colors.preto00)
                KitchenLineSpace(size = 15.dp)
//                pedidos.map { pedidoss ->
                KitchenWrapper(fullWidth = true) {
//                        pedidoss.pedido?.map { pedido ->
                    KitchenWrapper(
                        fullWidth = true,
                        height = 20.dp,
                        vertical = KitchenWrapperAlignment.Center
                    ) {
                        KitchenLineDivider(color = theme.colors.cinza03, fullLargura = true, grossura = 1.dp)
                        KitchenColumnSpace(size = 12.dp)
                        KitchenTypography(text = "2522", color = theme.colors.cinza06, style = TextStyle(fontSize = 15.sp))
                    }
                    KitchenLineSpace(size = 10.dp)
                    pedidoViewModel.carrinho.value?.map { help ->
                        help.titulo?.let { help.descricao?.let { it1 -> KitchenCheckList(titulo = it, it1, valor = help.valor.toBRL(), unchecked = theme.colors.preto01, corTexto = theme.colors.preto01, pedidoViewModel, model = KitchenProdutoModel()) } }
                    }
                    KitchenLineSpace(size = 5.dp)
                }
            }
        }
    }
}