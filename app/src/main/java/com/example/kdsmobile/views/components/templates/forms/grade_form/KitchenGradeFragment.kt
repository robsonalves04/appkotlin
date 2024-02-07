package com.example.kdsmobile.views.components.templates.forms.grade_form

import android.content.Context
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kdsmobile.viewmodels.kitchen_fragment.KitchenFragmentSlider
import com.example.kdsmobile.viewmodels.kitchen_fragment.KitchenFragmentsViewModel
import com.example.kdsmobile.viewmodels.kitchen_grade.KitchenGradeViewModel
import com.example.kdsmobile.viewmodels.kitchen_pedido.KitchenPedidoViewModel
import com.example.kdsmobile.views.components.atoms.kitchen_container.KitchenWrapper
import com.example.kdsmobile.views.components.atoms.kitchen_paper.KitchenLineSpace
import com.example.kdsmobile.views.components.atoms.kitchen_paper.KitchenMargin
import com.example.kdsmobile.views.components.atoms.kitchen_table.KitchenTable
import com.example.kdsmobile.views.components.atoms.kitchen_typography.KitchenTypography
import com.example.kdsmobile.views.components.molecules.produtos.KitchenProdutoCard


@Composable
fun KitchenGradeFragment(
    viewModel: KitchenFragmentsViewModel,
    gradeViewModel: KitchenGradeViewModel,
    pedidoViewModel: KitchenPedidoViewModel,
) {
//    val grade = gradeProdutosMock
    // --== Contexto da aplicação
    val context: Context = LocalContext.current;

    val itensExibidos = gradeViewModel.produtosVazio.observeAsState()

    val grade = gradeViewModel.produtos.observeAsState()
    var page = 1

    //     --== Carregando produtos

    LaunchedEffect(Unit) {
        gradeViewModel.obterProdutos(context, page = page);
    }

    // --== Seção de categorias
    //TicketCategoriaLineList(grade.value?.categorias)

    // --== Tabela de produtos

    KitchenFragmentSlider(viewModel.gradeFragmentView.observeAsState()) {
        KitchenMargin {
            KitchenWrapper(fullHeight = true) {


                KitchenTypography(
                    text = "Mais Vendidos", size = 18.sp, weight = FontWeight(800),
                    padding = PaddingValues(start = 4.dp)
                )

                KitchenLineSpace(size = 4.dp)

                // --== Listagem de produtos
                grade.value?.produtos?.list?.let { produtos ->
                    KitchenTable(fullwidth = true,
                        items = produtos.map { item ->
                            {
                                KitchenProdutoCard(model = item, onClick = { model ->
                                    pedidoViewModel.adicionarAoCarrinho(model);
                                })
                            }
                        },
                        onReachEnd = {
                            gradeViewModel.obterProdutos(context, page = page);
                            page++

                        }
                    )
                }
            }
        }
    }
}
