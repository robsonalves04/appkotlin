package com.example.kdsmobile.views.components.templates.forms.historico_form

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.BookOnline
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material.icons.outlined.TableRestaurant
import androidx.compose.material.rememberSwipeableState
import androidx.compose.material.swipeable
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kdsmobile.config.theme.theme
import com.example.kdsmobile.config.types.KitchenInputValidationState
import com.example.kdsmobile.extensions.navigation.KitchenScreens
import com.example.kdsmobile.extensions.navigation.navigate
import com.example.kdsmobile.views.components.atoms.kitchen_button.KitchenButton
import com.example.kdsmobile.views.components.atoms.kitchen_container.KitchenWrapper
import com.example.kdsmobile.views.components.atoms.kitchen_container.KitchenWrapperAlignment
import com.example.kdsmobile.views.components.atoms.kitchen_paper.KitchenColumnSpace
import com.example.kdsmobile.views.components.atoms.kitchen_paper.KitchenLineDivider
import com.example.kdsmobile.views.components.atoms.kitchen_paper.KitchenLineSpace
import com.example.kdsmobile.views.components.atoms.kitchen_paper.KitchenMargin
import com.example.kdsmobile.views.components.atoms.kitchen_textfield.KitchenTextInput
import com.example.kdsmobile.views.components.atoms.kitchen_typography.KitchenTypography
import kotlin.math.roundToInt

@Composable
fun KitchenHistoricoForm() {
    val context = LocalContext.current

    var isPanelVisible by remember { mutableStateOf(false) }
    KitchenWrapper {
        if (isPanelVisible) {
            KitchenDetalhadoForm()
        } else {
        KitchenWrapper(fullWidth = true, backgroundColor = Color.Black, height = 60.dp) {
            KitchenMargin {
                //Cabeçalho do histórico
                KitchenWrapper(fullWidth = true, inline = true, fullHeight = true, vertical = KitchenWrapperAlignment.Bottom) {

                    KitchenWrapper(widthFloat = 0.2f, clickable = true,
//                        onClick = { context.navigate(KitchenScreens.Home) }
                    ) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "voltar", tint = theme.colors.verdeVibe00, modifier = Modifier.size(35.dp))
                    }
                    KitchenWrapper(widthFloat = 0.9f, fullWidth = true, horizontal = KitchenWrapperAlignment.Center) {
                        KitchenTypography(text = "Histórico", style = TextStyle(fontSize = 20.sp), color = Color.White, weight = FontWeight.Bold)
                    }
                }
            }
        }
        KitchenWrapper(fullWidth = true) {
//        KitchenSearchForm()
            //-- Barra de pesquisa
            KitchenMargin() {
                KitchenTextInput(field = KitchenInputValidationState(), placeholder = "Busque pela mesa")
            }
        }
        KitchenMargin {
            KitchenWrapper(fullWidth = true) {
                KitchenTypography(text = "Halal Lab", color = theme.colors.cinza07, weight = FontWeight.SemiBold, style = TextStyle(fontSize = 30.sp))
                KitchenLineSpace(size = 15.dp)
                KitchenTypography(text = "Seg. 25 de janeiro 2024", color = theme.colors.cinza07, weight = FontWeight.Normal, style = TextStyle(fontSize = 20.sp))
            }
        }
        KitchenWrapper(
//            fullHeight = true, verticalScroll = true
        ) {
            KitchenMargin {
                repeat(1) {
                        KitchenWrapper(fullWidth = true, clickable = true, onClick = { isPanelVisible = !isPanelVisible }) {
                            KitchenWrapper(fullWidth = true, height = 80.dp, elevation = 1.dp, borderRadius = 8.dp, vertical = KitchenWrapperAlignment.Center) {
                                KitchenMargin {
                                    KitchenWrapper(fullWidth = true, inline = true) {
                                        KitchenWrapper(widthFloat = 3f, inline = true) {
                                            KitchenWrapper(borderRadius = 100.dp, width = 25.dp, height = 25.dp, backgroundColor = theme.colors.preto01, vertical = KitchenWrapperAlignment.Center, horizontal = KitchenWrapperAlignment.Center) {
                                                Icon(imageVector = Icons.Outlined.TableRestaurant, contentDescription = "mesa", tint = theme.colors.branco, modifier = Modifier.size(20.dp))
                                            }
                                            KitchenColumnSpace(size = 5.dp)
                                            KitchenTypography(text = "Mesa 1", size = 25.sp, color = theme.colors.preto01, weight = FontWeight.SemiBold)
                                        }
                                        KitchenColumnSpace(size = 10.dp)
                                        KitchenWrapper(widthFloat = 3f, inline = true) {
                                            KitchenWrapper(borderRadius = 100.dp, width = 25.dp, height = 25.dp, backgroundColor = theme.colors.preto01, vertical = KitchenWrapperAlignment.Center, horizontal = KitchenWrapperAlignment.Center) {
                                                Icon(imageVector = Icons.Outlined.BookOnline, contentDescription = "comanda", tint = theme.colors.branco, modifier = Modifier.size(18.dp))
                                            }
                                            KitchenColumnSpace(size = 5.dp)
                                            KitchenTypography(text = "Comanda 0001", size = 25.sp, color = theme.colors.preto01, weight = FontWeight.SemiBold)
                                        }
                                        KitchenColumnSpace(size = 25.dp)
                                        KitchenWrapper(widthFloat = 3f, inline = true) {
                                            KitchenWrapper(borderRadius = 100.dp, width = 25.dp, height = 25.dp, backgroundColor = theme.colors.transparente) {
                                                Icon(imageVector = Icons.Outlined.KeyboardArrowDown, contentDescription = "mesa", tint = theme.colors.cinza07, modifier = Modifier.size(25.dp))
                                            }
                                        }
                                    }
                                }
                            }
                            KitchenLineSpace(size = 10.dp)
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun KitchenDetalhadoForm() {
    val context = LocalContext.current
    val swipeableState = rememberSwipeableState(0)
    val squareSize = 700.dp
    val anchors = mapOf(1f to 0, 700f to 1)

    //--Background durante do swipeup estiver ativo
    KitchenHistoricoForm()

    Box(
        modifier = Modifier
            .fillMaxHeight()
            .swipeable(
                state = swipeableState,
                anchors = anchors,
                thresholds = { _, _ -> FractionalThreshold(0.0f) },
                orientation = Orientation.Vertical
            )
            .background(theme.colors.pretoSemiTransparente)
    ) {
        Box(modifier = Modifier
            .offset { IntOffset(0, swipeableState.offset.value.roundToInt()) }
            .size(squareSize)
            .background(Color.Transparent)) {
            KitchenWrapper(fullHeight = true, vertical = KitchenWrapperAlignment.Bottom, offSetY = 30.dp) {
                KitchenWrapper(fullWidth = true, height = 400.dp, borderRadius = 40.dp, backgroundColor = theme.colors.paper) {
                    KitchenWrapper(fullWidth = true, vertical = KitchenWrapperAlignment.Top, horizontal = KitchenWrapperAlignment.Center) {
                        KitchenLineSpace(size = 10.dp)
                        KitchenWrapper(clickable = true,
//                            onClick = { context.navigate(KitchenScreens.Perfil) }
                        ) {
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
                            KitchenButton(text = "Não", backgroundColor = theme.colors.branco, backgroundColorDegrade = theme.colors.branco, borderColor = theme.colors.preto00, borderSize = 1.dp, height = 50.dp, hasBorder = true,
//                                onClick = { context.navigate(KitchenScreens.Perfil) }
                            ) { }
                        }
                    }
                }
            }
        }
    }
}