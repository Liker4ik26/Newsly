package com.network.newsly.screens.home_news.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.collectAsLazyPagingItems
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.network.newsly.core_ui.TopHeadlineBlock
import com.network.newsly.screens.home_news.presentation.components.HomeListPagingHolder
import com.network.newsly.screens.home_news.presentation.models.NewsDetailsNavArg
import com.network.newsly.screens.home_news.presentation.models.asNavArg
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun HomeNewsScreen(navigation: HomeScreenNavigation) {
    HomeNewsScreen(openArticle = navigation::openNewsDetails)
}

@Composable
private fun HomeNewsScreen(
    openArticle: (NewsDetailsNavArg) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {

    val uiState by viewModel.state.collectAsStateWithLifecycle()
    val newsItems = uiState.news.collectAsLazyPagingItems()

    LaunchedEffect(Unit) {
        viewModel.effect.collect() { effect -> // TODO
            when (effect) {
                is HomeUiEffect.NavigateToArticle -> openArticle(effect.article)
            }
        }
    }

    SwipeRefresh(
        state = rememberSwipeRefreshState(isRefreshing = uiState.isRefreshing),
        onRefresh = { viewModel.sendEvent(HomeUiEvent.OnRetry) },
        modifier = Modifier.fillMaxSize()
    ) {
        Box(modifier = Modifier.background(MaterialTheme.colorScheme.background)) {
            Spacer(modifier = Modifier.height(10.dp))
            TopHeadlineBlock(text = "Newsly") //TODO
            Spacer(modifier = Modifier.height(10.dp))
            HomeListPagingHolder(
                newsItems = newsItems,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                onRetry = {
                    newsItems.retry()
                },
                openArticle = {
                    viewModel.sendEvent(HomeUiEvent.OnHeadline(it.asNavArg()))
                }
            )
        }
    }
}