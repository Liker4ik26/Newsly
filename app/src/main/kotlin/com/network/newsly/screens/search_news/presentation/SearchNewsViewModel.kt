package com.network.newsly.screens.search_news.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.network.newsly.news_api.headlines.data.NewsRepository
import com.network.newsly.news_api.headlines.domain.ArticleDomain
import com.network.newsly.screens.search_news.presentation.models.mappers.asSearchItemUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.update

@HiltViewModel
class SearchNewsViewModel @Inject constructor(
    private val newsRepository: NewsRepository
) : ViewModel() {

    private val _state = MutableStateFlow(SearchNewsUiState.Empty)
    val state = _state.asStateFlow()

    private val _effect = MutableSharedFlow<SearchNewsUiEffect>()
    val effect = _effect.asSharedFlow()

    fun sendEvent(event: SearchNewsUiEvent) {
        when (event) {
            is SearchNewsUiEvent.OnNews -> {
                viewModelScope.launch {
                    _effect.emit(SearchNewsUiEffect.NavigateToDetailsNews(event.news))
                }
            }
            is SearchNewsUiEvent.OnSearchType -> {
                _state.update { it.copy(isLoading = true, searchQuery = event.query) }
                loadSearchResult(_state.value.searchQuery)
            }
            SearchNewsUiEvent.OnRetry -> {
                _state.update { it.copy(isRefreshing = true) }
                loadSearchResult(_state.value.searchQuery)
            }
        }
    }

    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    private fun loadSearchResult(query: String) {
        val result = newsRepository.getEverythingPagedFlow(query)
            .debounce(300)
            .distinctUntilChanged()
            .cachedIn(viewModelScope)
            .mapLatest { data ->
                data.map(ArticleDomain::asSearchItemUi)
            }
        _state.update { it.copy(isLoading = false, isRefreshing = false, searchResult = result) }
    }
}