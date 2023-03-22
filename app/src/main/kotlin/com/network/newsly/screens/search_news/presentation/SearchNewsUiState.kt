package com.network.newsly.screens.search_news.presentation

import androidx.paging.PagingData
import com.network.newsly.screens.search_news.presentation.models.SearchNewsItemUi
import com.network.newsly.screens.search_news.presentation.models.SearchNewsItemNavArg
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import javax.annotation.concurrent.Immutable

@Immutable
data class SearchNewsUiState(
    val isLoading: Boolean = true,
    val isRefreshing: Boolean = false,
    val searchQuery: String = "",
    val searchResult: Flow<PagingData<SearchNewsItemUi>> = emptyFlow()
) {
    companion object {
        val Empty = SearchNewsUiState()
    }
}

sealed class SearchNewsUiEvent {
    object OnRetry : SearchNewsUiEvent()
    class OnSearchType(val query: String) : SearchNewsUiEvent()
    class OnNews(val news: SearchNewsItemNavArg) : SearchNewsUiEvent()
}

sealed class SearchNewsUiEffect {
    class NavigateToDetailsNews(val news: SearchNewsItemNavArg) : SearchNewsUiEffect()
}