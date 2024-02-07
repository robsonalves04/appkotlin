package com.example.kdsmobile.services.auth

import android.content.Context
import com.example.kdsmobile.models.identidade.KitchenAutenticadoModel

interface IKichentAuthService {

    fun create(context: Context)
    fun autenticar(context: Context, model: KitchenAutenticadoModel)
    fun logout(context: Context)
    fun isValidSession(): Boolean
}