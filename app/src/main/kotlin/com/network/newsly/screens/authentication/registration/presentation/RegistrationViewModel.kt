package com.network.newsly.screens.authentication.registration.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.ktx.userProfileChangeRequest
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
class RegistrationViewModel @Inject constructor(
    private val authenticationRepository: AuthenticationRepository
) : ViewModel() {

    private val _state = MutableStateFlow(RegistrationUiState.Empty)
    val state = _state.asStateFlow()

    private val _effect = MutableSharedFlow<RegistrationUiEffect>()
    val effect = _effect.asSharedFlow()

    fun sendEvent(event: RegistrationUiEvent) {
        when (event) {
            is RegistrationUiEvent.OnFirstNameInput -> {
                viewModelScope.launch { _state.update { it.copy(firstName = event.firstName) } }
            }
            is RegistrationUiEvent.OnLastNameInput -> {
                viewModelScope.launch { _state.update { it.copy(lastName = event.lastName) } }
            }
            is RegistrationUiEvent.OnEmailInput -> {
                viewModelScope.launch {
                    _state.update { it.copy(email = event.email) }
                }
            }
            is RegistrationUiEvent.OnPasswordInput -> {
                viewModelScope.launch {
                    _state.update { it.copy(password = event.password) }
                }
            }
            is RegistrationUiEvent.OnNavigateBack -> {
                viewModelScope.launch {
                    _effect.emit(RegistrationUiEffect.NavigateBack)
                }
            }
            is RegistrationUiEvent.OnNavigateToHomeScreen -> {
                viewModelScope.launch { checkRegistrationStatus() }
            }
        }
    }

    private suspend fun checkRegistrationStatus() {
        if (state.value.email.trim().isEmpty() || state.value.password.trim().isEmpty()
        ) {
            _state.update { it.copy(isError = true) }
        } else {
            authenticationRepository.signUp(
                email = _state.value.email,
                password = _state.value.password,
                onSuccessListener = { authResult ->
                    authResult.user?.updateProfile(userProfileChangeRequest {
                        displayName = "${_state.value.firstName} ${_state.value.lastName}"
                    })
                    viewModelScope.launch {
                        _state.update { it.copy(isError = false) }
                        _effect.emit(RegistrationUiEffect.NavigateBack)
                    }
                },
                onFailureListener = {
                    viewModelScope.launch { _state.update { it.copy(isError = true) } }
                }
            )
        }
    }
}