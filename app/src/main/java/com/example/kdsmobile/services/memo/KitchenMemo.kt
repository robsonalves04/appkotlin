package com.example.kdsmobile.services.memo

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson

class KitchenMemo : IKitchenMemo {

    override fun save(context : Context, key: String, value : String, chunk : String){
        val sharedPreferences = context
            .getSharedPreferences(chunk, Context.MODE_PRIVATE)
        // --== Criando editor
        val editor = sharedPreferences.edit()
        editor.putString(key, value)
        // --== Salvando alterações
        editor.apply()
    }

    override fun edit(context: Context, key: String, chunk: String, newValue: String) {
        TODO("Not yet implemented")
    }

    override fun remove(context: Context, key: String, chunk: String){
        val sharedPreferences = context
            .getSharedPreferences(chunk, Context.MODE_PRIVATE)
        // --== Criando editor
        val editor = sharedPreferences.edit()
        editor.remove(key)
        // --== Salvando alterações
        editor.apply()
    }

    override fun find(context : Context, key: String, chunk : String) : String?{
        val sharedPreferences = context
            .getSharedPreferences(chunk, Context.MODE_PRIVATE)


        // --== Retornando encontrado
        return sharedPreferences.getString(key, null)
    }

    override fun <T> getAll(context: Context, chunk: String, tClass: Class<T>): List<T> {
        return getAllTyped(context, chunk, tClass)
    }

    override fun exists(context: Context, chunk: String, refId : String) : Boolean{
        val sharedPreferences = context
            .getSharedPreferences(chunk, Context.MODE_PRIVATE)

        return sharedPreferences.contains(refId)
    }


    // --== Obter todos os registro e converter para o tipo esperado
    private fun <T> getAllTyped(context: Context, chunk: String, tClass: Class<T>): List<T> {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences(chunk, Context.MODE_PRIVATE)
        val allEntries: Map<String, *> = sharedPreferences.all
        val gson = Gson()
        val items: MutableList<T> = mutableListOf()

        for ((_, value) in allEntries) {
            if (tClass.isInstance(value)) {
                items.add(value as T)
            } else if (value is String) {
                try {
                    val item: T = gson.fromJson(value, tClass)
                    items.add(item)
                } catch (_: Exception) { }
            }
        }
        return items
    }
}
