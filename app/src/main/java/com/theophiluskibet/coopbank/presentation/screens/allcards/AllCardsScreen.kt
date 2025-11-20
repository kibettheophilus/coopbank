package com.theophiluskibet.coopbank.presentation.screens.allcards

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.theophiluskibet.coopbank.domain.models.Card
import com.theophiluskibet.coopbank.presentation.components.CardComponent
import com.theophiluskibet.coopbank.presentation.components.LoadingComponent
import org.koin.androidx.compose.koinViewModel

@Composable
fun AllCardsScreen(
    modifier: Modifier = Modifier,
    allCardsViewModel: AllCardsViewModel = koinViewModel()
) {
    val uiState by allCardsViewModel.allCardsState.collectAsStateWithLifecycle()

    AllCardsScreenContent(uiState = uiState)
}

@Composable
fun AllCardsScreenContent(uiState: AllCardsUiSate) {
    when {
        uiState.isLoading -> {
            LoadingComponent()
        }

        uiState.error.isNotEmpty() -> {
            // show error
        }

        uiState.cards.isNotEmpty() -> {
            LazyContent(cards = uiState.cards)
        }
    }
}

@Composable
fun LazyContent(modifier: Modifier = Modifier, cards: List<Card>) {
    LazyColumn() {
        items(items = cards) { card ->
            CardComponent(card = card)
        }
    }
}