package com.example.kdsmobile.models.paged

 data class KitchenPagedResult<T>(
    val list : List<T>?,
    val totalPages : Int ?= 0
)