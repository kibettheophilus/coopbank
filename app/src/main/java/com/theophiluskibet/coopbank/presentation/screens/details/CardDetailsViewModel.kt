package com.theophiluskibet.coopbank.presentation.screens.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.theophiluskibet.coopbank.domain.models.Card
import com.theophiluskibet.coopbank.domain.models.Transaction
import com.theophiluskibet.coopbank.domain.repositories.BankRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class CardDetailsUiState(
    val isLoading: Boolean = false,
    val error: String = "",
    val data: Card? = null,
    val transactions: List<Transaction> = emptyList(),
    val userName: String = ""
)

class CardDetailsViewModel(private val bankRepository: BankRepository) : ViewModel() {
    private val _cardDetailsState = MutableStateFlow(CardDetailsUiState())
    val cardDetailsState: StateFlow<CardDetailsUiState> = _cardDetailsState.asStateFlow()

    fun getCard(id: String) {
        viewModelScope.launch {
            _cardDetailsState.value = _cardDetailsState.value.copy(isLoading = true)
            bankRepository.getCard(id = id).collect { result ->
                result.onSuccess {
                    _cardDetailsState.value =
                        _cardDetailsState.value.copy(data = it, isLoading = false)
                }.onFailure {
                    _cardDetailsState.value = _cardDetailsState.value.copy(
                        error = it.message ?: "An error occurred",
                        isLoading = false
                    )
                }
            }
        }
    }
}