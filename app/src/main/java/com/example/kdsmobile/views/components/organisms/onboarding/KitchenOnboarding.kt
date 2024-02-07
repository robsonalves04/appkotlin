package com.example.kdsmobile.views.components.organisms.onboarding

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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kdsmobile.config.theme.theme
import com.example.kdsmobile.viewmodels.login.KitchenLoginViewModel
import com.example.kdsmobile.views.components.atoms.kitchen_button.KitchenButton
import com.example.kdsmobile.views.components.atoms.kitchen_container.KitchenWrapper
import com.example.kdsmobile.views.components.atoms.kitchen_container.KitchenWrapperAlignment
import com.example.kdsmobile.views.components.atoms.kitchen_paper.KitchenLineDivider
import com.example.kdsmobile.views.components.atoms.kitchen_paper.KitchenLineSpace
import com.example.kdsmobile.views.components.atoms.kitchen_paper.KitchenMargin
import com.example.kdsmobile.views.components.atoms.kitchen_textfield.KitchenTextInput
import com.example.kdsmobile.views.components.atoms.kitchen_typography.KitchenTypography
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun KitchenOnboarding(viewModel: KitchenLoginViewModel) {
    var isPanelVisible by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val swipeableState = rememberSwipeableState(0)
    val squareSize = 1000.dp
    val anchors = mapOf(0f to 0, 1500f to 1)

    KitchenWrapper(
        fullWidth = true, fullHeight = true,
        backgroundDirection = Brush.verticalGradient(
            listOf(Color.Transparent, theme.colors.preto00)
        ), vertical = KitchenWrapperAlignment.Bottom, horizontal = KitchenWrapperAlignment.Center
    ) {
        KitchenWrapper(fullWidth = true) {
            KitchenMargin(marginBottom = 24.dp, marginLeft = 0.dp) {
                KitchenWrapper(inline = true) {
                    KitchenWrapper(fullWidth = true, widthFloat = 0.05f) {}

                    KitchenWrapper(
                        fullWidth = true, fullHeight = true, heightFloat = 0.4f,
                        vertical = KitchenWrapperAlignment.SpaceBetween,
                    ) {
                        KitchenWrapper {

                            KitchenTypography(
                                text = "Descubra o garçomapp:", weight = FontWeight.Bold,
                                color = theme.colors.branco, style = TextStyle(fontSize = 19.sp), resposivel = true, maxLines = 1,
                            )
                            KitchenLineSpace(size = 10.dp)
                            KitchenTypography(
                                text = "A simplicidade de gerenciar pedidos com eficiência. Entre no futuro do atendimento eficiente!",
                                color = theme.colors.branco, style = TextStyle(fontSize = 18.sp), resposivel = true, maxLines = 3, weight = FontWeight.W400,
                            )
                        }
                        KitchenButton(
                            text = "Acessar", fullHeight = true, heightFloat = 0.25f, textSize = 15.sp, onClick = { isPanelVisible = !isPanelVisible },
                            backgroundColor = theme.colors.verdeVibe01, backgroundColorDegrade = theme.colors.verdeVibe02
                        ) {}

                        KitchenWrapper(fullWidth = true, horizontal = KitchenWrapperAlignment.Center) {
                            KitchenTypography(
                                text = "Ao se cadastrar voce aceita os", style = TextStyle(fontSize = 15.sp), resposivel = true,
                                color = theme.colors.cinza05, textAlign = TextAlign.Center,
                            )
                            KitchenWrapper(
                                fullWidth = true, inline = true, horizontal = KitchenWrapperAlignment.Center
                            ) {
                                KitchenTypography(
                                    text = "Termo de uso ", color = theme.colors.cinza05, weight = FontWeight.Bold, style = TextStyle(fontSize = 15.sp), resposivel = true,
                                )
                                KitchenTypography(text = "Politica de Serviços", color = theme.colors.cinza05, style = TextStyle(fontSize = 15.sp), resposivel = true)
                            }
                        }
                    }
                }
            }
        }
    }

    //-- Box com o swipe vertical
    if (isPanelVisible) {
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
                //-- Janela que aparece ao clicar
                KitchenWrapper(
                    fullHeight = true, vertical = KitchenWrapperAlignment.Bottom, offSetY = 30.dp
                ) {
                    KitchenWrapper(
                        fullWidth = true,
                        height = 500.dp,
                        borderRadius = 40.dp,
                        backgroundColor = theme.colors.preto01, backgroundColorDegrade = theme.colors.preto00,
                    ) {
                        KitchenWrapper(
                            fullWidth = true, vertical = KitchenWrapperAlignment.Top, horizontal = KitchenWrapperAlignment.Center
                        ) {
                            KitchenLineSpace(size = 10.dp)
                            KitchenWrapper(
                                clickable = true,
                                onClick = { }
                            ) {
                                KitchenLineDivider(
                                    grossura = 7.dp, color = theme.colors.cinza05, largura = 90.dp,
                                    fullLargura = false, paddingTop = 5.dp
                                )
                            }
                        }
                        KitchenMargin(marginTop = 30.dp) {
                            KitchenWrapper(fullWidth = true, horizontal = KitchenWrapperAlignment.Center) {
                                KitchenTypography(
                                    text = "Acesse Sua Conta", style = TextStyle(fontSize = 20.sp), resposivel = true,
                                    weight = FontWeight.Bold, color = theme.colors.branco
                                )
                                KitchenLineSpace(size = 10.dp)
                                KitchenTypography(
                                    text = "Insira o Id e senha que foi disponibilizado no  perfil de Gerenciamento Do Vibe Business.",
                                    style = TextStyle(fontSize = 15.sp), maxLines = 2, resposivel = true, color = theme.colors.cinza07
                                )
                                KitchenLineSpace(size = 20.dp)
                                KitchenWrapper {
                                    KitchenTextInput(viewModel.login, keyboard = KeyboardType.Email, placeholder = "Login")
                                    KitchenTextInput(viewModel.senha, keyboard = KeyboardType.Password, placeholder = "Senha", ImeAction.Next)
                                    KitchenButton(
                                        text = "Login", fullHeight = true, heightFloat = 0.30f, textSize = 15.sp,
                                        backgroundColor = theme.colors.verdeVibe01, backgroundColorDegrade = theme.colors.verdeVibe02,
                                        onClick = { viewModel.autenticar(context) },
                                    ) {}
                                }
                                KitchenLineSpace(size = 30.dp)
                                KitchenWrapper(fullWidth = true, horizontal = KitchenWrapperAlignment.Center) {
                                    KitchenTypography(
                                        text = "Ao se cadastrar voce aceita os",
                                        color = theme.colors.cinza05, style = TextStyle(fontSize = 12.sp), resposivel = true,
                                    )

                                    KitchenWrapper(fullWidth = true, inline = true, horizontal = KitchenWrapperAlignment.Center) {
                                        KitchenTypography(
                                            text = "Termo de uso ", color = theme.colors.cinza05, weight = FontWeight.Bold, style = TextStyle(fontSize = 12.sp), resposivel = true,
                                        )
                                        KitchenTypography(
                                            text = "Politica de Serviços", color = theme.colors.cinza05, style = TextStyle(fontSize = 12.sp), resposivel = true,
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        LaunchedEffect(swipeableState) {
            snapshotFlow { swipeableState.currentValue }
                .collect { offset ->
                    if (offset > 0) {
                        isPanelVisible = false;
                        swipeableState.snapTo(0)
                    }
                }
        }
    }
}


