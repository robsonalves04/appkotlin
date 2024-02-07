package com.example.kdsmobile.views.components.molecules.swipeup

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.rememberSwipeableState
import androidx.compose.material.swipeable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
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
import com.example.kdsmobile.views.components.atoms.kitchen_button.KitchenButton
import com.example.kdsmobile.views.components.atoms.kitchen_container.KitchenWrapper
import com.example.kdsmobile.views.components.atoms.kitchen_container.KitchenWrapperAlignment
import com.example.kdsmobile.views.components.atoms.kitchen_paper.KitchenLineDivider
import com.example.kdsmobile.views.components.atoms.kitchen_paper.KitchenLineSpace
import com.example.kdsmobile.views.components.atoms.kitchen_paper.KitchenMargin
import com.example.kdsmobile.views.components.atoms.kitchen_typography.KitchenTypography
import kotlin.math.roundToInt
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun KitchenSwipeUp(isPanelVisible: Boolean, ){
    var isPanelVisible by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val swipeableState = rememberSwipeableState(0)
    val squareSize = 1000.dp
    val anchors = mapOf(1f to 0, 970f to 1)

    //-- Box com o swipe vertical
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
                )}
            .swipeable(
                state = swipeableState,
                anchors = anchors,
                thresholds = { _, _ -> FractionalThreshold(0.0f) },
                orientation = Orientation.Vertical,)
    ) {
        Box(modifier = Modifier
            .offset { IntOffset(0, swipeableState.offset.value.roundToInt()) }
            .size(squareSize)
            .background(Color.Transparent)) {
            //-- Janela que aparece ao clicar
            KitchenWrapper(fullHeight = true, vertical = KitchenWrapperAlignment.Bottom, offSetY = 30.dp) {
                KitchenWrapper(fullWidth = true, height = 400.dp, borderRadius = 40.dp, backgroundColor = theme.colors.paper) {
                    KitchenWrapper(fullWidth = true, vertical = KitchenWrapperAlignment.Top, horizontal = KitchenWrapperAlignment.Center) {
                        KitchenLineSpace(size = 10.dp)
                        KitchenWrapper{
                            KitchenLineDivider(grossura = 7.dp, color = theme.colors.cinza05, largura = 90.dp, fullLargura = false, paddingTop = 5.dp)
                        }
                    }
                    KitchenMargin(marginTop = 50.dp) {
                        KitchenWrapper(fullWidth = true, horizontal = KitchenWrapperAlignment.Center) {
                            KitchenTypography(text = "Sair", style = TextStyle(fontSize = 20.sp), color = theme.colors.preto00, weight = FontWeight.Bold)
                            KitchenLineSpace(size = 15.dp)
                            KitchenTypography(text = "Tem certeza disso?", style = TextStyle(fontSize = 20.sp), color = theme.colors.cinza14, weight = FontWeight.Bold)
                        }
                    }
                    //-- Botoes de confirmação de Sair ou Não
                    KitchenMargin(marginTop = 60.dp) {
                        KitchenWrapper(height = 50.dp) {
                            KitchenButton(text = "Sim", backgroundColor = theme.colors.verdeVibe02, backgroundColorDegrade = theme.colors.verdeVibe02,
                                onClick = { context.navigate(KitchenScreens.Login) }) { }
                        }
                        KitchenLineSpace(size = 15.dp)
                        KitchenWrapper(height = 50.dp) {
                            KitchenButton(
                                text = "Não", backgroundColor = theme.colors.branco, backgroundColorDegrade = theme.colors.branco, borderColor = theme.colors.preto00,
                                borderSize = 1.dp, height = 50.dp, hasBorder = true,
//                                onClick = { context.navigate(KitchenScreens.Perfil) }
                            ) { }
                        }
                    }
                }
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
