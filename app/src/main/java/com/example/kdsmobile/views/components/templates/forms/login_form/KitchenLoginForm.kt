package com.example.kdsmobile.views.components.templates.forms.login_form

import android.annotation.SuppressLint
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kdsmobile.config.theme.theme
import com.example.kdsmobile.config.types.KitchenInputValidationState
import com.example.kdsmobile.viewmodels.login.KitchenLoginViewModel
import com.example.kdsmobile.views.components.atoms.kitchen_button.KitchenButton
import com.example.kdsmobile.views.components.atoms.kitchen_container.KitchenWrapper
import com.example.kdsmobile.views.components.atoms.kitchen_container.KitchenWrapperAlignment
import com.example.kdsmobile.views.components.atoms.kitchen_paper.KitchenMargin
import com.example.kdsmobile.views.components.atoms.kitchen_textfield.KitchenTextInput
import com.example.kdsmobile.views.components.atoms.kitchen_typography.KitchenTypography
import kotlin.math.roundToInt

@SuppressLint("SuspiciousIndentation")
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun KitchenLoginForm(viewModel: KitchenLoginViewModel, field: KitchenInputValidationState? = null) {

    var isPanelVisible by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val swipeableState = rememberSwipeableState(0)
    val squareSize = 500.dp
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
                KitchenWrapper(
                    fullHeight = true, vertical = KitchenWrapperAlignment.Bottom, offSetY = 30.dp
                ) {
                    KitchenWrapper(fullWidth = true, fullHeight = true, heightFloat = 0.5f, widthFloat = 0.4f) {
                        //* KitchenSticker(source = ImageSource.Drawable(R.drawable.kitchen_logo))
                    }
                }
                KitchenWrapper(
                    borderRadiusBottomRight = 0.dp, borderRadiusBottomLeft = 0.dp, fullHeight = true,
                    borderRadiusTopLeft = 45.dp, borderRadiusTopRight = 45.dp, fullWidth = true,
                    backgroundColor = theme.colors.cinza12, vertical = KitchenWrapperAlignment.SpaceAround
                ) {
                    KitchenMargin(marginTop = 20.dp) {
                        KitchenWrapper(fullHeight = true, fullWidth = true, vertical = KitchenWrapperAlignment.SpaceAround) {
                            KitchenTypography(
                                text = "Acesse Sua Conta", style = TextStyle(fontSize = 20.sp), resposivel = true,
                                weight = FontWeight.Bold, color = theme.colors.branco
                            )

                            KitchenTypography(
                                text = "Insira o Id e senha que foi disponibilizado no  perfil de Gerenciamento Do Vibe Business.",
                                style = TextStyle(fontSize = 15.sp), maxLines = 2, resposivel = true, color = theme.colors.cinza07
                            )

                            KitchenWrapper {
                                KitchenTextInput(viewModel.login, keyboard = KeyboardType.Email, placeholder = "Login")
                                KitchenTextInput(viewModel.senha, keyboard = KeyboardType.Password, placeholder = "Senha", ImeAction.Next)
                                KitchenButton(
                                    text = "Login", fullHeight = true, heightFloat = 0.20f, textSize = 15.sp,
                                    backgroundColor = theme.colors.verdeVibe01, backgroundColorDegrade = theme.colors.verdeVibe02,
                                    onClick = { viewModel.autenticar(context) },
                                ) {}
                            }


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

