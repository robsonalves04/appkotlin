package com.example.kdsmobile.views.components.molecules.topbar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kdsmobile.config.theme.theme
import com.example.kdsmobile.extensions.navigation.KitchenScreens
import com.example.kdsmobile.extensions.navigation.navigate
import com.example.kdsmobile.viewmodels.kitchen_fragment.KitchenFragmentsViewModel
import com.example.kdsmobile.viewmodels.kitchen_fragment.KitchenTopbarFragmentSlider
import com.example.kdsmobile.views.components.atoms.kitchen_container.KitchenWrapper
import com.example.kdsmobile.views.components.atoms.kitchen_container.KitchenWrapperAlignment
import com.example.kdsmobile.views.components.atoms.kitchen_icon.KitchenIcon
import com.example.kdsmobile.views.components.atoms.kitchen_typography.KitchenTypography

@Composable
fun KitchenTopBar(title: String) {

    val context = LocalContext.current

    KitchenWrapper(
        fullWidth = true, height = theme.height / 10, backgroundColor = theme.colors.preto01, inline = true,
        vertical = KitchenWrapperAlignment.Center, horizontal = KitchenWrapperAlignment.SpaceAround
    ) {

        KitchenIcon(
            icon = Icons.Default.ArrowBack,
            iconColor = theme.colors.verdeVibe01,
            onClick = { context.navigate(KitchenScreens.Home) },
            size = 20.dp

        )
        KitchenTypography(text = title, color = theme.colors.branco, weight = FontWeight.Bold, size = 10.sp)

        KitchenIcon(
            icon = Icons.Outlined.ShoppingCart,
            iconColor = theme.colors.verdeVibe01,
            size = 20.dp
        )

    }
}

// --== Composição da barra de navegação superior
@Composable
fun KitchenTopbar(
    title: String? = null,
    icon: ImageVector? = Icons.Default.ArrowBackIos,
    onIconClick: (() -> Unit)? = null,
    viewModel: KitchenFragmentsViewModel,
) {
    // --== Informações de visualização de navbar
    val topbarView = viewModel.topbarView.observeAsState()

    KitchenTopbarFragmentSlider(topbarView) {

        Box(modifier = Modifier.height(70.dp)) {
            TopAppBar(backgroundColor = theme.colors.preto01, modifier = Modifier.height(70.dp)) {
                Row(
                    modifier = Modifier
                        .height(62.dp)
                        .fillMaxWidth()
                        .padding(start = 16.dp, top = 18.dp), verticalAlignment = Alignment.CenterVertically
                ) {

                    icon?.let {
                        Icon(
                            imageVector = icon, tint = theme.colors.verdeVibe01, modifier = Modifier
                                .size(18.dp)
                                .clickable(onClick = { onIconClick?.invoke() }),
                            contentDescription = "Decrementar"
                        )

                        Spacer(modifier = Modifier.width(12.dp))
                    }

                    KitchenTypography(
                        text = "$title", color = theme.colors.branco, size = 16.sp,
                        weight = FontWeight.Medium, textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}


