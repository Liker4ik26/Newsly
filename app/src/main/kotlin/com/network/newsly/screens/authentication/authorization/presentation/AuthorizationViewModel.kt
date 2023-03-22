package com.network.newsly.screens.authentication.authorization.presentation

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
class AuthorizationViewModel @Inject constructor(
    private val authenticationRepository: AuthenticationRepository
) : ViewModel() {

    private val _state = MutableStateFlow(AuthorizationUiState.Empty)
    val state = _state.asStateFlow()

    private val _effect = MutableSharedFlow<AuthorizationEffect>()
    val effect = _effect.asSharedFlow()

    fun sendEvent(event: AuthorizationUiEvent) {
        when (event) {
            is AuthorizationUiEvent.OnNavigateToHome -> {
                viewModelScope.launch { checkAuthorizedStatus() }
            }
            is AuthorizationUiEvent.OnEmailInput -> {
                viewModelScope.launch { _state.update { it.copy(email = event.email) } }
            }
            is AuthorizationUiEvent.OnPasswordInput -> {
                viewModelScope.launch { _state.update { it.copy(password = event.password) } }
            }
            is AuthorizationUiEvent.OnNavigateToRegistrationScreen -> {
                viewModelScope.launch { _effect.emit(AuthorizationEffect.NavigateToRegistrationScreen) }
            }
        }
    }

    private suspend fun checkAuthorizedStatus() {
        if (_state.value.email.trim().isEmpty() || _state.value.password.trim().isEmpty()) {
            _state.update { it.copy(isError = true) }
        } else {
            authenticationRepository.signIn(
                email = _state.value.email,
                password = _state.value.password,
                onSuccessListener = {
                    viewModelScope.launch {
                        _state.update { it.copy(isError = false) }
                        _effect.emit(AuthorizationEffect.NavigateToHomeScreen)
                    }
                },
                onFailureListener = {
                    viewModelScope.launch { _state.update { it.copy(isError = true) } }
                }
            )
        }
    }
}