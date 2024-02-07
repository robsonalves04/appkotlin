package com.example.kdsmobile.viewmodels.login

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kdsmobile.config.types.KitchenInputValidationState
import com.example.kdsmobile.extensions.navigation.KitchenScreens
import com.example.kdsmobile.extensions.navigation.navigate
import com.example.kdsmobile.models.identidade.KitchenAutenticadoModel
import com.example.kdsmobile.models.identidade.KitchenCredenciaisModel
import com.example.kdsmobile.services.auth.IKichentAuthService
import com.example.kdsmobile.services.httpclient.KitchenAPICallback
import com.example.kdsmobile.services.identidade.IKitchenIdentidadeService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class KitchenLoginViewModel(
    private val _identidadeService: IKitchenIdentidadeService,
    private val _kitchenUser: IKichentAuthService
) : ViewModel() {

    //--== Formularios Para o Login
    val login: KitchenInputValidationState = KitchenInputValidationState("305180@vibe-kds")
    val senha: KitchenInputValidationState = KitchenInputValidationState("999507")


    // --== Autenticar usuário
    fun autenticar(context: Context) {
        viewModelScope.launch {
            withContext(Dispatchers.Main) {
                _identidadeService.autenticar(context,
                    KitchenCredenciaisModel(login.field.value, senha.field.value),

                    KitchenAPICallback<KitchenAutenticadoModel>(
                        onSuccess = {
                            _kitchenUser.autenticar(context, it)
//                            context.navigate(KitchenScreens.Home)
                        },
                        onFailure = {
                            //-- Quando email e senha estiver errado
                            login.setError(it)
                            senha.setError(it)
                            Toast.makeText(context,"Email ou senha incorretos", Toast.LENGTH_LONG).show()
                        }
                    )
                )
            }
        }
    }
}