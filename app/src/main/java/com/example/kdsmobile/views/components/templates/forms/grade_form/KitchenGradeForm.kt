package com.example.kdsmobile.views.components.templates.forms.grade_form

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kdsmobile.config.theme.theme
import com.example.kdsmobile.views.components.atoms.kitchen_container.KitchenWrapper
import com.example.kdsmobile.views.components.atoms.kitchen_container.KitchenWrapperAlignment
import com.example.kdsmobile.views.components.atoms.kitchen_icon.KitchenIcon
import com.example.kdsmobile.views.components.atoms.kitchen_paper.KitchenLineSpace
import com.example.kdsmobile.views.components.atoms.kitchen_sticker.ImageSource
import com.example.kdsmobile.views.components.atoms.kitchen_sticker.KitchenSticker
import com.example.kdsmobile.views.components.atoms.kitchen_typography.KitchenTypography


@Composable
fun KitchenSearchForm() {
    val context = LocalContext.current
    var searchResult by remember { mutableStateOf("") }
    var searchItems by remember { mutableStateOf<List<String>>(emptyList()) }
    var sortedItems by remember { mutableStateOf<List<String>>(emptyList()) }
    val buscasRecentesManager = BuscasRecentesManager(context)
    val recentContacts = remember { buscasRecentesManager.getBuscasRecentes() }

    // --== PROVISÓRIO PARA TESTES LISTA DE ITENS
    searchItems = listOf(
        "Maçã1",
        "Manga2",
        "Melancia3",
        "Morango4",
        "Uva5",
        "Pêra6",
        "Abacaxi7",
        "Alface8",
        "Ameixa9",
        "Cenoura10"
    )



    // --== Exibe o campo de pesquisa e os resultados da pesquisa
    SearchTextField { searchText ->
        // --== Aqui você pode lidar com a ação de pesquisa
        searchResult = "Você pesquisou: $searchText"
        // --== Filtra os itens com base no texto digitado
        sortedItems = filterAndSortItems(searchItems, searchText)
    }

    // --== Verifica se a pesquisa foi iniciada ou não
    if (searchResult.isEmpty()) {
        KitchenTypography(
            text = "Buscas Recentes", weight = FontWeight.Bold,
            size = 18.sp
        )

        // Exibir as buscas recentes salvas
        recentContacts.forEach { contact ->
            KitchenWrapper(inline = true, horizontal =  KitchenWrapperAlignment.SpaceBetween) {
                KitchenTypography(
                    text = contact, size = 16.sp, color = theme.colors.cinza04,
                    padding = PaddingValues(16.dp)
                )
                KitchenIcon(
                    icon = Icons.Default.Close,
                    onClick = { buscasRecentesManager.removerBuscaRecente(contact) }
                )
            }
        }
    } else {
        KitchenWrapper() {
            KitchenTypography(
                text = "Você procura por", weight = FontWeight.Bold,
                size = 18.sp
            )

            sortedItems.forEach { item ->
                // Não salve as buscas recentes aqui
                KitchenWrapper(inline = true, vertical = KitchenWrapperAlignment.Center) {
                    KitchenWrapper(
                        borderRadius = 90.dp,
                        width = 40.dp,
                        height = 40.dp,
                        backgroundColor = theme.colors.preto01, clickable = true,
                        onClick = { buscasRecentesManager.salvarBuscaRecente(item) }
                    ) {
                        KitchenSticker(source = ImageSource.Url("https://st.depositphotos.com/1364913/1440/i/450/depositphotos_14400923-stock-photo-glass-of-caipirinha-with-crushed.jpg"))
                    }
                    KitchenTypography(
                        text = item, size = 16.sp, color = theme.colors.cinza04,
                        padding = PaddingValues(16.dp)
                    )
                }
            }
            KitchenLineSpace(size = 80.dp)
            KitchenTypography(
                text = "Aqui estão todos os itens relacionados à sua pesquisa.",  size = 14.sp
            )
        }
    }
}



@Composable
fun SearchTextField(
    onSearch: (String) -> Unit
) {
    var searchText by remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current

    Box {
        TextField(
            value = searchText,
            onValueChange = {
                searchText = it
                onSearch(it) // Atualiza os resultados de pesquisa enquanto digita
            },
            leadingIcon = { KitchenIcon(icon = Icons.Default.Search, iconColor = theme.colors.verdeVibe02) },
            placeholder = { Text("Pesquisar") },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    onSearch(searchText)
                    keyboardController?.hide()
                }
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
    }
}


@Composable
fun SearchScreen() {
    var searchResult by remember { mutableStateOf("") }
    var searchItems by remember { mutableStateOf<List<String>>(emptyList()) }
    var sortedItems by remember { mutableStateOf<List<String>>(emptyList()) }

    // Adicionando alguns itens à lista de resultados com nomes parecidos
    searchItems = listOf(
        "Maçã",
        "Manga",
        "Melancia",
        "Morango",
        "Uva",
        "Pêra",
        "Abacaxi",
        "Alface",
        "Ameixa",
        "Cenoura"
    )

    Column {
        SearchTextField { searchText ->
            // Aqui você pode lidar com a ação de pesquisa
            searchResult = "Você pesquisou: $searchText"
            // Filtra e classifica os itens por prioridade
            sortedItems = filterAndSortItems(searchItems, searchText)
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(sortedItems) { item ->
                Text(
                    text = item,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}


// Função para filtrar e classificar os itens por prioridade
fun filterAndSortItems(items: List<String>, searchText: String): List<String> {
    val filteredItems = items.filter { item ->
        searchText.all { char ->
            item.contains(char, ignoreCase = true)
        }
    }

    val sortedItems = filteredItems.sortedWith(Comparator { a, b ->
        val scoreA = calculateScore(a, searchText)
        val scoreB = calculateScore(b, searchText)
        when {
            scoreA > scoreB -> -1
            scoreA < scoreB -> 1
            else -> a.compareTo(b, ignoreCase = true)
        }
    })

    return sortedItems
}

// Função para calcular a pontuação de prioridade de um item
fun calculateScore(item: String, searchText: String): Int {
    val searchChars = searchText.toCharArray()
    var score = 0

    for (char in searchChars) {
        if (item.startsWith(char, ignoreCase = true)) {
            score += 2
        } else if (item.contains(char, ignoreCase = true)) {
            score++
        }
    }

    return score
}


class BuscasRecentesManager(context: Context) {
    private val sharedPreferences = context.getSharedPreferences("BuscasRecentes", Context.MODE_PRIVATE)
    private val KEY_RECENT_CONTACTS = "recent_contacts"

    fun salvarBuscaRecente(contactId: String) {
        val contactsList = getBuscasRecentes()
        contactsList.remove(contactId) // Remover para evitar duplicatas
        contactsList.add(contactId) // Adicionar ao final da lista

        // Limitar o tamanho da lista, se necessário
        if (contactsList.size > MAX_RECENT_ITEMS) {
            contactsList.removeAt(0) // Remover o item mais antigo
        }

        sharedPreferences.edit().putString(KEY_RECENT_CONTACTS, contactsList.joinToString(",")).apply()
    }

    fun removerBuscaRecente(contactId: String) {
        val contactsList = getBuscasRecentes()
        contactsList.remove(contactId)

        sharedPreferences.edit().putString(KEY_RECENT_CONTACTS, contactsList.joinToString(",")).apply()
    }

    fun getBuscasRecentes(): MutableList<String> {
        val contactsString = sharedPreferences.getString(KEY_RECENT_CONTACTS, "") ?: ""
        return contactsString.split(",").toMutableList()
    }

    companion object {
        private const val MAX_RECENT_ITEMS = 5 // Defina o número máximo de itens recentes desejado
    }
}