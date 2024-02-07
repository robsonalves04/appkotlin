package com.example.kdsmobile.views.components.molecules.navbar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.BottomAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.RestaurantMenu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.kdsmobile.config.theme.theme
import com.example.kdsmobile.viewmodels.kitchen_cliente.KitchenClienteViewModel
import com.example.kdsmobile.viewmodels.kitchen_fragment.KitchenFragmentsNavigation
import com.example.kdsmobile.viewmodels.kitchen_fragment.KitchenFragmentsViewModel
import com.example.kdsmobile.viewmodels.kitchen_fragment.KitchenNavbarFragmentSlider
import com.example.kdsmobile.viewmodels.kitchen_pedido.KitchenPedidoViewModel
import com.example.kdsmobile.views.components.atoms.kitchen_container.KitchenWrapper
import com.example.kdsmobile.views.components.atoms.kitchen_container.KitchenWrapperAlignment
import com.example.kdsmobile.views.components.molecules.badge.KitchenCarrinhoBadge

@Composable
fun KitchenNavbar(
    viewModel: KitchenFragmentsViewModel,
    clienteViewModel: KitchenClienteViewModel? = null,
    pedidoViewModel: KitchenPedidoViewModel,
) {
    // --== Informações da tela atual
    val tela = viewModel.telaAtual.observeAsState()

    // --== Informações de visualização de navbar
    val navbarView = viewModel.navBarView.observeAsState()


    KitchenWrapper(fullHeight = true, vertical = KitchenWrapperAlignment.Center) {
        KitchenNavbarFragmentSlider(navbarView) {


            Box(modifier = Modifier.height(80.dp)) {
                // --== Barra de notificação inferior
                KitchenCarrinhoBadge(pedidoViewModel, clienteViewModel, viewModel);

                KitchenWrapper(
                    vertical = KitchenWrapperAlignment.Bottom,
                    fullWidth = true, fullHeight = true
                ) {
                    // --== Estilo para obter o posicionamento correto
                    BottomAppBar(
                        modifier = Modifier
                            .fillMaxHeight()
                            .fillMaxWidth()
                            .zIndex(100f),
                        backgroundColor = theme.colors.preto01, elevation = 5.dp
                    ) {
                        // --== Alinhando conteúdo, centralizando tudo
                        KitchenWrapper(
                            fullHeight = true,
                            fullWidth = true,
                            inline = true,
                            vertical = KitchenWrapperAlignment.Center,
                            horizontal = KitchenWrapperAlignment.Center,
                            margin = PaddingValues(bottom = 20.dp)
                        ) {
                            KitchenWrapper(
                                fullHeight = true,
                                width = 300.dp,
                                inline = true,
                                vertical = KitchenWrapperAlignment.Center,
                                horizontal = KitchenWrapperAlignment.SpaceBetween
                            ) {
                                // --== Opção de Grade de produtos
                                KitchenNavbarItem(icon = Icons.Default.RestaurantMenu, onClick = {
                                    viewModel.verGrade()
                                }, tela.value?.id == KitchenFragmentsNavigation.Grade)

                                // --== Opção de pesquisa
                                KitchenNavbarItem(icon = Icons.Default.Search, onClick = {
                                    viewModel.verGrade()
                                }, tela.value?.id == KitchenFragmentsNavigation.Pesquisa)

                                // --== Opção de carrinho
                                KitchenNavbarItem(icon = Icons.Default.ShoppingCart, onClick = {
                                    viewModel.verCarrinho()
                                }, tela.value?.id == KitchenFragmentsNavigation.Carrinho)

                                // --== Opção de busca de cliente
                                KitchenNavbarItem(icon = Icons.Default.Person, onClick = {

                                }, tela.value?.id == KitchenFragmentsNavigation.BuscarCliente)
                            }
                        }
                    }
                }
            }

        }
    }
}

