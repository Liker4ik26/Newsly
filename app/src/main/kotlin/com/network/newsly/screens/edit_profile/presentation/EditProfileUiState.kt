package com.network.newsly.screens.edit_profile.presentation

import com.google.errorprone.annotations.Immutable

@Immutable
data class EditProfileUiState(
    val firstName: String = "",
    val lastName: String = "",
    val isError: Boolean = false
) {
    companion object {
        val Empty = EditProfileUiState()
    }
}

sealed class EditProfileUiEvent {
    class OnFirstNameInput(val firstName: String) : EditProfileUiEvent()
    class OnLastNameInput(val lastName: String) : EditProfileUiEvent()
    object OnSave : EditProfileUiEvent()
    object OnNavigateBack : EditProfileUiEvent()
}

sealed class EditProfileUiEffect {
    object NavigateToProfile : EditProfileUiEffect()
    object NavigateBack : EditProfileUiEffect()
}