package com.example.kdsmobile.views.components.molecules.cards.mesas_card

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NavigateNext
import androidx.compose.material.icons.outlined.TableRestaurant
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kdsmobile.config.theme.colorChange
import com.example.kdsmobile.config.theme.theme
import com.example.kdsmobile.views.components.atoms.kitchen_container.KitchenWrapper
import com.example.kdsmobile.views.components.atoms.kitchen_container.KitchenWrapperAlignment
import com.example.kdsmobile.views.components.atoms.kitchen_icon.KitchenIcon
import com.example.kdsmobile.views.components.atoms.kitchen_paper.KitchenLineSpace
import com.example.kdsmobile.views.components.atoms.kitchen_paper.KitchenMargin
import com.example.kdsmobile.views.components.atoms.kitchen_typography.KitchenTypography

@Composable
fun KitchenMesasCard(
    mesa: String? = "",
    livre: Boolean? = true,
    isSelected: Boolean,
    onItemClick: () -> Unit,

    ) {
    val textoMesa = mutableStateOf("Livre")
    val cor = mutableStateOf(theme.colors.verdeLivre)
    val corLetra = mutableStateOf(theme.colors.verdeConfirmar)

    if (livre == false) {
        textoMesa.value = "Ocupado"
        cor.value = theme.colors.rosa00
        corLetra.value = theme.colors.vermelho00
    }

    KitchenWrapper(
        fullWidth = true, height = 72.dp, clickable = true, onClick = onItemClick,
        backgroundColor = isSelected.colorChange(), elevation = 4.dp,
        vertical = KitchenWrapperAlignment.Center, horizontal = KitchenWrapperAlignment.SpaceBetween,
        borderRadius = 12.dp, inline = true, borderSize = 3.dp, borderColor = isSelected.colorChange(theme.colors.verdeVibe01, theme.colors.branco)
    ) {
        KitchenMargin(
            marginLeft = 20.dp, marginTop = 0.dp, marginBottom = 0.dp,
            marginRight = 0.dp
        ) {
            KitchenWrapper(
                height = 46.dp, width = 46.dp, backgroundColor = theme.colors.verdeVibe01,
                horizontal = KitchenWrapperAlignment.Center, borderRadius = 8.dp,
                vertical = KitchenWrapperAlignment.Center
            ) {
                KitchenIcon(icon = Icons.Outlined.TableRestaurant, iconColor = theme.colors.preto01, size = 28.dp, onClick = onItemClick)
            }
        }

        KitchenTypography(
            text = "Mesa $mesa",
            size = 11.sp,
            color = isSelected.colorChange(theme.colors.branco, theme.colors.preto01),
            weight = FontWeight.Bold,
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            KitchenMargin {
                Column(
                    modifier = Modifier
                        .background(color = cor.value, shape = RoundedCornerShape(12.dp))
                        .padding(6.dp)
                        .height(22.dp)
                        .width(62.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    KitchenTypography(text = textoMesa.value, style = TextStyle(fontSize = 15.sp), color = corLetra.value)
                }
            }
        }
        KitchenMargin(
            marginLeft = 0.dp, marginTop = 0.dp, marginBottom = 0.dp,
            marginRight = 16.dp
        ) {
            KitchenIcon(
                icon = Icons.Default.NavigateNext, onClick = onItemClick,
                iconColor = isSelected.colorChange(theme.colors.branco, theme.colors.preto01), size = 28.dp
            )
        }
    }
    KitchenLineSpace(size = 12.dp)
}