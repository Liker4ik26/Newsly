package com.network.newsly.screens.home_news.presentation

import androidx.paging.PagingData
import com.network.newsly.screens.home_news.presentation.models.HeadlineItemUi
import com.network.newsly.screens.home_news.presentation.models.NewsDetailsNavArg
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class HomeUiState(
    val isLoading: Boolean = true,
    val isRefreshing: Boolean = false,
    val errorMessage: String? = null,
    val news: Flow<PagingData<HeadlineItemUi>> = emptyFlow()
) {
    companion object {
        val Empty = HomeUiState()
    }
}

sealed class HomeUiEvent {
    object OnRetry : HomeUiEvent()
    class OnHeadline(val article: NewsDetailsNavArg) : HomeUiEvent()
}

sealed class HomeUiEffect {
    class NavigateToArticle(val article: NewsDetailsNavArg) : HomeUiEffect()
}