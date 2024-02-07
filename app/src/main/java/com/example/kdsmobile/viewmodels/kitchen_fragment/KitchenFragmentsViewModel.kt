package com.example.kdsmobile.viewmodels.kitchen_fragment

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kdsmobile.config.screen.KitchenFragmentConfig
import com.example.kdsmobile.config.screen.KitchenFragmentSliderView
import com.example.kdsmobile.config.screen.KitchenNavbarSliderView
import com.example.kdsmobile.config.screen.KitchenTopbarSliderView
import com.example.kdsmobile.config.screen.carrinhoFragmentConfig
import com.example.kdsmobile.config.screen.confirmarPedidoFragmentConfig
import com.example.kdsmobile.config.screen.gradeFragmentConfig
import com.example.kdsmobile.config.screen.homeFragmentConfig
import com.example.kdsmobile.config.screen.selecionarMesaFragmentConfig
import com.example.kdsmobile.config.theme.offsetNavbarVisivel
import com.example.kdsmobile.config.theme.offsetTopBarVisivel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class KitchenFragmentsViewModel(
) : ViewModel() {

    // --== Referência da tela atual
    private val telaAnterior = mutableStateOf<KitchenFragmentsNavigation?>(null);

    // --== Referência da tela atual
    val telaAtual = MutableLiveData<KitchenFragmentConfig>(homeFragmentConfig);

    // --== Manipulador da mesa (Fragmento) de Confirmação do Pedido
    val homeFragmentView = MutableLiveData<KitchenFragmentSliderView>(KitchenFragmentSliderView());

    // --== Manipulador da tela (Fragmento) da grade
    val gradeFragmentView = MutableLiveData<KitchenFragmentSliderView>(KitchenFragmentSliderView());

    // --== Manipulador da tela (Fragmento) do carrinho
    val carrinhoFragmentView =
        MutableLiveData<KitchenFragmentSliderView>(KitchenFragmentSliderView());

    // --== Manipulador da mesa (Fragmento) de Confirmação do Pedido
    val selecionarMesaFragmentView =
        MutableLiveData<KitchenFragmentSliderView>(KitchenFragmentSliderView());

    // --== Manipulador de confirmar pedido (Fragmento) de Confirmação do Pedido
    val confirmarPedidoFragmentView =
        MutableLiveData<KitchenFragmentSliderView>(KitchenFragmentSliderView());


    // --== Manipulador da tela (Fragmento) de carregamento
    val carregamentoFragmentView =
        MutableLiveData<KitchenFragmentSliderView>(KitchenFragmentSliderView());


    // --== Alterando visibilidade
    val isCarregamentoVisible = MutableLiveData(false)

    // --=========================================================--\\
    val escopoDeVisualizacao = MutableLiveData(KitchenFragmentsNavigationScope.Home)

    // --== Referência de qual tela responde à qual controlador
    private val refFragmentViewMap = mapOf(

        KitchenFragmentsNavigation.Home to homeFragmentView,
        KitchenFragmentsNavigation.Grade to gradeFragmentView,
        KitchenFragmentsNavigation.Carrinho to carrinhoFragmentView,
        KitchenFragmentsNavigation.ConfirmarPedido to confirmarPedidoFragmentView,
    )

    // --=================================
    // --== Inicio das funções de navegação
    // --=================================

    fun verHome() {

        // --== Irá abrir a navbar, caso esteja fechada
        fecharTopbar()

        fecharNavbar()

        // --== Irá abrir a navbar, caso esteja fechada
        alterarEscopoDeVisualizacao(KitchenFragmentsNavigationScope.Home)

        // --== Vendo a tela de grade
        fecharTodosEAbrir(abrir = KitchenFragmentsNavigation.Home)
    }

    fun verGrade() {
        // --== Irá abrir a navbar, caso esteja fechada
        abrirNavbar()

        abrirTopbar()

        alterarEscopoDeVisualizacao(KitchenFragmentsNavigationScope.Grade)

        // --== Marcando como anterior
        marcarComoAnterior(alvo = KitchenFragmentsNavigation.Home)
        // --== Vendo a tela de grade
        fecharTodosEAbrir(abrir = KitchenFragmentsNavigation.Grade)
    }

    fun verCarrinho() {

        // --== Irá abrir a navbar, caso esteja fechada
        abrirNavbar()

        abrirTopbar()

        alterarEscopoDeVisualizacao(KitchenFragmentsNavigationScope.Grade)

        // --== Marcando como anterior
        marcarComoAnterior(alvo = KitchenFragmentsNavigation.Grade)

        // --== Vendo a tela de carrinho
        fecharTodosEAbrir(abrir = KitchenFragmentsNavigation.Carrinho)
    }

    fun selecionarMesa() {

        // --== Marcando como anterior
        marcarComoAnterior(alvo = KitchenFragmentsNavigation.Carrinho)

        // --== Vendo a tela de carrinho
        fecharTodosEAbrir(abrir = KitchenFragmentsNavigation.SelecionarMesa)
    }

    fun verConfirmarPedido() {

        // --== Marcando como anterior
        marcarComoAnterior(alvo = KitchenFragmentsNavigation.Carrinho)

        // --== Vendo a tela de carrinho
        fecharTodosEAbrir(abrir = KitchenFragmentsNavigation.ConfirmarPedido)
    }


    fun verPagamentoFidelidade() {

        // --== Alterando escopo de visualização
        alterarEscopoDeVisualizacao(KitchenFragmentsNavigationScope.PagamentoFid)

        // --== Marcando como anterior
        marcarComoAnterior(alvo = KitchenFragmentsNavigation.Carrinho)

        // --== Vendo a tela de pagamento por fidelidade
        fecharTodosEAbrir(abrir = KitchenFragmentsNavigation.PagamentoFidelidade)
    }


    // --=================================
    // --== Fim das funções de navegação
    // --=================================

    fun iniciarCarregamento(title: String, desc: String) {

        // --== Fechando todas as telas
        fecharTodos()

        // --== Inserindo o titúlo e descrição
        carregamentoFragmentView.value?.atualizar(title, desc)

        // --== Trazendo tela de carregamento
        carregamentoFragmentView.value?.trazer()

        viewModelScope.launch {
            delay(500)

            // --== Mostrando carregamento
            isCarregamentoVisible.value = true;
        }

    }

    fun pararCarregamento() {

        // --== Trazendo tela de carregamento
        carregamentoFragmentView.value?.passar()

        viewModelScope.launch {
            delay(500)

            // --== Mostrando carregamento
            isCarregamentoVisible.value = false;
        }
    }

    private fun fecharTodosEAbrir(abrir: KitchenFragmentsNavigation) {

        // --== Atualizando referência
        telaAtual.value = dadosDaTela(abrir);

        // --== Mapeando e alterando visibilidade
        refFragmentViewMap.map { (id, fragmentControllerView) ->
            fragmentControllerView.value?.let { fragment ->

                // --== Caso não seja o atual e esteja visivel, então fechar
                if (id != abrir && fragment.estaVisivel)
                    fragmentControllerView.value?.passar()

                // --== Caso não esteja visivel e seja o atual, então abrir
                if (id == abrir && (!(fragment.estaVisivel)))
                    fragmentControllerView.value?.trazer()
            }
        }
    }

    private fun fecharTodos() {

        // --== Mapeando e alterando visibilidade
        refFragmentViewMap.map { (id, fragmentControllerView) ->
            fragmentControllerView.value?.let { fragment ->

                // --== Caso esteja visível, então fechar
                if (fragment.estaVisivel)
                    fragmentControllerView.value?.passar()
            }
        }
    }

    private fun dadosDaTela(tela: KitchenFragmentsNavigation): KitchenFragmentConfig {
        return when (tela) {
            KitchenFragmentsNavigation.Home -> homeFragmentConfig
            KitchenFragmentsNavigation.Grade -> gradeFragmentConfig
            KitchenFragmentsNavigation.Carrinho -> carrinhoFragmentConfig
            KitchenFragmentsNavigation.SelecionarMesa -> selecionarMesaFragmentConfig
            KitchenFragmentsNavigation.ConfirmarPedido -> confirmarPedidoFragmentConfig
            else -> homeFragmentConfig
        }
    }

    private fun marcarComoAnterior(alvo: KitchenFragmentsNavigation? = null) {
        telaAnterior.value = alvo;
    }


    fun voltar(onBackLimit: () -> Unit) {
        if (this.telaAnterior.value == null)
            onBackLimit()
        else voltar()
    }

    private fun voltar() {
        when (this.telaAnterior.value) {
            KitchenFragmentsNavigation.Home -> verHome()
            KitchenFragmentsNavigation.Grade -> verGrade()
            KitchenFragmentsNavigation.Carrinho -> verCarrinho()


            else -> {}
        }
    }

    // --== Manipulador da tela (Fragmento) de buscar cliente
    val topbarView = MutableLiveData<KitchenTopbarSliderView>(KitchenTopbarSliderView());

    // --== Manipulador da tela (Fragmento) de buscar cliente
    val navBarView = MutableLiveData<KitchenNavbarSliderView>(KitchenNavbarSliderView());

    fun alterarEscopoDeVisualizacao(scope: KitchenFragmentsNavigationScope) {
        if (this.escopoDeVisualizacao.value != scope)
            this.escopoDeVisualizacao.value = scope;
    }

    fun fecharNavbar() = navBarView.value?.mandarPraBaixo()
    fun abrirNavbar() {
        navBarView.value?.targetOffSet?.value?.let {
            if (it != offsetNavbarVisivel)
                navBarView.value?.puxarPraCima()
        }
    }

    fun fecharTopbar() = topbarView.value?.mandarPraCima()
    fun abrirTopbar() {
        topbarView.value?.targetOffSet?.value?.let {
            if (it != offsetTopBarVisivel)
                topbarView.value?.puxarPraBaixo()
        }
    }
}


enum class KitchenFragmentsNavigation {
    Home, Grade, Carrinho, SelecionarMesa, ConfirmarPedido, BuscarCliente, Pesquisa,
    PagamentoFidelidade,

}

enum class KitchenFragmentsNavigationScope {
    Home, Grade, Carrinho, SelecionarMesa, PagamentoFid,
}


