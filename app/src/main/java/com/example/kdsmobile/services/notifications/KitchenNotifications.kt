package com.example.kdsmobile.services.notifications

import android.annotation.SuppressLint
import android.util.Log
import com.example.kdsmobile.BuildConfig

import com.example.kdsmobile.services.httpclient.trustAllCerts
import com.google.gson.Gson
import com.microsoft.signalr.HubConnection
import com.microsoft.signalr.HubConnectionBuilder
import com.microsoft.signalr.TransportEnum
import okhttp3.logging.HttpLoggingInterceptor
import java.security.SecureRandom
import javax.net.ssl.SSLContext
import javax.net.ssl.X509TrustManager

@SuppressLint("CheckResult")
class KitchenNotifications : IKitchenNotifications {

    private var hubConnection: HubConnection =
        HubConnectionBuilder
            .create("${BuildConfig.PODSMART_WEBSOCKET}/${BuildConfig.PODSMART_WEBSOCKET_CHANNEL}")
            .setHttpClientBuilderCallback { builder ->
                val logging = HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
                builder.addInterceptor(logging)
                builder.hostnameVerifier { _, _ -> true }
                builder.sslSocketFactory(SSLContext.getInstance("TLS").apply {
                    init(null, trustAllCerts, SecureRandom())
                }.socketFactory, trustAllCerts[0] as X509TrustManager)
            }
            .shouldSkipNegotiate(true)
            .withTransport(TransportEnum.WEBSOCKETS).build()


    init {
        hubConnection.start().subscribe({

            // Conexão iniciada.
            Log.d("TicketNotifications", "Conexão com o SignalR iniciada.")
        }, { error ->

            Log.e("TicketNotifications", "Conexão com o SignalR fechada com erro: $error")
        })
    }

    override fun <T> listen(endpoint: String, clazz: Class<T>, onReceived: (data: T?) -> Unit) {
        hubConnection.on<String>(endpoint, { message ->
            try {
                Log.d("TicketNotifications", "Mensagem recebida: $message")
                val data = Gson().fromJson<T>(message, clazz)
                onReceived(data)
            } catch (e: Exception) {
                Log.e("TicketNotifications", "Erro na desserialização: ${e.message}", e)
            }
        }, String::class.java)
    }
}