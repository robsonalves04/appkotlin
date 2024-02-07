package com.example.kdsmobile.config.types

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

class KitchenInputValidationState(private val initial: String? = "") {

    val field: MutableState<String> = mutableStateOf(initial!!)
    val error: MutableState<String> = mutableStateOf("")
    fun hasErrors(): Boolean = error.value != ""
    fun clearErrors() {
        this.error.value = "";
    }
    fun setError(problem: String) {
        this.error.value = problem;
    }
}