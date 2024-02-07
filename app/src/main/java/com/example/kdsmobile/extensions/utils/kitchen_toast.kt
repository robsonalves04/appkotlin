package com.example.kdsmobile.extensions.utils

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

fun KitchenToast(context: Context, message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(context, message, duration).show()
}

fun kitchenSnackbar(context: Context, message: String, duration: Int = Snackbar.LENGTH_SHORT) {
    val snackbar = Snackbar.make((context as Activity).findViewById(android.R.id.content), message, duration)

    // Altera a cor de fundo da Snackbar
    snackbar.view.setBackgroundColor(Color.RED)

    snackbar.show()
}