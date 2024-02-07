package com.example.kdsmobile.config.mocks.pedidos

import com.example.kdsmobile.models.pedidos.KitchenComplementoPedidoModel
import com.example.kdsmobile.models.pedidos.KitchenItensPedidoModel
import com.example.kdsmobile.models.pedidos.KitchenPedidoModelMockTeste
import com.example.kdsmobile.models.pedidos.KitchenTipoPedidosModel

val pedidosMock = listOf<KitchenPedidoModelMockTeste>(

    KitchenPedidoModelMockTeste(
        listOf<KitchenTipoPedidosModel>(
            KitchenTipoPedidosModel(
                tipo = "Pratos",
                listOf<KitchenItensPedidoModel>(
                    KitchenItensPedidoModel(
                        nome = "Caprese  Salad with pesto sauce",
                        complementos = listOf<KitchenComplementoPedidoModel>(
                            KitchenComplementoPedidoModel("1X Caprese"),
                            KitchenComplementoPedidoModel("1x molho do Caprese"),
                            KitchenComplementoPedidoModel("1x pesto do Caprese")
                        ),
                        valor = 150.00
                    ),
                    KitchenItensPedidoModel(
                        nome = "Macarrão com salsicha",
                        complementos = listOf<KitchenComplementoPedidoModel>(
                            KitchenComplementoPedidoModel("macarrão"),
                            KitchenComplementoPedidoModel("salsicha"),
                        ),
                        valor = 180.00
                    ),
                )
            ),
            KitchenTipoPedidosModel(
                tipo = "Bebidas",
                listOf<KitchenItensPedidoModel>(
                    KitchenItensPedidoModel(
                        nome = "Coca Cola ",
                        complementos = listOf<KitchenComplementoPedidoModel>(
                            KitchenComplementoPedidoModel("Com Gelo"),
                            KitchenComplementoPedidoModel("Limão espremido"),
                        ),
                        valor = 18.00
                    ),
                    KitchenItensPedidoModel(
                        nome = "Água mineral",
                        complementos = listOf<KitchenComplementoPedidoModel>(),
                        valor = 15.00
                    ),
                )
            ),
        )
    ),

    KitchenPedidoModelMockTeste(
        listOf<KitchenTipoPedidosModel>(
            KitchenTipoPedidosModel(
                tipo = "Bebidas",
                listOf<KitchenItensPedidoModel>(
                    KitchenItensPedidoModel(
                        nome = "Coca Cola ",
                        complementos = listOf<KitchenComplementoPedidoModel>(
                            KitchenComplementoPedidoModel("Com Gelo"),
                            KitchenComplementoPedidoModel("Limão espremido"),
                        ),
                        valor = 18.00
                    ),
                    KitchenItensPedidoModel(
                        nome = "Água mineral",
                        complementos = listOf<KitchenComplementoPedidoModel>(),
                        valor = 15.00
                    ),
                )
            ),
            KitchenTipoPedidosModel(
                tipo = "Pratos",
                listOf<KitchenItensPedidoModel>(
                    KitchenItensPedidoModel(
                        nome = "Caprese  Salad with pesto sauce",
                        complementos = listOf<KitchenComplementoPedidoModel>(
                            KitchenComplementoPedidoModel("1X Caprese"),
                            KitchenComplementoPedidoModel("1x molho do Caprese"),
                            KitchenComplementoPedidoModel("1x pesto do Caprese")
                        ),
                        valor = 180.00
                    ),
                    KitchenItensPedidoModel(
                        nome = "Macarrão com salsicha",
                        complementos = listOf<KitchenComplementoPedidoModel>(
                            KitchenComplementoPedidoModel("macarrão"),
                            KitchenComplementoPedidoModel("salsicha"),
                        ),
                        valor = 158.00
                    ),
                )
            ),
        )
    ),
)