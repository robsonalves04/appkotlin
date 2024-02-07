package com.example.kdsmobile.views.components.atoms.kitchen_textfield

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kdsmobile.config.theme.theme
import com.example.kdsmobile.config.types.KitchenInputValidationState
import com.example.kdsmobile.views.components.atoms.kitchen_container.KitchenWrapper
import com.example.kdsmobile.views.components.atoms.kitchen_container.KitchenWrapperAlignment
import com.example.kdsmobile.views.components.atoms.kitchen_typography.KitchenTypography

@Composable
fun KitchenTextInput(
    field: KitchenInputValidationState,
    keyboard : KeyboardType? = KeyboardType.Text,
    placeholder: String? = "null",
    imeAction: ImeAction? = ImeAction.Next
) {
    val shape = RoundedCornerShape(6.dp);

    KitchenWrapper(fullWidth = true, borderRadius = 6.dp) {
        TextField(
            value = field.field.value,
            singleLine = true,
            onValueChange = { field.field.value = it },
            placeholder = {
                placeholder?.let {
                    KitchenTypography(
                        text = it,size = 7.sp,
                        color = theme.colors.cinza04,
                        weight = FontWeight.Medium
                    )
                }
            },
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                backgroundColor = theme.colors.branco,
                unfocusedIndicatorColor = Color.Transparent,

                ),
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.None,
                autoCorrect = true,
                keyboardType = keyboard!!,
                imeAction = imeAction!!,
            ),

            shape = shape,
            textStyle = androidx.compose.ui.text.TextStyle(fontSize = 16.sp),
            modifier = Modifier
                .border(
                    1.dp,
                    if (field.hasErrors()) theme.colors.vermelho00 else theme.colors.cinza08,
                    shape
                )
                .fillMaxWidth()
        )
    }

    KitchenWrapper(vertical = KitchenWrapperAlignment.Center, height = 30.dp) {
        if (field.hasErrors())
            KitchenTypography(text = field.error.value, color = theme.colors.vermelho00)
    }
}