package com.network.newsly.screens.news_details.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.network.newsly.screens.destinations.NewsDetailsScreenDestination
import com.network.newsly.screens.news_details.presentation.components.UiText
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class NewsDetailsViewModel @AssistedInject constructor(
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    @AssistedFactory
    interface Factory {
        fun create(savedStateHandle: SavedStateHandle): NewsDetailsViewModel
    }

    private val _state = MutableStateFlow(NewsDetailsUiState.Empty)
    val state = _state.asStateFlow()

    private val _effect = MutableSharedFlow<NewsDetailsUiEffect>()
    val effect = _effect.asSharedFlow()

    init {
        obtainDetails()
    }

    fun sendEvent(event: NewsDetailsUiEvent) {
        when (event) {
            NewsDetailsUiEvent.OnBack -> {
                viewModelScope.launch { _effect.emit(NewsDetailsUiEffect.NavigateBack) }
            }
            is NewsDetailsUiEvent.OnOpenLink -> {
                viewModelScope.launch { _effect.emit(NewsDetailsUiEffect.OpenLinkInBrowser(event.url)) }
            }
        }
    }

    private fun obtainDetails() {
        val newsDetails: NewsDetails =
            NewsDetailsScreenDestination.argsFrom(savedStateHandle).newsDetails
        with(newsDetails) {
            _state.update {
                it.copy(
                    title = title,
                    source = source,
                    url = url,
                    imageUrl = imageUrl,
                    date = date,
                    description = if (description != null) {
                        UiText.DynamicString(description)
                    } else null,
                    author = if (author != null) {
                        UiText.DynamicString(author)
                    } else null,
                    content = if (content != null) {
                        UiText.DynamicString(content)
                    } else null
                )
            }
        }
    }
}
