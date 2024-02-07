package com.example.kdsmobile.viewmodels.kitchen_pedido

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kdsmobile.extensions.navigation.KitchenScreens
import com.example.kdsmobile.extensions.navigation.navigate
import com.example.kdsmobile.models.pedidos.KitchenPedidoModel
import com.example.kdsmobile.models.produto.KitchenProdutoModel
import com.example.kdsmobile.models.produto.mapper
import com.example.kdsmobile.services.httpclient.KitchenAPICallback
import com.example.kdsmobile.services.pagamento.IKitchenPagPedidoService
import com.example.kdsmobile.viewmodels.kitchen_cliente.KitchenClienteViewModel
import com.example.kdsmobile.viewmodels.kitchen_fragment.KitchenFragmentsNavigationScope
import com.example.kdsmobile.viewmodels.kitchen_fragment.KitchenFragmentsViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class KitchenPedidoViewModel(
    private val ticketpagPedidoService: IKitchenPagPedidoService,
//    private val ticketNFCService: ITicketNFCService,
) : ViewModel() {

    // --== Carrinho de produtos
    val carrinho = MutableLiveData<List<KitchenProdutoModel>>(null)

    // --== Pedido atual
    val pedido = MutableLiveData<KitchenPedidoModel>(null)

    val pagFidStatus =  MutableLiveData<KitchenPagamentoFidelidadeStatus>(KitchenPagamentoFidelidadeStatus.Pronto)

    val falhaPagtoMotivo = MutableLiveData<String?>(null)

    fun adicionarAoCarrinho(produto: KitchenProdutoModel) {
        val listaAtualizada = carrinho.value.orEmpty()
            .toMutableList().apply { add(produto) }
        carrinho.postValue(listaAtualizada)
    }

    fun removerDoCarrinho(produtoId: String) {
        val listaAtualizada = carrinho.value.orEmpty().toMutableList()
        val index = listaAtualizada.indexOfFirst { it.id == produtoId }
        if (index >= 0) listaAtualizada.removeAt(index)
        carrinho.postValue(listaAtualizada)
    }

    fun esvaziarCarrinho() {

    }

    fun carrinhoBadgeTexto() {

    }

    fun finalizarVenda(context: Context, mainViewModel: KitchenFragmentsViewModel,
                       clienteViewModel : KitchenClienteViewModel
    ) {


        // --== Removendo motivo de falha anterior
        falhaPagtoMotivo.value = null;

        // --== Alterando escopo de visualização
        mainViewModel.escopoDeVisualizacao.value = KitchenFragmentsNavigationScope.PagamentoFid;

        viewModelScope.launch(Dispatchers.Main) {

            // --== Pequena pausa para remover renderização
            delay(420)

            // --== Escondendo topbar
            mainViewModel.fecharTopbar()

            // --== Escondendo navbar
            mainViewModel.fecharNavbar()

            // --== Iniciando carregamento na tela principal
            mainViewModel.iniciarCarregamento(
                "Iniciando venda",
                "Aguarde enquanto nós preparamos o processo de venda"
            )
//
//            // --== Realizando requisição de pedido
            carrinho.value?.mapper()?.let { produtos ->
                ticketpagPedidoService.pedir(context, produtos, KitchenAPICallback(
                    onSuccess = {

                        // --== Esperando NFC


                        // --== Atualizando pedido
                        pedido.value = it;

                        // --== Parando carregamento
                        mainViewModel.pararCarregamento()

                        // --== Parando carregamento
                        mainViewModel.verPagamentoFidelidade()

                        pedido.value?.valor?.let { valor ->

                            // --== Iniciando processo de pagamento

                        }

                    },
                    onFailure = {

                    }
                ))
            }
        }
    }

//    private fun iniciarPagamento(context: Context, valor: Double, clienteViewModel : KitchenClienteViewModel) {
//        pedido.value?.id?.let { pedidoId ->
//
////            ticketNFCService.pos { nfc ->
////                nfc.detect(context, 135) { detected ->
//
//                    // --== Cartão NFC detectado
//                    alterarPagFidStatus(TicketPagamentoFidelidadeStatus.Processando)
//
////                    detected.read { rawCliFidId ->
//
//                        // --== Obtendo referência de cliente
////                        val cliFidId = rawCliFidId
////                            .nfcToClass<KitchenCliFidIdModel>();
//
//                        // --== Modelo de pagamento pedido
//                        val pagPedido = KitchenPagamentoModel();
//
//                        // --== Autorizando pagamento
////                        pagPedido.authorize(cliFidId.cliFidId, pedidoId)
//
//                        // --== Definindo valor que será pago
//                        pagPedido.pagar(valor)
//
//                        // --== Realizando requisição de pagamento
//                        viewModelScope.launch {
//                            ticketpagPedidoService.pagar(context,
//                                pagPedido, KitchenAPICallback(
//                                    onSuccess = { model ->
//
//                                        model.clienteId?.let { clienteId ->
//                                            clienteViewModel.obterPedidosImpressaoPendente(context,
//                                                clienteId = clienteId);
//                                        }
//
//                                        // --== Pago com sucesso
//                                        alterarPagFidStatus(TicketPagamentoFidelidadeStatus.Sucesso)
//
//                                    },
//                                    onFailure = { motivo ->
//
//                                        // --== Pago com sucesso
//                                        alterarPagFidStatus(TicketPagamentoFidelidadeStatus.Falha)
//
//                                        // --== Enviando motivo de falha na transação
//                                        falhaPagtoMotivo.value = motivo;
//                                    }
//                                )
//                            )
//                        }
//                    }
//                }
            }
//        }
//
//    }

//    private fun alterarPagFidStatus(status: TicketPagamentoFidelidadeStatus) {
//        if (this.pagFidStatus.value != status)
//            viewModelScope.launch {
//                pagFidStatus.value = status
//            }
//    }
//}

enum class KitchenEnumPedidoProcesso {
    Finalizando, VendoCarrinho, EmAberto
}

enum class KitchenPagamentoFidelidadeStatus {
    Pronto, Processando, Sucesso, Falha
}
