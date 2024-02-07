package com.example.kdsmobile.extensions.device

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.content.ContextCompat


@SuppressLint("HardwareIds", "MissingPermission")
fun obterNumeroDeSerie(context: Context): String? {
    return if (ContextCompat.checkSelfPermission(context, android.Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            try {
                Build.getSerial()
            } catch (e: SecurityException) {
                "RXCT60392XX"
            }
        } else {
            Build.SERIAL
        }
    } else {
        "RXCT60392XX"
    }
}