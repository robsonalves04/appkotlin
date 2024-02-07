package com.example.kdsmobile.extensions.utils

import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

inline fun <reified T> getTypeToken(): Type {
    return object : TypeToken<T>() {}.type
}