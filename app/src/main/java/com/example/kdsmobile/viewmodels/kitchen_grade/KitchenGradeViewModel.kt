package com.example.kdsmobile.viewmodels.kitchen_grade

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kdsmobile.extensions.utils.kitchenSnackbar
import com.example.kdsmobile.models.grade.KitchenGradeModel
import com.example.kdsmobile.models.produto.KitchenProdutoModel
import com.example.kdsmobile.services.httpclient.KitchenAPICallback
import com.example.kdsmobile.services.produtos.IKitchenProdutoService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class KitchenGradeViewModel(
    private val produtoService: IKitchenProdutoService
) : ViewModel() {

    // --== Guardando produtos
    var produtos = MutableLiveData<KitchenGradeModel>();

    var produtosVazio = MutableLiveData<KitchenGradeModel>();

    fun obterProdutos(context: Context, page: Int) {
        viewModelScope.launch(Dispatchers.Main) {
            produtoService.grade(
                context,
                KitchenAPICallback<KitchenGradeModel>(
                    onFailure = { erro ->
                        kitchenSnackbar(context, erro)
                    },
                    onSuccess = { newProducts ->

                        if (newProducts.produtos.list?.isEmpty() == false) {

                            // Verifica se a lista atual de produtos é nula
                            if (produtos.value?.produtos?.list == null) {
                                produtos.value = newProducts
                            } else {
                                // Adiciona os novos produtos à lista existente
                                val updatedList =
                                    produtos.value?.produtos?.list.orEmpty() + newProducts.produtos.list.orEmpty()
                                produtos.value = produtos.value?.produtos?.copy(list = updatedList)
                                    ?.let { produtos.value?.copy(produtos = it) }
                            }
                        } else {

                        }
                    }
                ),
                page,
            )
        }
    }

    val testeMock = MutableLiveData<List<KitchenProdutoModel>>()
    fun onSucessTest(model: List<KitchenProdutoModel>) {
        testeMock.value = model
    }

    fun onFailedTest() {}
    fun testeCarousel(context: Context) {
        viewModelScope.launch(Dispatchers.Main) {
            val callback = KitchenAPICallback<List<KitchenProdutoModel>>(
                onFailure = { onFailedTest() },
                onSuccess = { model -> onSucessTest(model)
                }
            )
            produtoService.obterGrades(context, callback)
        }
    }
}