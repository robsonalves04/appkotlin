package com.example.kdsmobile.services.memo

import android.content.Context
import com.example.kdsmobile.config.keys.KitchenMemoChunks

interface IKitchenMemo {
    fun save(context : Context, key: String, value : String, chunk : String = KitchenMemoChunks().default)
    fun edit(context : Context, key: String, chunk : String, newValue : String)
    fun exists(context: Context, chunk: String, refId : String) : Boolean
    fun remove(context: Context, key: String, chunk: String)
    fun find(context : Context, key: String, chunk : String = KitchenMemoChunks().default) : String?
    fun <T> getAll(context: Context, chunk: String, tClass: Class<T>): List<T>
}