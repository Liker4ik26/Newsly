package com.network.newsly.screens.authentication.registration.presentation

import javax.annotation.concurrent.Immutable

@Immutable
data class RegistrationUiState(
    val firstName: String = "",
    val lastName: String = "",
    val email: String = "",
    val password: String = "",
    val isError: Boolean = false
) {
    companion object {
        val Empty = RegistrationUiState()
    }
}

sealed class RegistrationUiEvent {
    object OnNavigateBack : RegistrationUiEvent()
    object OnNavigateToHomeScreen : RegistrationUiEvent()
    class OnFirstNameInput(val firstName: String) : RegistrationUiEvent()
    class OnLastNameInput(val lastName: String) : RegistrationUiEvent()
    class OnEmailInput(val email: String) : RegistrationUiEvent()
    class OnPasswordInput(val password: String) : RegistrationUiEvent()
}

sealed class RegistrationUiEffect {
    object NavigateBack : RegistrationUiEffect()
    object NavigateToHomeScreen : RegistrationUiEffect()
}