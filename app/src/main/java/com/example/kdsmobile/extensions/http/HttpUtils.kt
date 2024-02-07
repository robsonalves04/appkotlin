package com.example.kdsmobile.extensions.http

import com.example.kdsmobile.services.httpclient.HttpResponseMessage
import com.example.kdsmobile.services.httpclient.KitchenAPICallback

fun <D> HttpResponseMessage<D>.serializeAndResolve(options: KitchenAPICallback<D>) {
    if (!this.isOk) options.onFailure(
        this.problem ?: "Não foi possivel processar sua requisição"
    )
    else this.result?.let { options.onSuccess(it) }
}

fun HttpResponseMessage<Any>.serializeAndResponse(options: KitchenAPICallback<Any>) {
    if (!this.isOk) options.onFailure(
        this.problem ?: "Não foi possivel processar sua requisição"
    );
    else {
        options.onSuccess("")
    }
}