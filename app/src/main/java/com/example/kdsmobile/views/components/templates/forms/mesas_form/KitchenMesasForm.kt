package com.example.kdsmobile.views.components.templates.forms.mesas_form

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.outlined.TableRestaurant
import androidx.compose.material.rememberSwipeableState
import androidx.compose.material.swipeable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kdsmobile.config.theme.theme
import com.example.kdsmobile.extensions.navigation.KitchenScreens
import com.example.kdsmobile.extensions.navigation.navigate
import com.example.kdsmobile.viewmodels.kitchen_grade.KitchenGradeViewModel
import com.example.kdsmobile.viewmodels.kitchen_pedido.KitchenPedidoViewModel
import com.example.kdsmobile.viewmodels.mesas.KitchenMesasViewModel
import com.example.kdsmobile.views.components.atoms.kitchen_button.KitchenButton
import com.example.kdsmobile.views.components.atoms.kitchen_container.KitchenWrapper
import com.example.kdsmobile.views.components.atoms.kitchen_container.KitchenWrapperAlignment
import com.example.kdsmobile.views.components.atoms.kitchen_icon.KitchenIcon
import com.example.kdsmobile.views.components.atoms.kitchen_paper.KitchenLineDivider
import com.example.kdsmobile.views.components.atoms.kitchen_paper.KitchenLineSpace
import com.example.kdsmobile.views.components.atoms.kitchen_paper.KitchenMargin
import com.example.kdsmobile.views.components.atoms.kitchen_typography.KitchenTypography
import com.example.kdsmobile.views.components.molecules.cards.mesas_card.KitchenMesasCard
import com.example.kdsmobile.views.components.molecules.carousel.mesas_carousel.KitchenMesasCarousel
import kotlin.math.roundToInt


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun KitchenMesasForm(mesasViewModel: KitchenMesasViewModel, pedidoViewModel: KitchenPedidoViewModel, gradeViewModel: KitchenGradeViewModel, pedidoViewModel1: KitchenPedidoViewModel) {
    var selectedItem by remember { mutableStateOf(0) }
    var searchQuery by remember { mutableStateOf("") }
    val context = LocalContext.current
    val anchors = mapOf(1f to 0, 1200f to 1)
    var isPanelVisible by remember { mutableStateOf(false) }
    var swipeableState = rememberSwipeableState(0)
    val squareSize = 1000.dp



    //Cabeçalho do pagina
    KitchenWrapper {
        KitchenWrapper(fullWidth = true, backgroundColor = Color.Black, height = 60.dp) {
            KitchenMargin {
                KitchenWrapper(fullWidth = true, inline = true, fullHeight = true, vertical = KitchenWrapperAlignment.Bottom) {

                    KitchenWrapper(
                        widthFloat = 0.2f, clickable = true,
                        onClick = { context.navigate(KitchenScreens.Home) }
                    ) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "voltar", tint = theme.colors.verdeVibe00, modifier = Modifier.size(30.dp))
                    }
                    KitchenWrapper(widthFloat = 0.9f, fullWidth = true, horizontal = KitchenWrapperAlignment.Center) {
                        KitchenTypography(text = "Mesas", style = TextStyle(fontSize = 20.sp), color = Color.White, weight = FontWeight.Bold)
                    }
                }
            }
        }
        //--Primeira parte da pagina
        Column(Modifier.fillMaxSize()) {

            KitchenMargin(marginTop = 24.dp) {
                KitchenMesasCarousel(mesasViewModel)

                KitchenLineSpace(size = 24.dp)
                //--== Barra de pesquisae
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it; mesasViewModel.selectedCategory.value = "Todos" },
                    placeholder = {
                        KitchenTypography(
                            text = "Busque pela mesa",
                            color = theme.colors.cinza04,
                            weight = FontWeight.SemiBold
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(64.dp)
                        .padding(bottom = 12.dp),

                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        unfocusedBorderColor = theme.colors.cinza04,
                        focusedBorderColor = theme.colors.cinza04,
                        cursorColor = theme.colors.cinza04
                    ),
                    shape = RoundedCornerShape(16.dp),
                    trailingIcon = {
                        KitchenIcon(
                            icon = Icons.Default.Close,
                            onClick = { searchQuery = "" },
                            iconColor = theme.colors.cinza03,
                            containerColor = theme.colors.cinza01,
                            size = 16.dp
                        )
                    }

                )

                KitchenLineSpace(size = 24.dp)

                KitchenWrapper(fullHeight = true, fullWidth = true, verticalScroll = true) {

                    // Utilizando repeat para criar 30 instâncias do KitchenMesasCard
                    var hasMatches = false
                    repeat(30) { index ->
                        val isEven = index % 2 == 0
                        val isVisible = when (mesasViewModel.selectedCategory.value) {
                            "Livres" -> isEven
                            "Ocupados" -> !isEven
                            "Todos" -> true
                            else -> true
                        }

                        // Filtra com base na barra de pesquisa
                        val matchesSearch = searchQuery.isEmpty() || index.toString().contains(searchQuery)

                        if (isVisible && matchesSearch) {
                            hasMatches = true
                            KitchenMesasCard(
                                index.toString(),
                                isEven,  // true para números pares, false para ímpares
                                selectedItem == index,

                                onItemClick = { selectedItem = index; isPanelVisible = true }

                            )
                        }
                    }

                    // Exibe mensagem de erro se não houver correspondências
                    if (!hasMatches && searchQuery.isNotEmpty()) {
                        Column(
                            Modifier.fillMaxWidth(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            KitchenIcon(icon = Icons.Default.Error, size = 60.dp, iconColor = theme.colors.preto01)
                            KitchenLineSpace(size = 16.dp)
                            KitchenTypography(text = "Nenhuma correspondência encontrada", size = 18.sp)
                        }
                    }
                }

            }
        }
    }
    if (isPanelVisible) {
        //-- SwipeUp de sair
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .background(theme.colors.pretoSemiTransparente)
                .pointerInput(Unit) {
                    detectTapGestures(
                        onLongPress = { },
                        onTap = { offset ->
                            isPanelVisible = false
                        }
                    )
                }
                .swipeable(
                    state = swipeableState,
                    anchors = anchors,
                    thresholds = { _, _ -> FractionalThreshold(0.0f) },
                    orientation = Orientation.Vertical,
                )
        ) {
            Box(modifier = Modifier
                .offset { IntOffset(0, swipeableState.offset.value.roundToInt()) }
                .size(squareSize)
                .background(Color.Transparent)) {
                //-- Janela de sair
                KitchenWrapper(fullHeight = true, vertical = KitchenWrapperAlignment.Bottom, offSetY = 30.dp) {
                    KitchenWrapper(
                        fullWidth = true,
                        height = 400.dp,
                        borderRadius = 40.dp,
                        backgroundColor = theme.colors.paper,
                        clickable = true, onClick = {}
                    ) {
                        KitchenWrapper(
                            fullWidth = true,
                            vertical = KitchenWrapperAlignment.Top,
                            horizontal = KitchenWrapperAlignment.Center
                        ) {
                            KitchenLineSpace(size = 10.dp)
                            KitchenWrapper(
                                clickable = true,
                                onClick = { isPanelVisible = !isPanelVisible }
                            ) {
                                KitchenLineDivider(
                                    grossura = 7.dp,
                                    color = theme.colors.cinza05,
                                    largura = 90.dp,
                                    fullLargura = false,
                                    paddingTop = 5.dp
                                )
                            }
                        }
                        KitchenMargin(marginTop = 50.dp) {
                            KitchenWrapper(fullWidth = true, horizontal = KitchenWrapperAlignment.Center) {
                                KitchenTypography(
                                    text = "Por favor, confirme",
                                    style = TextStyle(fontSize = 27.sp),
                                    color = theme.colors.preto00,
                                    weight = FontWeight.SemiBold
                                )

                                KitchenTypography(
                                    text = "a mesa selecionada",
                                    style = TextStyle(fontSize = 27.sp),
                                    color = theme.colors.preto00,
                                    weight = FontWeight.SemiBold
                                )
                                KitchenLineSpace(size = 15.dp)

                                Column(
                                    Modifier
                                        .height(72.dp)
                                        .width(72.dp)
                                        .background(theme.colors.preto00, RoundedCornerShape(12.dp)),
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {

                                    KitchenIcon(
                                        icon = Icons.Outlined.TableRestaurant,
                                        size = 40.dp,
                                        iconColor = theme.colors.branco
                                    )

                                }

                                KitchenTypography(
                                    text = "Mesa $selectedItem",
                                    style = TextStyle(fontSize = 27.sp),
                                    color = theme.colors.preto00,
                                    weight = FontWeight.SemiBold
                                )

                            }
                        }
                        //-- Botoes de confirmação
                        KitchenMargin() {
                            KitchenWrapper(height = 50.dp) {
                                KitchenButton(
                                    text = "Confirmar Mesa",
                                    backgroundColor = theme.colors.verdeVibe02,
                                    backgroundColorDegrade = theme.colors.verdeVibe02,

                                    ) { }
                            }
                            KitchenLineSpace(size = 15.dp)
                        }
                    }
                }

                LaunchedEffect(swipeableState) {
                    snapshotFlow { swipeableState.currentValue }
                        .collect { offset ->
                            // Verifique se a segunda Box está fora da tela (offset maior que a altura da tela)
                            if (offset > 0) {
                                isPanelVisible = false;
                                swipeableState.snapTo(0)

                            }
                        }
                }
            }
        }
    }
}

