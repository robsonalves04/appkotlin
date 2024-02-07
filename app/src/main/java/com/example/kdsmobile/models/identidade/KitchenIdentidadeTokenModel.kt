package com.example.kdsmobile.models.identidade

import com.example.kdsmobile.extensions.jwt.hasExpired

data class KitchenIdentidadeTokenModel(
    val id: String?,
    val nome: String,
    val sobrenome: String?,
    val nbf: Long?,
    val exp: Long?,
    val iat: Long?,
) {

    // --== Validando se o token já está expirado
    fun isTokenExpired(): Boolean {
        return if (this.exp == null) true;
        else hasExpired(this.exp)
    }
}