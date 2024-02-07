package com.example.kdsmobile.views.components.atoms.kitchen_paper

import android.annotation.SuppressLint
import android.app.Activity
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.kdsmobile.config.theme.offsetNavbarVisivel
import com.example.kdsmobile.config.theme.offsetTopBarVisivel
import com.example.kdsmobile.config.theme.theme
import com.example.kdsmobile.viewmodels.kitchen_cliente.KitchenClienteViewModel
import com.example.kdsmobile.viewmodels.kitchen_fragment.KitchenFragmentsViewModel
import com.example.kdsmobile.viewmodels.kitchen_grade.KitchenGradeViewModel
import com.example.kdsmobile.viewmodels.kitchen_pedido.KitchenPedidoViewModel
import com.example.kdsmobile.views.components.atoms.kitchen_container.KitchenWrapper
import com.example.kdsmobile.views.components.molecules.navbar.KitchenNavbar
import com.example.kdsmobile.views.components.molecules.topbar.KitchenTopbar

// --== Composição para facilitar padronização de margem
@Composable
fun KitchenMargin(
    marginTop: Dp = 8.dp, marginBottom: Dp = 8.dp,
    marginLeft: Dp = 16.dp, marginRight: Dp = 16.dp, children: @Composable (() -> Unit)?,
) {
    // --== Iniciando renderização
    Column(
        modifier = Modifier.padding(
            start = marginLeft,
            end = marginRight,
            bottom = marginBottom,
            top = marginTop
        )
    ) {
        // -- Chamando o filho caso exista
        children?.invoke()
    }
}

// --== Espaçamento de linha
@Composable
fun KitchenLineSpace(
    size: Dp,
) {
    Spacer(modifier = Modifier.height(size))
}

// --== Espaçamento de coluna
@Composable
fun KitchenColumnSpace(
    size: Dp,
) {
    Spacer(modifier = Modifier.width(size))
}

@Composable
fun KitchenLineDivider(
    paddingTop: Dp? = 1.dp,
    paddingBottom: Dp? = 1.dp,
    color: Color? = theme.colors.cinza06,
    grossura: Dp? = 1.dp,
    largura: Dp? = 50.dp,
    fullLargura: Boolean? = true
) {
    KitchenLineSpace(size = paddingTop!!)
    KitchenWrapper(fullWidth = fullLargura!!, width = largura, height = grossura, backgroundColor = color) {}
    KitchenLineSpace(size = paddingBottom!!)
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "SuspiciousIndentation")
@Composable
fun KitchenScreen(
    backgroundColor: Color? = theme.colors.branco,
    pedidoViewModel: KitchenPedidoViewModel,
    clienteViewModel: KitchenClienteViewModel? = null,
    gradeViewModel: KitchenGradeViewModel? = null,
    viewModel: KitchenFragmentsViewModel,
    children: (@Composable () -> Unit)?,
) {
    // --== Contexto da composição
    val context = LocalContext.current


    // --== Informações da tela atual
    val telaAtual = viewModel.telaAtual.observeAsState()

    val showBar = telaAtual


    val topPadding by animateDpAsState(
        if (viewModel.topbarView.value?.targetOffSet?.value == offsetTopBarVisivel) {
            70.dp
        } else {
            0.dp
        }, label = "",
        animationSpec = spring(dampingRatio = Spring.DampingRatioNoBouncy, stiffness = Spring.StiffnessLow)
    )

    val bottomPadding by animateDpAsState(
        if (viewModel.navBarView.value?.targetOffSet?.value == offsetNavbarVisivel) {
            (50f).dp
        } else {
            0.dp
        }, label = "",
        animationSpec = spring(dampingRatio = Spring.DampingRatioNoBouncy, stiffness = Spring.StiffnessLow)
    )
if (showBar != viewModel.homeFragmentView){

    // --== Iniciando renderização
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = backgroundColor!!)
    ) {
        Column(modifier = Modifier.padding(top = topPadding, bottom = bottomPadding)) {
            children?.invoke()
        }
        if (children != null) {
            KitchenTopbar(telaAtual.value?.title, viewModel = viewModel,
                onIconClick = { viewModel.voltar { (context as Activity).onBackPressed() } })

            KitchenNavbar(viewModel, clienteViewModel, pedidoViewModel)
        }
    }
}
}

@Composable
fun KitchenScreenAppear(
    backgroundColor: Color? = theme.colors.branco,
    pedidoViewModel: KitchenPedidoViewModel,
    clienteViewModel: KitchenClienteViewModel? = null,
    gradeViewModel: KitchenGradeViewModel? = null,
    viewModel: KitchenFragmentsViewModel,
    children: (@Composable () -> Unit)?,
) {
    // --== Contexto da composição
    val context = LocalContext.current

    // --== Informações da tela atual
    val telaAtual = viewModel.telaAtual.observeAsState()

    // --== Iniciando renderização
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = backgroundColor!!)
    ) {
        Column(modifier = Modifier.padding(top = 1.dp, bottom = 1.dp)) {
            children?.invoke()
        }
        if (children != null) {

            Column() {
                viewModel.telaAtual
            }


        }
    }
}