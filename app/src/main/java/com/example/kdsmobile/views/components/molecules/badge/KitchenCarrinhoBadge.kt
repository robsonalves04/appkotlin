package com.example.kdsmobile.views.components.molecules.badge

import android.util.Log
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kdsmobile.config.theme.theme
import com.example.kdsmobile.extensions.utils.toBRL
import com.example.kdsmobile.viewmodels.kitchen_cliente.KitchenClienteViewModel
import com.example.kdsmobile.viewmodels.kitchen_fragment.KitchenFragmentsNavigation
import com.example.kdsmobile.viewmodels.kitchen_fragment.KitchenFragmentsViewModel
import com.example.kdsmobile.viewmodels.kitchen_pedido.KitchenPedidoViewModel
import com.example.kdsmobile.views.components.atoms.kitchen_animation.KitchenOffsetContainer
import com.example.kdsmobile.views.components.atoms.kitchen_button.KitchenButton
import com.example.kdsmobile.views.components.atoms.kitchen_container.KitchenWrapper
import com.example.kdsmobile.views.components.atoms.kitchen_container.KitchenWrapperAlignment
import com.example.kdsmobile.views.components.atoms.kitchen_paper.KitchenColumnSpace
import com.example.kdsmobile.views.components.atoms.kitchen_typography.KitchenTypography

// --== Conteúdo da barra de notificação inferior para o carrinho
@Composable
fun KitchenCarrinhoBadge(
    pedidoViewModel: KitchenPedidoViewModel,
    clienteViewModel: KitchenClienteViewModel? = null,
    viewModel: KitchenFragmentsViewModel,
) {

    // --== Obtendo contexto da aplicação
    val context = LocalContext.current;

    val targetOffsetY = remember { mutableFloatStateOf(1f) }

    val offsetY = remember { Animatable(1f) }

    LaunchedEffect(targetOffsetY.floatValue) {
        offsetY.animateTo(
            targetValue = targetOffsetY.floatValue,
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioNoBouncy,
                stiffness = Spring.StiffnessLow
            )
        )
    }

    // --== Pedidos do carrinho
    val carrinho = pedidoViewModel?.carrinho?.observeAsState();

    // --== Carrinho alterado, mostrando barra de alerta
    LaunchedEffect(carrinho?.value) {
        carrinho?.value?.let {
            if (it.any()) {
                Log.d("Teste", "2")

                targetOffsetY.floatValue = -62f
            } else {
                Log.d("Teste", "1")
                targetOffsetY.floatValue = 50f
            }
        }
    }

    val actions = remember {
        mutableStateOf(
            KitchenActionModel(
                { viewModel.verCarrinho() }, "ver carrinho"
            )
        )
    }

    val telaAtual = viewModel.telaAtual.observeAsState()

    LaunchedEffect(telaAtual.value) {
        telaAtual.value?.id?.let {

            if (it == KitchenFragmentsNavigation.ConfirmarPedido)
                actions.value = KitchenActionModel(
                    { viewModel.iniciarCarregamento("Confirmando o seu pedido", "aguarde") },
                    "Finzalizar"
                )

            if (it == KitchenFragmentsNavigation.SelecionarMesa)
                actions.value = KitchenActionModel(
                    { viewModel.verConfirmarPedido() },
                    "Finzalizar"
                )

            if (it == KitchenFragmentsNavigation.Carrinho)
                actions.value = KitchenActionModel(
                    onAction = {
//                        clienteViewModel?.let {
//                            pedidoViewModel?.finalizarVenda(context, viewModel, clienteViewModel)
                        viewModel.selecionarMesa()
//                        }
                    },
                    "Selecionar mesa"
                )

            if (it == KitchenFragmentsNavigation.Grade)
                actions.value = KitchenActionModel(
                    { viewModel.verCarrinho() },
                    "Ver carrinho"
                )

        }
    }

    KitchenOffsetContainer(offsetY.value) {
        KitchenWrapper(
            inline = true, horizontal = KitchenWrapperAlignment.SpaceBetween, fullWidth = true,
            vertical = KitchenWrapperAlignment.Center, backgroundColor = theme.colors.verdeVibe01,
            height = 62.dp
        ) {
            KitchenWrapper(
                vertical = KitchenWrapperAlignment.Start,
                width = 160.dp,
                margin = PaddingValues(start = 22.dp)
            ) {
                KitchenTypography(text = "Valor Total")
                KitchenWrapper(inline = true, vertical = KitchenWrapperAlignment.Center) {

                    // --== Label de valor total
                    KitchenWrapper(inline = true, vertical = KitchenWrapperAlignment.Center) {

                        KitchenTypography(
                            text = "R$ ${carrinho?.value?.sumOf { it.valor }?.toBRL()}",
                            size = 18.sp, weight = FontWeight.Bold
                        )

                        KitchenColumnSpace(size = 4.dp)
                        KitchenTypography(
                            text = "/ ${carrinho?.value?.size} Itens",
                            color = theme.colors.preto00,
                            size = 12.sp,
                            weight = FontWeight.Medium
                        )
                    }
                }
            }
            KitchenWrapper(
                margin = PaddingValues(end = 12.dp),
                fullHeight = true,
                vertical = KitchenWrapperAlignment.Center
            ) {
                KitchenWrapper(width = 202.dp, height = 38.dp) {
                    KitchenButton(
                        actions.value.actionText,
                        backgroundColor = theme.colors.preto00,
                        backgroundColorDegrade = theme.colors.preto01,
                        onClick = { actions.value.onAction() },
                        borderRadius = (2.5).dp,
//                        color = theme.colors.branco,
                        textWeight = FontWeight.Bold,
                        textSize = 15.sp,
                        textColor = theme.colors.branco
//                        gradient = Brush
//                            .horizontalGradient(
//                                colors = listOf(
//                                    Color.Transparent,
//                                    Color.Transparent
//                                )
//                            )
                    )
                }
            }
        }
    }
}

data class KitchenActionModel(
    val onAction: () -> Unit,
    val actionText: String,
)