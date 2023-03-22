package com.network.newsly.screens.home_news.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
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
import com.network.newsly.screens.home_news.presentation.models.HeadlineItemUi

@Composable
fun HomeListPagingHolder(
    newsItems: LazyPagingItems<HeadlineItemUi>,
    modifier: Modifier = Modifier,
    openArticle: (HeadlineItemUi) -> Unit,
    onRetry: () -> Unit
) {
    when (val refresh = newsItems.loadState.refresh) {
        LoadState.Loading -> {
            Box(modifier = modifier) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }
        is LoadState.NotLoading -> {
            if (newsItems.itemCount != 0) {
                HomeList(
                    newsItems = newsItems,
                    openArticle = { openArticle(it) },
                    onRetry = onRetry
                )
            }
        }
        is LoadState.Error -> {
            val apiMessage = (refresh.error as? NewsLoadException)?.reason?.message
            ErrorMessageWithIconAndButton(
                message = apiMessage ?: stringResource(id = R.string.unknown_error),
                modifier = modifier,
                iconModifier = Modifier.size(160.dp)
            ) {
                onRetry()
            }
        }
    }
}