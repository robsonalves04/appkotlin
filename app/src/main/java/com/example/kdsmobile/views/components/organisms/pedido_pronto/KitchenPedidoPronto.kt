package com.example.kdsmobile.views.components.organisms.pedido_pronto

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.RestaurantMenu
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kdsmobile.config.mocks.pedidos.pedidosMock
import com.example.kdsmobile.config.theme.theme
import com.example.kdsmobile.views.components.atoms.kitchen_button.KitchenButton
import com.example.kdsmobile.views.components.atoms.kitchen_container.KitchenWrapper
import com.example.kdsmobile.views.components.atoms.kitchen_container.KitchenWrapperAlignment
import com.example.kdsmobile.views.components.atoms.kitchen_icon.KitchenIcon
import com.example.kdsmobile.views.components.atoms.kitchen_paper.KitchenColumnSpace
import com.example.kdsmobile.views.components.atoms.kitchen_paper.KitchenLineDivider
import com.example.kdsmobile.views.components.atoms.kitchen_paper.KitchenLineSpace
import com.example.kdsmobile.views.components.atoms.kitchen_paper.KitchenMargin
import com.example.kdsmobile.views.components.atoms.kitchen_typography.KitchenTypography

@Composable
fun KitchenPedidoPronto() {
    val pedidos = pedidosMock

    KitchenWrapper(verticalScroll = true) {

        pedidos.map { pedidoss ->
            KitchenLineSpace(size = theme.height / 20)
            KitchenWrapper(
                fullWidth = true, borderRadius = 16.dp,
                vertical = KitchenWrapperAlignment.Center, horizontal = KitchenWrapperAlignment.Center,
                backgroundDirection = Brush.verticalGradient(colors = listOf(theme.colors.cinza12, theme.colors.cinza13))
            ) {
                KitchenWrapper(
                    offSetY = theme.height / -30, fullWidth = true,
                    vertical = KitchenWrapperAlignment.Center, horizontal = KitchenWrapperAlignment.Center,
                ) {
                    KitchenWrapper(
                        height = theme.height / 13, width = theme.width / 2,
                        backgroundColor = theme.colors.vermelho00, inline = true, borderRadius = 16.dp,
                        vertical = KitchenWrapperAlignment.Center, horizontal = KitchenWrapperAlignment.SpaceEvenly
                    ) {
                        KitchenIcon(icon = Icons.Default.RestaurantMenu, size = 24.dp)
                        KitchenTypography(text = "Pedido Pronto", color = theme.colors.branco, size = 6.sp)
                    }

                    KitchenMargin {
                        KitchenWrapper(inline = true, fullWidth = true, horizontal = KitchenWrapperAlignment.SpaceBetween) {

                            KitchenWrapper {
                                KitchenTypography(text = "Mesa", color = theme.colors.cinza05, size = (-2).sp)
                                KitchenLineSpace(size = theme.height / 120)
                                KitchenTypography(text = "012", color = theme.colors.branco, size = 6.sp)
                            }

                            KitchenWrapper {
                                KitchenTypography(text = "Comanda", color = theme.colors.cinza05, size = (-2).sp)
                                KitchenLineSpace(size = theme.height / 120)
                                KitchenTypography(text = "000123", color = theme.colors.branco, size = 6.sp)
                            }

                            KitchenWrapper {
                                KitchenTypography(text = "Número do Pedido", color = theme.colors.cinza05, size = (-2).sp)
                                KitchenLineSpace(size = theme.height / 120)
                                KitchenTypography(text = "N°176", color = theme.colors.branco, size = 6.sp)
                            }
                        }
                        KitchenLineSpace(size = theme.height / 80)

                        pedidoss.pedido?.map { pedido ->
                            KitchenWrapper(
                                fullWidth = true,
                                height = theme.height / 20,
                                backgroundColor = theme.colors.cinza08,
                                borderRadius = 8.dp, inline = true,
                                vertical = KitchenWrapperAlignment.Center
                            ) {
                                KitchenColumnSpace(size = theme.width / 12)
                                pedido.tipo?.let { KitchenTypography(text = it, color = theme.colors.branco, size = 6.sp) }
                            }

                            pedido.itens?.map { item ->
                                KitchenWrapper {
                                    KitchenLineSpace(size = theme.height / 120)
//                                    item.nome?.let { item.complementos?.let { it1 -> KitchenCheckList(3it, 18.00,unchecked = theme.colors.branco, corTexto = theme.colors.branco,  it1) } }
                                    KitchenLineSpace(size = theme.height / 120)
                                }
                            }
                        }
                        KitchenLineSpace(size = theme.height / 40)
                        KitchenMargin(marginLeft = theme.width / 20, marginRight = theme.width / 20) {
                            KitchenButton(
                                backgroundColor = theme.colors.verdeVibe01, fullHeight = false,
                                backgroundColorDegrade = theme.colors.verdeVibe01, hasIcon = true,
                                height = theme.height / 13, text = " Finalizar", icon = Icons.Default.Check,
                                iconColor = theme.colors.preto00, borderRadius = 8.dp, iconSize = 20.dp, textSize = 18.sp,
                            ) {}
                        }
                        KitchenLineSpace(size = theme.height / 40)
                    }
                }
            }
            KitchenLineSpace(size = theme.height / 40)
            KitchenLineDivider(grossura = theme.height / 120)
        }
    }
}