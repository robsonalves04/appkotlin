package com.example.kdsmobile.views.screens.private.home_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChecklistRtl
import androidx.compose.material.icons.filled.HourglassEmpty
import androidx.compose.material.icons.filled.ManageSearch
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.kdsmobile.R
import com.example.kdsmobile.config.mocks.status_pedido.statusPedidoMock
import com.example.kdsmobile.config.theme.KitchenWindowSize
import com.example.kdsmobile.config.theme.WindowType
import com.example.kdsmobile.config.theme.theme
import com.example.kdsmobile.extensions.navigation.KitchenScreens
import com.example.kdsmobile.extensions.navigation.navigate
import com.example.kdsmobile.viewmodels.kitchen_fragment.KitchenFragmentSlider
import com.example.kdsmobile.viewmodels.kitchen_fragment.KitchenFragmentsViewModel
import com.example.kdsmobile.viewmodels.kitchen_grade.KitchenGradeViewModel
import com.example.kdsmobile.viewmodels.status_pedido.StatusPedidoViewModel
import com.example.kdsmobile.views.components.atoms.kitchen_container.KitchenWrapper
import com.example.kdsmobile.views.components.atoms.kitchen_container.KitchenWrapperAlignment
import com.example.kdsmobile.views.components.atoms.kitchen_paper.KitchenColumnSpace
import com.example.kdsmobile.views.components.atoms.kitchen_paper.KitchenLineDivider
import com.example.kdsmobile.views.components.atoms.kitchen_paper.KitchenLineSpace
import com.example.kdsmobile.views.components.atoms.kitchen_paper.KitchenMargin
import com.example.kdsmobile.views.components.atoms.kitchen_sticker.ImageSource
import com.example.kdsmobile.views.components.atoms.kitchen_sticker.KitchenSticker
import com.example.kdsmobile.views.components.atoms.kitchen_typography.KitchenTypography
import com.example.kdsmobile.views.components.molecules.carousel.KitchenCarousel
import com.example.kdsmobile.views.components.molecules.ordenarea.KitchenPedidosForm
import com.example.kdsmobile.views.components.templates.forms.home_form.KitchenDrawerTouch

@Composable
fun KitchenHomeFragment(
    viewModel: KitchenFragmentsViewModel,
    statusViewModel: StatusPedidoViewModel,
    gradeProdutosViewModel: KitchenGradeViewModel,
) {

    val status = statusPedidoMock
    val context = LocalContext.current
    val observeStatus = statusViewModel.status.observeAsState()
    val window = KitchenWindowSize()

    KitchenFragmentSlider(viewModel.homeFragmentView.observeAsState()) {

        //-- Redenrização do ViewModel
        LaunchedEffect(Unit) {
            statusViewModel.obterStatus(context)
        }
        KitchenWrapper(fullWidth = true, verticalScroll = true) {
            KitchenMargin {
                //-- Icone de acesso ao perfil canto superior esquerdo
                KitchenWrapper(
                    fullWidth = true, inline = true, horizontal = KitchenWrapperAlignment.Start, height = 50.dp,
                    vertical = KitchenWrapperAlignment.Center, margin = PaddingValues(top = 20.dp)
                ) {
                    KitchenWrapper(
                        clickable = true, onClick = { context.navigate(KitchenScreens.Perfil) },
                    ) {
                        KitchenSticker(source = ImageSource.Drawable(R.drawable.icon_vibe), borderRadius = 100.dp, height = 45.dp, width = 45.dp)
                    }
                    KitchenColumnSpace(size = 8.dp)
                    KitchenTypography(text = " Festival Vibe", style = TextStyle(fontSize = 15.sp), color = theme.colors.cinza15)
                }
                //-- Segunda parte seção Sugestão de venda e Status do pedido
                KitchenLineSpace(size = 30.dp)
                KitchenWrapper() {
                    KitchenTypography(text = "Sugestões de vendas", style = TextStyle(fontSize = 22.sp), weight = FontWeight.SemiBold, color = Color.Black)
                    KitchenLineSpace(size = 10.dp)
                    //-- Carousel de imagem, na parte superior
                    KitchenWrapper(fullWidth = true, height = when(window.height){
                        WindowType.Expanded -> 250.dp
                        WindowType.Medium -> 250.dp
                        else -> 180.dp
                    }) {
                        KitchenCarousel(gradeProdutosViewModel, onClick = {})
                    }
                    //-- botão de status de pedido
                    KitchenLineSpace(size = 10.dp)
                    KitchenWrapper() {
                        KitchenWrapper(fullWidth = true, inline = true, horizontal = KitchenWrapperAlignment.SpaceAround) {
                            status.map { item ->
                                KitchenPedidosForm(icone = Icons.Default.Restaurant, texto = "Prontos", qtd = "04", corQtd = theme.colors.verdeConfirmar, borderNum = theme.colors.branco, onClick = { context.navigate(KitchenScreens.PedidosProntos) })
                                KitchenPedidosForm(icone = Icons.Default.HourglassEmpty, texto = "Atrasados", qtd = "01", corQtd = theme.colors.vermelho00, borderNum = theme.colors.branco, onClick = {})
                                KitchenPedidosForm(icone = Icons.Default.ChecklistRtl, texto = "Lançados", qtd = "", onClick = {})
                                KitchenPedidosForm(icone = Icons.Default.ManageSearch, texto = "Histórico", qtd = "", onClick = {})
                            }
                        }
                    }
                }
                //-- Terceira parte, seção MESA
                KitchenLineSpace(size = 50.dp)
                KitchenWrapper {
                    KitchenTypography(text = "Mesas", style = TextStyle(fontSize = 22.sp), weight = FontWeight.SemiBold, color = Color.Black)
                    KitchenLineSpace(size = 5.dp)
                    KitchenWrapper(fullWidth = true, inline = true, horizontal = KitchenWrapperAlignment.SpaceBetween, vertical = KitchenWrapperAlignment.Center) {
                        //-- botão de tarefa completo
                        KitchenWrapper(
                            width = 103.dp, height = 150.dp, backgroundColor = theme.colors.cinza01, borderRadius = 8.dp,
                            horizontal = KitchenWrapperAlignment.Center, vertical = KitchenWrapperAlignment.Center, clickable = true,onClick = { }
                        ) {
                            KitchenWrapper(
                                width = 50.dp, height = 50.dp, borderRadius = 100.dp, backgroundColor = theme.colors.transparente, borderSize = 2.dp,
                                borderColor = theme.colors.preto01, horizontal = KitchenWrapperAlignment.Center, vertical = KitchenWrapperAlignment.Center,

                                ) {
                                KitchenTypography(text = "20", color = theme.colors.preto01, style = TextStyle(fontSize = 18.sp), weight = FontWeight.Bold)
                            }
                            KitchenLineSpace(size = 10.dp)
                            KitchenTypography(text = "Livres", color = Color.Black, style = TextStyle(fontSize = 14.sp), weight = FontWeight.SemiBold)
                        }
                        //-- botão de tarefa completo
                        KitchenWrapper(
                            width = 103.dp, height = 150.dp, backgroundColor = theme.colors.cinza01, borderRadius = 8.dp,
                            horizontal = KitchenWrapperAlignment.Center, vertical = KitchenWrapperAlignment.Center
                        ) {
                            KitchenWrapper(
                                width = 50.dp, height = 50.dp, borderRadius = 100.dp, backgroundColor = theme.colors.transparente, borderSize = 2.dp,
                                borderColor = theme.colors.preto01, horizontal = KitchenWrapperAlignment.Center, vertical = KitchenWrapperAlignment.Center
                            ) {
                                KitchenTypography(text = "10", color = theme.colors.preto01, style = TextStyle(fontSize = 18.sp), weight = FontWeight.Bold)
                            }
                            KitchenLineSpace(size = 10.dp)
                            KitchenTypography(text = "Ocupados", color = Color.Black, style = TextStyle(fontSize = 14.sp), weight = FontWeight.SemiBold)
                        }
                        //-- botão de tarefa completo
                        KitchenWrapper(
                            width = 103.dp, height = 150.dp, backgroundColor = theme.colors.cinza01, borderRadius = 8.dp,
                            horizontal = KitchenWrapperAlignment.Center, vertical = KitchenWrapperAlignment.Center
                        ) {
                            KitchenWrapper(
                                width = 50.dp, height = 50.dp, borderRadius = 100.dp, backgroundColor = theme.colors.transparente, borderSize = 2.dp,
                                borderColor = theme.colors.preto01, horizontal = KitchenWrapperAlignment.Center, vertical = KitchenWrapperAlignment.Center
                            ) {
                                KitchenTypography(text = "30", color = theme.colors.preto01, style = TextStyle(fontSize = 18.sp), weight = FontWeight.Bold)
                            }
                            KitchenLineSpace(size = 10.dp)
                            KitchenTypography(text = "Todas", color = Color.Black, style = TextStyle(fontSize = 14.sp), weight = FontWeight.SemiBold)

                        }
                    }
                    KitchenLineDivider(color = Color.LightGray, grossura = 1.dp, paddingTop = 20.dp, paddingBottom = 20.dp)
                }
            }
        }
    }
        Box(Modifier.zIndex(1f)) {
            KitchenDrawerTouch(viewModel= viewModel,)
        }
}