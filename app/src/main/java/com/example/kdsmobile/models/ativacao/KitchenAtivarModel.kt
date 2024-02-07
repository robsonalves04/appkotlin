package com.example.kdsmobile.models.ativacao

import android.content.Context
import com.example.kdsmobile.config.keys.KitchenAtivacaoKeys
import com.example.kdsmobile.config.keys.KitchenMemoChunksData
import com.example.kdsmobile.extensions.device.obterNumeroDeSerie
import com.example.kdsmobile.extensions.jwt.toClass
import com.example.kdsmobile.services.memo.KitchenMemo

data class KitchenAtivarModel (
    var terminalId : String ?= null,
    var codigoSerial : String ?= null,
    var codigoAtivacao : String ?= null
){
    fun map(context : Context) : KitchenAtivarModel {
        this.codigoSerial = obterNumeroDeSerie(context);

        // --== Obtendo modelo de terminal em ativação
        val model = KitchenMemo()
            .find(context, KitchenAtivacaoKeys.emAtivacao,
             KitchenMemoChunksData.ativacao)

        if(!model.isNullOrEmpty()){
            val terminal = model
                .toClass<KitchenProntoParaAtivarModel>(KitchenProntoParaAtivarModel::class.java)

            // --== Alocando terminal id
            this.terminalId = terminal.TerminalId;
        }

        return this;
    }

    fun tentar(codigo : String) : KitchenAtivarModel {
        this.codigoAtivacao = codigo;
        return this;
    }
}