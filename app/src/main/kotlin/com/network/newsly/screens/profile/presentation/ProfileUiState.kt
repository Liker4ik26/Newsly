package com.network.newsly.screens.profile.presentation

sealed class ProfileUiEvent {
    object OnSignOut : ProfileUiEvent()
    object OnProfileEdit : ProfileUiEvent()
}

sealed class ProfileUiEffect {
    object SignOut : ProfileUiEffect()
    object NavigateToProfileEdit : ProfileUiEffect()
}