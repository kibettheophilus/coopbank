package com.theophiluskibet.coopbank.presentation.screens.allcards

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.theophiluskibet.coopbank.domain.models.Card
import com.theophiluskibet.coopbank.domain.repositories.BankRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class AllCardsUiSate(
    val cards: List<Card> = emptyList(),
    val isLoading: Boolean = false,
    val error: String = ""
)

class AllCardsViewModel(private val bankRepository: BankRepository) : ViewModel() {

    private val _allCardsState = MutableStateFlow(AllCardsUiSate())
    val allCardsState: StateFlow<AllCardsUiSate> = _allCardsState.asStateFlow()

    init {
        getAllCards()
    }

    fun getAllCards() {
        viewModelScope.launch {
            _allCardsState.value = AllCardsUiSate(isLoading = true)
            bankRepository.getCards().collect { result ->
                result.onSuccess {
                    _allCardsState.value = AllCardsUiSate(cards = it, isLoading = false)
                }.onFailure {
                    _allCardsState.value =
                        AllCardsUiSate(error = it.message.toString(), isLoading = false)
                }
            }
        }
    }
}