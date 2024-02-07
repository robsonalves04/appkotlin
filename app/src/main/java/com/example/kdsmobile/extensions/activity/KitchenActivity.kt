package com.example.kdsmobile.extensions.activity

import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.example.kdsmobile.config.routes.KitchenRoutesConfig
import com.example.kdsmobile.extensions.navigation.KitchenScreens
import com.example.kdsmobile.extensions.navigation.navigate
import com.example.kdsmobile.services.auth.IKichentAuthService
import org.koin.android.ext.android.inject
import androidx.core.view.ViewCompat



open class KitchenActivity(
    authorization : KitchenRoutesConfig
) : AppCompatActivity() {
    protected val _kitchenUser : IKichentAuthService by inject()

    private val authStatus = authorization;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // --== Inicializando validação de token de usuário
        _kitchenUser.create(this)

        hideNavigationBar();

        var teste = _kitchenUser.isValidSession();

        if(authStatus == KitchenRoutesConfig.AuthOnly && !_kitchenUser.isValidSession())
            this.navigate(KitchenScreens.Login)


        if(authStatus == KitchenRoutesConfig.AnonymousOnly && _kitchenUser.isValidSession())
            this.navigate(KitchenScreens.GradePedidos)

    }
    open fun hideNavigationBar() {
        // Verifica se a versão do Android é igual ou superior ao Android 19 (KitKat)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {

            val decorView = window.decorView
            val uiOptions = (View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
            decorView.systemUiVisibility = uiOptions

        } else {
            // Para versões anteriores ao KitKat, você pode apenas ocultar a barra inferior
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
    }
}