package com.example.kdsmobile.views.components.molecules.carousel.mesas_carousel

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kdsmobile.config.theme.theme
import com.example.kdsmobile.extensions.navigation.KitchenScreens
import com.example.kdsmobile.extensions.navigation.navigate
import com.example.kdsmobile.viewmodels.mesas.KitchenMesasViewModel
import com.example.kdsmobile.views.components.atoms.kitchen_container.KitchenWrapper
import com.example.kdsmobile.views.components.atoms.kitchen_container.KitchenWrapperAlignment
import com.example.kdsmobile.views.components.atoms.kitchen_paper.KitchenColumnSpace
import com.example.kdsmobile.views.components.atoms.kitchen_paper.KitchenMargin
import com.example.kdsmobile.views.components.atoms.kitchen_typography.KitchenTypography

@Composable
fun KitchenMesasCarousel(mesasViewModel: KitchenMesasViewModel) {
    //--Secao de item da mesa
    KitchenWrapper(inline = true, horizontalScroll = true) {
        Row(
            Modifier
                .width(104.dp)
                .height(52.dp)
                .shadow(2.dp, shape = RoundedCornerShape(90.dp))
                .clip(RoundedCornerShape(90.dp))
                .background(
                    if (mesasViewModel.selectedCategory.value == "Todas") theme.colors.preto00 else theme.colors.branco,
                    shape = RoundedCornerShape(90.dp)
                )
                .clickable { mesasViewModel.selectedCategory.value = "Todas" },
            verticalAlignment = Alignment.CenterVertically,

            ) {
            Column(
                Modifier
                    .padding(4.dp)
                    .width(36.dp)
                    .height(36.dp)
                    .border(2.dp, theme.colors.branco, shape = RoundedCornerShape(90.dp))
                    .background(theme.colors.preto00, shape = RoundedCornerShape(90.dp)),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                KitchenTypography(text = "30", weight = FontWeight.Bold, color = theme.colors.branco)
            }
            KitchenTypography(
                text = "Todas",
                weight = FontWeight.Bold,
                color = if (mesasViewModel.selectedCategory.value == "Todas") theme.colors.branco else theme.colors.preto00,
               style = TextStyle(fontSize = 14.sp)
            )
        }
        KitchenColumnSpace(size = 18.dp)
        Row(
            Modifier
                .width(104.dp)
                .height(52.dp)
                .shadow(2.dp, shape = RoundedCornerShape(90.dp))
                .clip(RoundedCornerShape(90.dp))
                .background(
                    if (mesasViewModel.selectedCategory.value == "Livres") theme.colors.preto00 else theme.colors.branco,
                    shape = RoundedCornerShape(90.dp)
                )
                .clickable { mesasViewModel.selectedCategory.value = "Livres" },
            verticalAlignment = Alignment.CenterVertically,
            ) {
            Column(
                Modifier
                    .padding(4.dp)
                    .width(36.dp)
                    .height(36.dp)
                    .border(2.dp, theme.colors.preto00, shape = RoundedCornerShape(90.dp))
                    .background(theme.colors.verdeVibe01, shape = RoundedCornerShape(90.dp)),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                KitchenTypography(text = "10", weight = FontWeight.Bold)
            }
            KitchenTypography(
                text = "Livres",
                weight = FontWeight.Bold,
                color = if (mesasViewModel.selectedCategory.value == "Livres") theme.colors.branco else theme.colors.preto00,
                style = TextStyle(fontSize = 14.sp)
            )
        }

        KitchenColumnSpace(size = 18.dp)

        Row(
            Modifier
                .width(104.dp)
                .height(52.dp)
                .shadow(2.dp, shape = RoundedCornerShape(90.dp))
                .clip(RoundedCornerShape(90.dp))
                .background(
                    if (mesasViewModel.selectedCategory.value == "Ocupados") theme.colors.preto00 else theme.colors.branco,
                    shape = RoundedCornerShape(90.dp)
                )
                .clickable { mesasViewModel.selectedCategory.value = "Ocupados" },
            verticalAlignment = Alignment.CenterVertically,

            ) {


            Column(
                Modifier
                    .padding(4.dp)
                    .width(36.dp)
                    .height(36.dp)
                    .border(2.dp, theme.colors.preto00, shape = RoundedCornerShape(90.dp))
                    .background(theme.colors.vermelhoOcupado, shape = RoundedCornerShape(90.dp)),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                KitchenTypography(text = "20", weight = FontWeight.Bold)
            }
            KitchenTypography(
                text = "Ocupadas",
                weight = FontWeight.Bold,
                color = if (mesasViewModel.selectedCategory.value == "Ocupados") theme.colors.branco else theme.colors.preto00,
                style = TextStyle(fontSize = 14.sp)
            )
        }
    }
}