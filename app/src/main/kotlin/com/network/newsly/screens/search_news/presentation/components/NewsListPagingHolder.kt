package com.network.newsly.screens.search_news.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.network.newsly.R
import com.network.newsly.core_ui.ErrorMessageWithIconAndButton
import com.network.newsly.news_api.headlines.data.remote.models.NewsLoadException
import com.network.newsly.screens.search_news.presentation.models.SearchNewsItemUi

@Composable
fun NewsListPagingHolder(
    newsItem: LazyPagingItems<SearchNewsItemUi>,
    listState: LazyListState,
    modifier: Modifier = Modifier,
    openDetailsNews: (SearchNewsItemUi) -> Unit,
    onRetry: () -> Unit
) {
    when (val refresh = newsItem.loadState.refresh) {
        is LoadState.NotLoading -> {
            if (newsItem.itemCount != 0) {
                NewsList(
                    news = newsItem,
                    listState = listState,
                    openDetailsNews = { openDetailsNews(it) },
                    onRetry = onRetry
                )
            } else {
                EmptySearch(modifier = Modifier.fillMaxSize())
            }
        }
        is LoadState.Error -> {
            val apiMessage = (refresh.error as? NewsLoadException)?.reason?.message
            ErrorMessageWithIconAndButton(
                message = apiMessage ?: stringResource(id = R.string.unknown_error),
                modifier = modifier,
                iconModifier = Modifier.size(100.dp)
            ) {
                onRetry()
            }
        }
        LoadState.Loading -> {
            Box(modifier = modifier) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }
    }
}