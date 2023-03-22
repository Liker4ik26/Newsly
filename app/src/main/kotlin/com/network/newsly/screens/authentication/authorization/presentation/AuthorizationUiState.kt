package com.network.newsly.screens.authentication.authorization.presentation

import javax.annotation.concurrent.Immutable

@Immutable
data class AuthorizationUiState(
    val email: String = "",
    val password: String = "",
    val isError: Boolean = false
) {
    companion object {
        val Empty = AuthorizationUiState()
    }
}

sealed class AuthorizationUiEvent {
    object OnNavigateToRegistrationScreen : AuthorizationUiEvent()
    object OnNavigateToHome : AuthorizationUiEvent()
    class OnEmailInput(val email: String) : AuthorizationUiEvent()
    class OnPasswordInput(val password: String) : AuthorizationUiEvent()
}

sealed class AuthorizationEffect {
    object NavigateToHomeScreen : AuthorizationEffect()
    object NavigateToRegistrationScreen : AuthorizationEffect()
}