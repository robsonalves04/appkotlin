package com.example.kdsmobile.views.components.molecules.carousel

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import com.example.kdsmobile.config.theme.KitchenWindowSize
import com.example.kdsmobile.config.theme.WindowType
import com.example.kdsmobile.config.theme.theme
import com.example.kdsmobile.models.produto.KitchenProdutoModel
import com.example.kdsmobile.viewmodels.kitchen_grade.KitchenGradeViewModel
import com.example.kdsmobile.views.components.atoms.kitchen_container.KitchenWrapper
import com.example.kdsmobile.views.components.atoms.kitchen_container.KitchenWrapperAlignment
import com.example.kdsmobile.views.components.molecules.produtos.KitchenCarouselCard
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun KitchenCarousel(_gradeProdutosViewModel: KitchenGradeViewModel, onClick: ((model: KitchenProdutoModel) -> Unit)) {
    val context = LocalContext.current
    // Usar remember para manter o estado do pager
    val pagerState = rememberPagerState(initialPage = 0)
    var page = 1
    // Utilizar resources para obter os IDs das imagens
    val scope = rememberCoroutineScope()
    val grade = _gradeProdutosViewModel.produtos.observeAsState()
    val testMock = _gradeProdutosViewModel.testeMock.observeAsState()
    val window = KitchenWindowSize()
    val itemCount = testMock.value?.size ?: 0

    //-- Redenrização do ViewModel
    LaunchedEffect(Unit) {
        _gradeProdutosViewModel.obterProdutos(context, page)
        _gradeProdutosViewModel.testeCarousel(context)
    }


    Column(
        modifier = Modifier.fillMaxSize()
    ) {
//        grade.value?.produtos?.list?.let { imageSlider ->
        testMock.value?.let { items ->
            com.google.accompanist.pager.HorizontalPager(
                count = items.size,
                state = pagerState,
                contentPadding = PaddingValues(horizontal =
                when(
                    window.height){
                    WindowType.Expanded -> 50.dp
                    WindowType.Medium -> 50.dp
                    else -> 70.dp
                }),
                modifier = Modifier
                    .height(when(window.height){
                     WindowType.Expanded -> 210.dp
                     WindowType.Medium -> 210.dp
                     else -> 130.dp
                    })
                    .padding(bottom = 20.dp)
            ) { page ->
                KitchenWrapper(fullWidth = true, horizontal = KitchenWrapperAlignment.Start) {
                    items[page]?.let { item ->
                        //--Slide dos cards e efeitos de opacidade
                        Card(
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier
                                .graphicsLayer {
                                    val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue
                                    scaleX = lerp(0.85f, 1f, 1f - pageOffset.coerceIn(0f, 1f))
                                    scaleY = lerp(0.85f, 1f, 1f - pageOffset.coerceIn(0f, 1f))
                                    alpha = lerp(0.50f, 1f, 1f - pageOffset.coerceIn(0f, 1f))
                                }
                        ) {
                            //-- Card que está recebendo da API
                            _gradeProdutosViewModel.produtos.value?.produtos?.list
                            KitchenCarouselCard(titulo = item.titulo!!, valor = item.valor, imagem = item.docImg!!, page = page, onClick = { onClick.invoke(KitchenProdutoModel()) })
                        }
                    }
                }
            }
        }
        // -- Barra de scrolagem inferior
        Row(
            modifier = Modifier
                .height(15.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(itemCount) { it ->
                val color =
                    if (pagerState.currentPage == it) theme.colors.cinza11 else theme.colors.cinza01
                Box(
                    modifier = Modifier
                        .padding(3.dp)
                        .clip(CircleShape)
                        .size(if (pagerState.currentPage == it) 35.dp else 10.dp)
                        .background(color)
                        .clickable {
                            scope.launch {
                                pagerState.animateScrollToPage(it)
                            }
                        }
                )
            }
        }
    }
}
