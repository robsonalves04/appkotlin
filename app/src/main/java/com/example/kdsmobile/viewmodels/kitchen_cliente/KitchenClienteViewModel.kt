package com.example.kdsmobile.viewmodels.kitchen_cliente

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class KitchenClienteViewModel(
//    private val ticketNFCService: ITicketNFCService,
//    private val ticketClienteService: ITicketClienteService,
//    private val ticketPrinterService: ITicketPrinterService,
//    private val ticketPedidoService: ITicketPedidoService,
) : ViewModel() {

    val vincularCartaoStatus =
        MutableLiveData<KitchenVincularCartaoNFCStatus>(KitchenVincularCartaoNFCStatus.Esperando);

    // --== Armazenando estado do cliente
//    val cliente = MutableLiveData<TicketClientePerfilModel?>(TicketClientePerfilModel())

    // --== Obter pedidos pendentes para impressão do cliente
//    val clientePedidosImpressaoPendentes = MutableLiveData(TicketPedidoProdutoModel())

//    fun limpar() {
//        this.cliente.value = null;
//        clientePedidosImpressaoPendentes.value = null;
//    }

//    fun obterClientePorCPF(
//        context: Context, cpf: String,
//        onClienteEncontrado: (() -> Unit)? = null, onClienteNaoEncontrado: (() -> Unit)? = null,
//    ) {
//        viewModelScope.launch {
//            ticketClienteService.validarCPF(context, cpf,
//                options = KitchenAPICallback<TicketClientePerfilModel>(
//                    onSucess = {
//                        cliente.value = it;
//
//                        it.id?.let { clienteId ->
//                            obterPedidosImpressaoPendente(context, clienteId)
//                        }
//
//                        onClienteEncontrado?.invoke()
//                    },
//                    onFailure = {
//                        onClienteNaoEncontrado?.invoke()
//                    }
//                )
//            )
//        }
//    }


//    fun vincularClienteVibeCard(context: Context, mainViewModel: TicketFragmentsViewModel) {
//
//        // --== Marcando estado como de aguardando
//        alterarVincularCartaoStatus(TicketVincularCartaoNFCStatus.Esperando)
//
//        cliente.value?.id?.let { clienteId ->
//
//            // --== Iniciando serviço NFC
//            ticketNFCService.pos { nfc ->
//                nfc.detect(context, 135) { detected ->
//
//                    // --== Cartão detectado, iniciando operação
//                    detected.use { card ->
//
//                        // --== Cartão identificado no leitor -NÃO AFASTE O CARTÃO-
//                        alterarVincularCartaoStatus(TicketVincularCartaoNFCStatus.EmUso)
//
//                        viewModelScope.launch(Dispatchers.Main) {
//
//                            ticketClienteService.vincularFidCard(context,
//                                TicketVincularFidelidadeModel(
//                                    clienteId = clienteId,
//                                    vCardNum = card.id.toString()
//                                ), TicketAPICallback(
//                                    onSucess = { model ->
//
//                                        // --== Salvando cartão -NÃO AFASTE O CARTÃO-
//                                        alterarVincularCartaoStatus(TicketVincularCartaoNFCStatus.EmUso)
//
//                                        // --== Escrevendo conteúdo de identificação no cartão
//                                        detected.write(model.classToNFC())
//
//                                        // --== Processo de vinculação finalizado
//                                        alterarVincularCartaoStatus(TicketVincularCartaoNFCStatus.Sucesso)
//
//                                        // --== Voltando para o dados do cliente
//                                        mainViewModel.verDadosDoCliente()
//
//                                    },
//                                    onFailure = {
//                                        alterarVincularCartaoStatus(TicketVincularCartaoNFCStatus.Erro)
//                                    }
//                                )
//                            )
//                        }
//                    }
//                }
//            }
//
//        }
//    }

//    fun obterPedidosImpressaoPendente(context: Context, clienteId: String) {
//        viewModelScope.launch {
//            ticketClienteService.obterPedidosClienteImpressaoPendente(context, clienteId,
//                TicketAPICallback(
//                    onSucess = {
//                        clientePedidosImpressaoPendentes.value = it;
//                    },
//                    onFailure = {
//
//                    }
//                )
//            )
//        }
//    }

//    fun imprimirConsumacaoProduto(context: Context, produtoId: String, pedidoId: String, onImpresso : (() -> Unit)? = null) {
//        viewModelScope.launch {
//            ticketPedidoService.imprimir(context, pedidoId, produtoId,
//                TicketAPICallback(
//                    onSucess = { model ->
//                        (context as Activity?)?.let { activity ->
//                            ticketPrinterService.imprimirFidelidade(model.nota, activity)
//                        }
//
//                        // --== Confirmando impressão de consumação
//                        confirmarImpressao(context, produtoId, pedidoId)
//
//                        // --== Chamando callback de impressão
//                        onImpresso?.invoke()
//                    },
//                    onFailure = {
//
//                    }
//                )
//            )
//        }
//
//    }

    private fun alterarVincularCartaoStatus(status: KitchenVincularCartaoNFCStatus) {
        viewModelScope.launch(Dispatchers.Main) {
            vincularCartaoStatus.value = status
        }
    }

//    private fun confirmarImpressao(context: Context, produtoId: String, pedidoId: String){
//        viewModelScope.launch {
//            ticketPedidoService.confirmarImpressao(context, pedidoId, produtoId,
//                TicketAPICallback(
//                    onSucess = { },
//                    onFailure = {
//                        throw Exception("Falha na confirmação")
//                    }
//                )
//            )
//        }
//    }

    // --== Partes do Codigo que estavam no TicketCadastroClienteViewModel

    // --== Variaveis dos inputs
    var nome: MutableState<String> = mutableStateOf("")
    var sobrenome: MutableState<String> = mutableStateOf("")
    var cpf: MutableState<String> = mutableStateOf("")
    var dataNasc: MutableState<String> = mutableStateOf("")


    var hasErrorNome: MutableState<Boolean> = mutableStateOf(false)
    var hasErrorSobreNome: MutableState<Boolean> = mutableStateOf(false)
    var hasErrorCpf: MutableState<Boolean> = mutableStateOf(false)
    var hasErrorDataDeNascimento: MutableState<Boolean> = mutableStateOf(false)

    // --== Função para o handleSubmit
//    fun getClienteFormValues(): KitchenClienteModel {
//
//        // --== Formata a Data para ficar de acordo com oque a SWAGGER Pede
//        val dataString = this.dataNasc.value
//        val dataStringComHora = dataString + "000000.000"
//        val dateFormat = SimpleDateFormat("ddMMyyyyHHmmss.SSS")
//        dateFormat.timeZone = TimeZone.getTimeZone("UTC")
//        val data = dateFormat.parse(dataStringComHora)
//        val dateFormatDesejado = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
//        dateFormatDesejado.timeZone = TimeZone.getTimeZone("UTC")
//        val dataFormatada = dateFormatDesejado.format(data)
//
//        return KitchenClienteModel(
//            cpf = this.cpf.value,
//            nome = this.nome.value,
//            sobrenome = this.sobrenome.value,
//            dataNasc = dataFormatada.toString(),
//        )
//    }

//    fun limparClientesFormValues(): KitchenClienteModel{
//
//        this.cliente.value = null
//
//        return KitchenClienteModel(
//            cpf = null,
//            nome = null,
//            sobrenome = null,
//            dataNasc = null,
//        )
//    }
//
//}

    enum class KitchenVincularCartaoNFCStatus {
        Esperando, EmUso, Sucesso, Erro
    }
}