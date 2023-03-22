package com.network.newsly.screens.home_news.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.network.newsly.news_api.headlines.data.NewsRepository
import com.network.newsly.news_api.headlines.domain.ArticleDomain
import com.network.newsly.screens.home_news.presentation.models.mappers.asHeadlineItemUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.update

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val newsRepository: NewsRepository
) : ViewModel() {

    private val _state = MutableStateFlow(HomeUiState.Empty)
    val state = _state.asStateFlow()

    private val _effect = MutableSharedFlow<HomeUiEffect>()
    val effect = _effect.asSharedFlow()

    init {
        loadNews()
    }

    fun sendEvent(event: HomeUiEvent) {
        when (event) {
            is HomeUiEvent.OnHeadline -> {
                viewModelScope.launch { _effect.emit(HomeUiEffect.NavigateToArticle(event.article)) }
            }
            HomeUiEvent.OnRetry -> {
                _state.update { it.copy(isRefreshing = true) }
                loadNews()
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    private fun loadNews() {
        val news =
            newsRepository.getHeadlinesPagedFlow().cachedIn(viewModelScope).mapLatest { data ->
                data.map(ArticleDomain::asHeadlineItemUi)
            }
        _state.update { it.copy(isLoading = false, isRefreshing = false, news = news) }
    }
}