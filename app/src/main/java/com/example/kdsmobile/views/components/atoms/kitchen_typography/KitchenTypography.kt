package com.example.kdsmobile.views.components.atoms.kitchen_typography

import android.graphics.Paint
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.isUnspecified
import androidx.compose.ui.unit.sp
import com.example.kdsmobile.config.theme.theme

@Composable
fun KitchenTypography(
    text: String,
    lineHeight: TextUnit = 20.sp,
    size: TextUnit = 15F.sp,
    style: TextStyle = MaterialTheme.typography.body1,
    weight: FontWeight? = FontWeight.Normal,
    padding: PaddingValues? = PaddingValues(0.dp),
    modifier: Modifier? = Modifier,
    color: Color = Color.Black,
    textAlign: TextAlign? = TextAlign.Start,
    fontFamily: FontFamily? = FontFamily.Default,
    maxLines: Int = Int.MAX_VALUE,
    resposivel: Boolean? = false,
    tachado: Boolean ?= false

    ) {

    var resizedTextStyle by remember {
        mutableStateOf(style)
    }
    var shouldDraw by remember {
        mutableStateOf(false)
    }

    val defaultFontSize = MaterialTheme.typography.body1.fontSize

    // --== Configurar estilos para o texto
    val texto = buildAnnotatedString {
        if (tachado == true) {
            withStyle(style = SpanStyle(textDecoration = TextDecoration.LineThrough)) {
                append(text)
            }
        } else { append(text) }
    }

    androidx.compose.material3.Text(
        text = texto, fontWeight = weight!!, textAlign = textAlign,
        color = color, fontFamily = fontFamily, maxLines = maxLines,lineHeight = lineHeight,
        modifier = modifier!!.drawWithContent {
            if (shouldDraw) {
                drawContent()
            }
        },
        softWrap = resposivel!!,
        style = resizedTextStyle,
        onTextLayout = { result ->
            if (result.didOverflowWidth) {
                if (style.fontSize.isUnspecified) {
                    resizedTextStyle = resizedTextStyle.copy(
                        fontSize = defaultFontSize
                    )
                }
                resizedTextStyle = resizedTextStyle.copy(
                    fontSize = resizedTextStyle.fontSize * 0.95
                )
            } else {
                shouldDraw = true
            }
        }
    )
}
/*
    text: String,
    size: TextUnit? = 1.sp,
    sizeMap: Map<IntRange, TextUnit> = mapOf(
        0.. 150 to 10.sp,
        150..300 to 11.sp,
        300..450 to 12.sp, //celular empresa
        450..599 to 13.sp,
        600..719 to 14.sp,
        720..839 to 14.sp,
        840..959 to 20.sp, // Pixerl C
        960..1079 to 11.sp,
        1080..1199 to 13.sp,
        1199..1319 to 20.sp,
        1320..1439 to 16.sp,
        1440..1559 to 17.sp,
        1560..Int.MAX_VALUE to 18.sp
    ),

//celular empresa = 753
//celular pessoal  = 889
//tablet pixel C = 1200

    weight: FontWeight? = FontWeight.Normal,
    padding: PaddingValues? = PaddingValues(0.dp),
    color: Color = theme.colors.preto00,
    textAlign: TextAlign? = TextAlign.Start,
    maxLines: Int = Int.MAX_VALUE,
    resposivel: Boolean? = true,
    letterSpacing: TextUnit? = TextUnit.Unspecified,
    lineHeight: TextUnit? = TextUnit.Unspecified,
    tachado: Boolean ?= false
) {

    // --== Primeira parte da resposividade
    val configuration = LocalContext.current.resources.configuration
    val screenWidthDp = configuration.screenWidthDp

    val baseFontSize = sizeMap.entries.firstOrNull { it.key.contains(screenWidthDp) }?.value
        ?: sizeMap.entries.last().value

    val adjustedFontSize = (baseFontSize.value) + size!!.value


    val textSize = remember { mutableStateOf(adjustedFontSize) }
    val textLineHeight = remember { mutableStateOf(lineHeight) }
    val textLetterSpacing = remember { mutableStateOf(letterSpacing) }

    // --== Configurar estilos para o texto
    val texto = buildAnnotatedString {
        if (tachado == true) {
            withStyle(style = SpanStyle(textDecoration = TextDecoration.LineThrough)) {
                append(text)
            }
        } else { append(text) }
    }

    // --== Iniciando renderização
    Text(
        text = texto, fontSize = textSize.value.sp, fontWeight = weight!!, textAlign = textAlign,
        letterSpacing = textLetterSpacing.value!!, color = color, maxLines = maxLines,
        overflow = TextOverflow.Ellipsis, modifier = Modifier.padding(padding!!), lineHeight = textLineHeight.value!!,
        // --== Resposavel por fazer a resposividade
        onTextLayout = { textLayoutResult ->

            if (resposivel == true) {
                val lineIndex = textLayoutResult.lineCount - 1
                if (textLayoutResult.isLineEllipsized(lineIndex)) {
                    textSize.value = textSize.value * 0.9f
                    if (textLineHeight.value != TextUnit.Unspecified) {
                        textLineHeight.value = textLineHeight.value!! * 0.9f
                    }
                    if (textLetterSpacing.value != TextUnit.Unspecified) {
                        textLetterSpacing.value = textLetterSpacing.value!! * 0.9f
                    }
                }
            }
        }
    )
}
*/