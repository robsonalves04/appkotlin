package com.example.kdsmobile.config.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableFloatStateOf
import com.example.kdsmobile.config.theme.KitchenWindowSize
import com.example.kdsmobile.config.theme.offsetEscondidoADireita
import com.example.kdsmobile.config.theme.offsetNavbarEscondidoPraBaixo
import com.example.kdsmobile.config.theme.offsetNavbarVisivel
import com.example.kdsmobile.config.theme.offsetTopBarEscondidoPraCima
import com.example.kdsmobile.config.theme.offsetTopBarVisivel
import com.example.kdsmobile.config.theme.offsetVisivel

data class KitchenFragmentSliderView(
    val offSet: MutableState<Float> = mutableFloatStateOf(offsetEscondidoADireita),
    val targetOffSet: MutableState<Float> = mutableFloatStateOf(offsetEscondidoADireita),
    var title : String ? = null,
    var desc : String ? = null
) {

    fun passar() {
        this.offSet.value = offsetVisivel;
        this.targetOffSet.value = offsetEscondidoADireita;

        this.offSet.value = offsetEscondidoADireita;
    }

    fun trazer() {
        this.offSet.value = offsetEscondidoADireita;
        this.targetOffSet.value = offsetVisivel;
    }

    fun atualizar(title : String, desc : String){
        this.desc = desc;
        this.title = title;
    }

    val estaVisivel: Boolean get() = targetOffSet.value == offsetVisivel
}

data class KitchenTopbarSliderView(
    val offSet: MutableState<Float> = mutableFloatStateOf(offsetTopBarEscondidoPraCima),
    val targetOffSet: MutableState<Float> = mutableFloatStateOf(offsetTopBarEscondidoPraCima),
) {
    fun mandarPraCima() {
        this.offSet.value = offsetTopBarVisivel;
        this.targetOffSet.value = offsetTopBarEscondidoPraCima;
    }

    fun puxarPraBaixo() {
        this.offSet.value = offsetTopBarEscondidoPraCima;
        this.targetOffSet.value = offsetTopBarVisivel;
    }
}

data class KitchenNavbarSliderView(
    val offSet: MutableState<Float> = mutableFloatStateOf(offsetNavbarEscondidoPraBaixo),
    val targetOffSet: MutableState<Float> = mutableFloatStateOf(offsetNavbarEscondidoPraBaixo),
) {
    fun puxarPraCima() {
        this.offSet.value = offsetNavbarEscondidoPraBaixo;
        this.targetOffSet.value = offsetNavbarVisivel;
    }

    fun mandarPraBaixo() {
        this.offSet.value = offsetNavbarVisivel;
        this.targetOffSet.value = offsetNavbarEscondidoPraBaixo;
    }
}



