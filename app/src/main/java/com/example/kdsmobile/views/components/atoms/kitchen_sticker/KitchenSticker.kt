package com.example.kdsmobile.views.components.atoms.kitchen_sticker

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.core.graphics.drawable.toBitmap
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

sealed class ImageSource {
    data class Url(val url: String) : ImageSource()
    data class Drawable(val resId: Int) : ImageSource()
}

@SuppressLint("UseCompatLoadingForDrawables")
@Composable
fun KitchenSticker(
    source: ImageSource,
    borderRadius: Dp? = null,
    borderLeftTop: Dp? = borderRadius,
    borderLeftBottom: Dp? = borderRadius,
    borderRightTop: Dp? = borderRadius,
    borderRightBottom: Dp? = borderRadius,
    height: Dp? = null,
    width: Dp? = null,
    size: Dp? = null,
    fullSize: Boolean? = false,
    fullWith: Boolean? = false,
    contentScale: ContentScale? = ContentScale.FillBounds,
    filter: Float = 1.0f
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val image = remember { mutableStateOf<ImageBitmap?>(null) }
    val colorMatrix = ColorMatrix()
    colorMatrix.setToSaturation(filter)

    LaunchedEffect(source) {
        coroutineScope.launch {
            when (source) {
                is ImageSource.Url -> {
                    val imageLoader = ImageLoader.Builder(context).build()
                    val request = ImageRequest.Builder(context)
                        .data(source.url).build()
                    val result = imageLoader.execute(request) as? SuccessResult
                    val drawable = result?.drawable
                    val bitmap: Bitmap? = drawable?.toBitmap()

                    withContext(Dispatchers.Main) {
                        image.value = bitmap?.asImageBitmap()
                    }
                }

                is ImageSource.Drawable -> {
                    val drawable = context.getDrawable(source.resId) as? BitmapDrawable
                    val bitmap: Bitmap? = drawable?.bitmap

                    withContext(Dispatchers.Main) {
                        image.value = bitmap?.asImageBitmap()
                    }
                }
            }
        }
    }
    var modifier = Modifier.wrapContentSize()
    height?.let { modifier = modifier.height(it) }
    width?.let { modifier = modifier.width(it) }
    size?.let { modifier = modifier.size(it) }
    fullSize?.let { modifier = modifier.fillMaxSize() }
    fullWith?.let { modifier = modifier.fillMaxWidth() }
    if (borderRadius != null)
        modifier =
            modifier.clip(RoundedCornerShape(borderLeftTop!!, borderRightTop!!, borderRightBottom!!, borderLeftBottom!!))

    image.value?.let {
        Image(
            bitmap = it,
            contentDescription = null,
            contentScale = contentScale!!,
            modifier = modifier,
            colorFilter = ColorFilter.colorMatrix(colorMatrix)
        )
    }
}