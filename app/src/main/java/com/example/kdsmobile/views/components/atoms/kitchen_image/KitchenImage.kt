package com.example.kdsmobile.views.components.atoms.kitchen_image

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.kdsmobile.R


@Composable
fun KitchenImage(
    image: String? = "https://i.uai.com.br/mhyoIaN0qGP7jiEy09yUMiIwcqU=/1200x900/smart/imgsapp2.uai.com.br/app/noticia_133890394703/2022/12/09/310793/michael-jackson-foto-reproducao_1_44002.jpeg",
    fillMaxSize: Boolean? = true
) {
    if (fillMaxSize == false) {

        Image(
            painter = rememberImagePainter(
                data = image,
                builder = {
                    crossfade(false)
                    placeholder(R.drawable.no_product_image)
                    error(R.drawable.no_product_image)
                }
            ),
            contentDescription = "Imagem carregada da internet", contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(100.dp)
                .width(100.dp)
                .clip(RoundedCornerShape(12.dp))
        )
    } else {

        Image(
            painter = rememberImagePainter(
                data = image,
                builder = {
                    crossfade(false)
                    placeholder(R.drawable.no_product_image)
                    error(R.drawable.no_product_image)
                }
            ),
            contentDescription = "Imagem carregada da internet", contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(12.dp))
        )
    }
}
