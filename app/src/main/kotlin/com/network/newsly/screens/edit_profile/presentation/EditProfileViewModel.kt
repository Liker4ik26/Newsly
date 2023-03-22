package com.network.newsly.screens.edit_profile.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.network.newsly.news_api.authentication.data.repository.AuthenticationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

@HiltViewModel
class EditProfileViewModel @Inject constructor(
    private val authenticationRepository: AuthenticationRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(EditProfileUiState.Empty)
    val state = _state.asStateFlow()

    private val _effect = MutableSharedFlow<EditProfileUiEffect>()
    val effect = _effect.asSharedFlow()

    fun sendEvent(event: EditProfileUiEvent) {
        when (event) {
            is EditProfileUiEvent.OnFirstNameInput -> {
                _state.update { it.copy(firstName = event.firstName) }
            }
            is EditProfileUiEvent.OnLastNameInput -> {
                _state.update { it.copy(lastName = event.lastName) }
            }
            is EditProfileUiEvent.OnSave -> {
                viewModelScope.launch { checkNullField() }
            }
            is EditProfileUiEvent.OnNavigateBack -> {
                viewModelScope.launch {
                    _effect.emit(EditProfileUiEffect.NavigateBack)
                }
            }
        }
    }

    private suspend fun checkNullField() {
        if (state.value.firstName.trim().isEmpty() || state.value.lastName.trim().isEmpty()) {
            _state.update { it.copy(isError = true) }
        } else {
            viewModelScope.launch {
                authenticationRepository.updateUserData(
                    firstName = _state.value.firstName,
                    lastName = _state.value.lastName,
                    onSuccessListener = {
                        viewModelScope.launch { _state.update { it.copy(isError = false) } }
                        viewModelScope.launch { _effect.emit(EditProfileUiEffect.NavigateToProfile) }
                    },
                    onFailureListener = {
                        viewModelScope.launch { _state.update { it.copy(isError = true) } }
                    }
                )
            }
        }
    }
}