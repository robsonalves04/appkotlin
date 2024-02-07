package com.example.kdsmobile.extensions.utils


import com.google.gson.Gson
import java.lang.reflect.Type

fun <T> String.toClass(type : Type) : T{
    return Gson().fromJson<T>(this, type)
}

fun <T> T.toJson() : String{
    return Gson().toJson(this)
}