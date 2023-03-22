package com.network.newsly.screens.profile.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.network.newsly.news_api.authentication.data.repository.AuthenticationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

@HiltViewModel
class ProfileViewModel @Inject constructor(private val authenticationRepository: AuthenticationRepository) :
    ViewModel() {

    private val _effect = MutableSharedFlow<ProfileUiEffect>()
    val effect = _effect.asSharedFlow()

    fun sendEvent(event: ProfileUiEvent) {
        when (event) {
            is ProfileUiEvent.OnSignOut -> {
                viewModelScope.launch {
                    authenticationRepository.singOut()
                    _effect.emit(ProfileUiEffect.SignOut)
                }
            }
            is ProfileUiEvent.OnProfileEdit -> {
                viewModelScope.launch {
                    _effect.emit(ProfileUiEffect.NavigateToProfileEdit)
                }
            }
        }
    }
}