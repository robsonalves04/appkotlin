package com.example.kdsmobile.views.screens.private.produtos

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.graphics.Color
import com.example.kdsmobile.config.routes.KitchenRoutesConfig
import com.example.kdsmobile.extensions.activity.KitchenActivity
import com.example.kdsmobile.viewmodels.kitchen_fragment.KitchenFragmentsNavigationScope
import com.example.kdsmobile.viewmodels.kitchen_fragment.KitchenFragmentsViewModel
import com.example.kdsmobile.viewmodels.kitchen_grade.KitchenGradeViewModel
import com.example.kdsmobile.viewmodels.kitchen_pedido.KitchenPedidoViewModel
import com.example.kdsmobile.viewmodels.mesas.KitchenMesasViewModel
import com.example.kdsmobile.viewmodels.status_pedido.StatusPedidoViewModel
import com.example.kdsmobile.views.components.atoms.kitchen_paper.KitchenScreen
import com.example.kdsmobile.views.components.templates.forms.grade_form.KitchenGradeFragment
import com.example.kdsmobile.views.screens.private.carrinho.KitchenCarrinhoFragment
import com.example.kdsmobile.views.screens.private.home_screen.KitchenHomeFragment
import com.example.kdsmobile.views.screens.private.kitchen_carregamento_fragment.KitchenCarregamentoFragment
import com.example.kdsmobile.views.screens.private.mesas.KitchenConfirmarPedidoFragment
import com.example.kdsmobile.views.screens.private.mesas.KitchenSelecionarMesaFragment
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class KitchenProdutosScreen : KitchenActivity(KitchenRoutesConfig.AuthOnly) {

    // --== ViewModel de controle de Grade de produtos
    private val _gradeProdutosViewModel: KitchenGradeViewModel by viewModel()

    // --== ViewModel de controle de pedidos e carrinho
    private val _pedidoViewModel: KitchenPedidoViewModel by viewModel()

    // --== ViewModel de controle de navegação e interação
    private val _fragmentMainViewModel: KitchenFragmentsViewModel by viewModel()

    private val _mesasViewModel: KitchenMesasViewModel by viewModel()
    private val _status: StatusPedidoViewModel by inject()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val escopoDeVisualizacao = _fragmentMainViewModel.escopoDeVisualizacao.observeAsState()

            LaunchedEffect(Unit) {
                _fragmentMainViewModel.verHome()
            }


                KitchenScreen(viewModel = _fragmentMainViewModel, backgroundColor = Color.White, pedidoViewModel = _pedidoViewModel) {

                    Box {
                        escopoDeVisualizacao.value.let {
                            if (escopoDeVisualizacao.value == KitchenFragmentsNavigationScope.Home) {
                                KitchenHomeFragment(
                                    gradeProdutosViewModel = _gradeProdutosViewModel,
                                    statusViewModel = _status, viewModel = _fragmentMainViewModel
                                )
                            }
                            if (escopoDeVisualizacao.value == KitchenFragmentsNavigationScope.Grade) {


                                KitchenGradeFragment(
                                    viewModel = _fragmentMainViewModel, gradeViewModel = _gradeProdutosViewModel,
                                    pedidoViewModel = _pedidoViewModel
                                )
                            }
                            if (escopoDeVisualizacao.value == KitchenFragmentsNavigationScope.Carrinho) {
                                // --== Tela (Fragmento) de carrinho de compras
                                KitchenCarrinhoFragment(
                                    viewModel = _fragmentMainViewModel,
                                    pedidoViewModel = _pedidoViewModel
                                )
                            }
                            KitchenSelecionarMesaFragment(
                                _mesasViewModel,
                                viewModel = _fragmentMainViewModel, gradeViewModel = _gradeProdutosViewModel,
                                pedidoViewModel = _pedidoViewModel
                            )
                            KitchenConfirmarPedidoFragment(
                                viewModel = _fragmentMainViewModel, gradeViewModel = _gradeProdutosViewModel,
                                pedidoViewModel = _pedidoViewModel
                            )
                            KitchenCarregamentoFragment(viewModel = _fragmentMainViewModel)
                        }
                    }
                }
            }
        }
    }
