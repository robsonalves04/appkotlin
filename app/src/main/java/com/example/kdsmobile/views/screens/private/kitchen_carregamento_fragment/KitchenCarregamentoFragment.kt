package com.example.kdsmobile.views.screens.private.kitchen_carregamento_fragment

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import com.example.kdsmobile.viewmodels.kitchen_fragment.KitchenFragmentSlider
import com.example.kdsmobile.viewmodels.kitchen_fragment.KitchenFragmentsViewModel
import com.example.kdsmobile.views.components.atoms.kitchen_paper.KitchenMargin
import com.example.kdsmobile.views.components.molecules.kitchen_loading.KitchenLoader


@Composable
fun KitchenCarregamentoFragment(
    viewModel: KitchenFragmentsViewModel
) {

    val isVisible = viewModel.isCarregamentoVisible.observeAsState()

    // --== Animação de visibilidade
    val alpha by animateFloatAsState(if (isVisible.value == true) 1f else 0f, label = "")

    KitchenFragmentSlider(viewModel.carregamentoFragmentView.observeAsState()) {

        KitchenMargin {
            Box(modifier = Modifier.graphicsLayer(alpha = alpha)) {

                if(isVisible.value == true){

                    Row(modifier = Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center) {
                        // --== Carregamento
                        KitchenLoader(title = viewModel.carregamentoFragmentView.value?.title,
                            description = viewModel.carregamentoFragmentView.value?.desc)
                    }
                }
            }
        }
    }
}