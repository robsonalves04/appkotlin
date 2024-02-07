package com.example.kdsmobile.viewmodels.kitchen_fragment

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import com.example.kdsmobile.config.screen.KitchenFragmentSliderView
import com.example.kdsmobile.config.screen.KitchenNavbarSliderView
import com.example.kdsmobile.config.screen.KitchenTopbarSliderView
import com.example.kdsmobile.views.components.atoms.kitchen_animation.KitchenPaginationContainer
import com.example.kdsmobile.views.components.atoms.kitchen_animation.KitchenPaginationContainerType


@Composable
fun KitchenFragmentSlider(
    form: State<KitchenFragmentSliderView?>,
    children: @Composable () -> Unit
) {
    form.let {
        it.value?.offSet?.value?.let { offSet ->
            it.value?.targetOffSet?.value?.let { targetOffset ->
                KitchenPaginationContainer(offSet, targetOffset) {
                    children()
                }
            }
        }
    }
}

@Composable
fun KitchenFragmentHome(
    form: State<KitchenFragmentSliderView?>,
    children: @Composable () -> Unit
) {
    form.let {
        it.value?.offSet?.value?.let { offSet ->
            it.value?.targetOffSet?.value?.let { targetOffset ->
                KitchenPaginationContainer(offSet, targetOffset) {
                    children()
                }
            }
        }
    }
}

@Composable
fun KitchenTopbarFragmentSlider(
    form: State<KitchenTopbarSliderView?>,
    children: @Composable () -> Unit
) {
    form.let {
        it.value?.offSet?.value?.let { offSet ->
            it.value?.targetOffSet?.value?.let { targetOffset ->
                KitchenPaginationContainer(offSet, targetOffset, orientation = KitchenPaginationContainerType.Vertical) {
                    children()
                }
            }
        }
    }
}

@Composable
fun KitchenNavbarFragmentSlider(
    form: State<KitchenNavbarSliderView?>,
    children: @Composable () -> Unit
) {
    form.let {
        it.value?.offSet?.value?.let { offSet ->
            it.value?.targetOffSet?.value?.let { targetOffset ->
                KitchenPaginationContainer(offSet, targetOffset, orientation = KitchenPaginationContainerType.Vertical) {
                    children()
                }
            }
        }
    }
}