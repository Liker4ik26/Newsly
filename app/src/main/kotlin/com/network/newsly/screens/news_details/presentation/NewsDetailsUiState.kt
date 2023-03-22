package com.network.newsly.screens.news_details.presentation

import com.network.newsly.screens.news_details.presentation.components.UiText
import javax.annotation.concurrent.Immutable

@Immutable
data class NewsDetailsUiState(
    val author: UiText? = null,
    val title: String = "",
    val description: UiText? = null,
    val source: String = "",
    val url: String = "",
    val imageUrl: String? = null,
    val date: String = "",
    val content: UiText? = null
) {
    companion object {
        val Empty = NewsDetailsUiState()
    }
}

sealed class NewsDetailsUiEvent {
    object OnBack : NewsDetailsUiEvent()
    class OnOpenLink(val url: String) : NewsDetailsUiEvent()
}

sealed class NewsDetailsUiEffect {
    object NavigateBack : NewsDetailsUiEffect()
    class OpenLinkInBrowser(val url: String) : NewsDetailsUiEffect()
}