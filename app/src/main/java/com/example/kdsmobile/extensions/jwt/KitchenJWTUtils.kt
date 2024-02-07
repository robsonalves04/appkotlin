package com.example.kdsmobile.extensions.jwt

import android.util.Base64
import com.example.kdsmobile.models.identidade.KitchenIdentidadeTokenModel
import com.google.gson.Gson
import java.lang.reflect.Type
import java.util.Date

// --== Verifica se o valor de expiração já expirou
fun hasExpired(exp: Long): Boolean {
    val expirationDate = Date(exp * 1000)
    val currentDate = Date()
    return expirationDate.before(currentDate)
}

fun String.decodeToken(): KitchenIdentidadeTokenModel {
    val parts = this.split(".")

    if (parts.size != 3)
        throw IllegalArgumentException("o token é inválido")

    // --== Obtendo corpo do token
    val payload = String(Base64.decode(parts[1], Base64.URL_SAFE))

    // --== Desserializando o token
    return payload.toClass<KitchenIdentidadeTokenModel>(KitchenIdentidadeTokenModel::class.java);
}

fun <T> String.toClass(type: Type): T {
    return Gson().fromJson<T>(this, type)
}

fun <T> T.toJson(): String {
    return Gson().toJson(this)
}