package com.example.kdsmobile.views.screens.private.mesas

import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import com.example.kdsmobile.viewmodels.kitchen_fragment.KitchenFragmentSlider
import com.example.kdsmobile.viewmodels.kitchen_fragment.KitchenFragmentsViewModel
import com.example.kdsmobile.viewmodels.kitchen_grade.KitchenGradeViewModel
import com.example.kdsmobile.viewmodels.kitchen_pedido.KitchenPedidoViewModel
import com.example.kdsmobile.viewmodels.mesas.KitchenMesasViewModel
import com.example.kdsmobile.views.components.molecules.produtos.KitchenConfirmacaoPedido
import com.example.kdsmobile.views.components.templates.forms.mesas_form.KitchenMesasForm


@Composable
fun KitchenConfirmarPedidoFragment(viewModel: KitchenFragmentsViewModel, gradeViewModel: KitchenGradeViewModel, pedidoViewModel: KitchenPedidoViewModel) {
    KitchenFragmentSlider(viewModel.confirmarPedidoFragmentView.observeAsState()) {

//            KitchenMesasForm(mesasViewModel)
        KitchenConfirmacaoPedido(pedidoViewModel = pedidoViewModel,gradeViewModel , pedidoViewModel )
    }
}
