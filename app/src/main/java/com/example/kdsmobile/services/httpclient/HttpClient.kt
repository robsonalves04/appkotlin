package com.example.kdsmobile.services.httpclient

import android.content.Context
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.lang.reflect.Type
import java.util.concurrent.TimeUnit
import javax.net.ssl.X509TrustManager
// --== Camada de integração
class HttpClient(
    private val baseUrl: String
) {
    // --== Agente HTTP
    private lateinit var _client: OkHttpClient
    private lateinit var _httpClientFactory: HttpClientFactory

    // --== Iniciando classe e realizando configurações
    init {
        // --== Criando base de integrador
        _httpClientFactory = HttpClientFactory(secureOnly = false)
        // --== Gerando um agente HTTP
        _client = _httpClientFactory.createClient()
    }

    // --== Função de envio de requisição POST
    suspend fun <D> postAsync(context: Context, path: String,
                              payload: Any, auth: Boolean? = false, dataType: Type
    ): HttpResponseMessage<D> {
        return _httpClientFactory.postFactoryAsync(context = context,
            body = payload, authorize = auth!!, _requester = _client,
            dataType = dataType, path = "$baseUrl/$path"
        )
    }

    // --== Função de envio de requisição GET
    suspend fun <D> getAsync(context: Context, path: String,
                             auth: Boolean? = false, dataType: Type
    ): HttpResponseMessage<D> {
        return _httpClientFactory.getFactoryAsync(context = context, authorize = auth!!,
            _requester = _client, dataType = dataType, path = "$baseUrl/$path"
        )
    }

    // --== Função de envio de requisição PATCH
    suspend fun <D> patchAsync(context: Context, path: String,
                               payload: Any, auth: Boolean? = false, dataType: Type
    ): HttpResponseMessage<D> {
        return _httpClientFactory.patchFactoryAsync(context = context, body = payload,
            authorize = auth!!, _requester = _client,
            dataType = dataType, path = "$baseUrl/$path"
        )
    }

    // --== Função de envio de requisição PUT
    suspend fun <D> putAsync(context: Context, path: String,
                             payload: Any, auth: Boolean? = false, dataType: Type
    ): HttpResponseMessage<D> {
        return _httpClientFactory.putFactoryAsync(context = context, body = payload,
            authorize = auth!!, _requester = _client,
            dataType = dataType, path = "$baseUrl/$path"
        )
    }

    // --== Função de envio de requisição DELETE
    suspend fun <D> deleteAsync(context: Context, path: String,
                                auth: Boolean? = false, dataType: Type
    ): HttpResponseMessage<D> {
        return _httpClientFactory.deleteFactoryAsync(context = context, authorize = auth!!,
            _requester = _client, dataType = dataType, path = "$baseUrl/$path"
        )
    }
}