package com.theophiluskibet.coopbank.presentation.screens.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.theophiluskibet.coopbank.domain.models.User
import com.theophiluskibet.coopbank.domain.repositories.BankRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class ProfileUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val profile: User? = null
)

class ProfileViewModel(private val bankRepository: BankRepository) : ViewModel() {
    private val _profileUiState = MutableStateFlow(ProfileUiState())
    val profileUiState: StateFlow<ProfileUiState> = _profileUiState.asStateFlow()

    init {
        getProfile()
    }

    fun getProfile() {
        viewModelScope.launch {
            _profileUiState.value = ProfileUiState(isLoading = true)
            bankRepository.getUser().collect { result ->
                result.onSuccess {
                    _profileUiState.value = ProfileUiState(profile = it, isLoading = false)
                }.onFailure {
                    _profileUiState.value = ProfileUiState(error = it.message, isLoading = false)
                }
            }
        }
    }
}