package com.example.kdsmobile.viewmodels.status_pedido

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kdsmobile.models.status_pedido.KitchenStatusPedidoModel
import com.example.kdsmobile.services.httpclient.KitchenAPICallback
import com.example.kdsmobile.services.status_pedidos.IKitchenStatusPedidoService
import kotlinx.coroutines.launch

class StatusPedidoViewModel(
    private val _statusService:IKitchenStatusPedidoService
):ViewModel() {

    val status =  MutableLiveData<List<KitchenStatusPedidoModel>>()

    fun onSucessStatus(model: List<KitchenStatusPedidoModel>){
        status.value = model
    }
    fun onFailedStatus(){}

    fun obterStatus(context: Context){
        viewModelScope.launch {
            val callback = KitchenAPICallback<List<KitchenStatusPedidoModel>>(
                onSuccess = {model-> onSucessStatus(model)},
                onFailure = {onFailedStatus()}
            )
            _statusService.obterStatus(context, callback)
        }
    }
}