package com.example.kdsmobile.config.keys

class KitchenMemoKeys private constructor() {
    companion object {
        const val token: String = "UIDREFTOKEN"
        const val vendaEmAndamento: String = "VNDREFANDAMENTO"
        const val modoTerminalAtivacao: String = "MDATIVATERMINAL-"
        const val terminalId: String = "TERMIDLOCAL-"
    }
}

data class KitchenMemoChunks (

        val identidade: String = "AUTHTICKETDEF",
        val default: String = "DEFMEMOTICKET",
        val venda: String = "DEFMEMOVENDA",
        val carrinho: String = "CARTMEMREF",
        val ativacao: String = "ATIVATERMDEF",
//    const val uIdKey = "UIDREFTOKEN"


)



val KitchenMemoChunksData = KitchenMemoChunks();