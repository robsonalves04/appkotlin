package com.example.kdsmobile.init


import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.ui.unit.dp
import com.example.kdsmobile.config.routes.KitchenRoutesConfig
import com.example.kdsmobile.config.theme.Dimensions
import com.example.kdsmobile.extensions.activity.KitchenActivity
import com.example.kdsmobile.services.auth.IKichentAuthService
import com.example.kdsmobile.services.auth.KitchenAuthService
import com.example.kdsmobile.services.identidade.IKitchenIdentidadeService
import com.example.kdsmobile.services.identidade.KitchenIdentidadeService
import com.example.kdsmobile.services.memo.IKitchenMemo
import com.example.kdsmobile.services.memo.KitchenMemo
import com.example.kdsmobile.services.notifications.IKitchenNotifications
import com.example.kdsmobile.services.notifications.KitchenNotifications
import com.example.kdsmobile.services.pagamento.IKitchenPagPedidoService
import com.example.kdsmobile.services.pagamento.KitchenPagPedidoService
import com.example.kdsmobile.services.produtos.IKitchenProdutoService
import com.example.kdsmobile.services.produtos.KitchenProdutoService
import com.example.kdsmobile.services.status_pedidos.IKitchenStatusPedidoService
import com.example.kdsmobile.services.status_pedidos.KitchenStatusPedidoService
import com.example.kdsmobile.viewmodels.kitchen_fragment.KitchenFragmentsViewModel
import com.example.kdsmobile.viewmodels.kitchen_grade.KitchenGradeViewModel
import com.example.kdsmobile.viewmodels.kitchen_pedido.KitchenPedidoViewModel
import com.example.kdsmobile.viewmodels.login.KitchenLoginViewModel
import com.example.kdsmobile.viewmodels.mesas.KitchenMesasViewModel
import com.example.kdsmobile.viewmodels.status_pedido.StatusPedidoViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel

import org.koin.core.context.startKoin
import org.koin.dsl.module

class Startup :Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@Startup)
            modules(module {

                //--== Injetando Configuração de Dimensão
                val config = (this@Startup).resources.configuration;

                single { Dimensions((config.screenHeightDp).dp, (config.screenWidthDp).dp) }

                // --== Injetando Serviços de entidade
                single<IKitchenMemo> { KitchenMemo() }
                single<IKichentAuthService> { KitchenAuthService(get()) }
                single <IKitchenStatusPedidoService>{KitchenStatusPedidoService()}
                single <IKitchenProdutoService>{ KitchenProdutoService() }
                single<IKitchenPagPedidoService> { KitchenPagPedidoService() }
                single<IKitchenNotifications> { KitchenNotifications(/*TicketSSLContextBuilder(this@Startup)*/) }

                // --== Injetando serviços de autenticação
                single<IKitchenIdentidadeService> { KitchenIdentidadeService() }



                viewModel { KitchenLoginViewModel(get(), get()) }

                viewModel{ StatusPedidoViewModel(get())}

                viewModel{ KitchenGradeViewModel(get())}

                viewModel { KitchenFragmentsViewModel() }

                viewModel { KitchenPedidoViewModel(get()) }

                viewModel { KitchenMesasViewModel()}

            })
        }
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }
}