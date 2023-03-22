package com.network.newsly.screens.search_news.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.collectAsLazyPagingItems
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.network.newsly.screens.search_news.presentation.components.NewsListPagingHolder
import com.network.newsly.screens.search_news.presentation.components.SearchField
import com.network.newsly.screens.search_news.presentation.models.SearchNewsItemNavArg
import com.network.newsly.screens.search_news.presentation.models.asNavArg
import com.ramcosta.composedestinations.annotation.Destination
import kotlinx.coroutines.launch

@Destination
@Composable
fun SearchNewsScreen(navigator: SearchNewsNavigation) {
    SearchNewsScreen(openDetailsNews = navigator::openNewsDetails)
}

@Composable
private fun SearchNewsScreen(
    openDetailsNews: (SearchNewsItemNavArg) -> Unit,
    viewModel: SearchNewsViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val newsItems = state.searchResult.collectAsLazyPagingItems()
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    val focusManager = LocalFocusManager.current

    LaunchedEffect(listState.isScrollInProgress) {
        focusManager.clearFocus()
    }

    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is SearchNewsUiEffect.NavigateToDetailsNews -> openDetailsNews(effect.news)
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        SwipeRefresh(
            state = rememberSwipeRefreshState(isRefreshing = state.isRefreshing),
            onRefresh = { viewModel.sendEvent(SearchNewsUiEvent.OnRetry) },
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxSize()
        ) {
            NewsListPagingHolder(
                newsItem = newsItems,
                listState = listState,
                modifier = Modifier
                    .padding(horizontal = 32.dp)
                    .fillMaxSize(),
                openDetailsNews = {
                    viewModel.sendEvent(SearchNewsUiEvent.OnNews(it.asNavArg()))
                },
                onRetry = {
                    newsItems.retry()
                }
            )
        }
        SearchField(
            text = state.searchQuery,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 16.dp)
                .padding(horizontal = 32.dp)
                .fillMaxWidth(),
            onType = {
                viewModel.sendEvent(SearchNewsUiEvent.OnSearchType(it))
                coroutineScope.launch {
                    listState.scrollToItem(0, 0)
                }
            }
        )
    }
}