package com.example.kdsmobile.services.notifications

interface IKitchenNotifications {
    fun <T> listen(endpoint: String, clazz: Class<T>, onReceived: (data: T?) -> Unit)
}