package com.network.newsly.screens.home_news.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.network.newsly.R
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemsIndexed
import com.network.newsly.core.pagingLoadStateItem
import com.network.newsly.core_ui.NewsCard
import com.network.newsly.core_ui.ErrorMessageWithButton
import com.network.newsly.screens.home_news.presentation.models.HeadlineItemUi

@Composable
fun HomeList(
    newsItems: LazyPagingItems<HeadlineItemUi>,
    modifier: Modifier = Modifier,
    openArticle: (HeadlineItemUi) -> Unit,
    onRetry: () -> Unit
) {
    LazyColumn(
        modifier = modifier,
        state = rememberLazyListState(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        item {
            Spacer(modifier = Modifier.statusBarsPadding())
        } //TODO

        pagingLoadStateItem(
            loadState = newsItems.loadState,
            loadingContent = {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp)
                ) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
            },
            errorContent = { message ->

                ErrorMessageWithButton(
                    message = message ?: stringResource(id = R.string.unknown_error),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp)
                ) {
                    onRetry()
                }
            }
        )

        itemsIndexed(items = newsItems) { _, item ->
            item?.let {

                NewsCard(
                    news = item,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    openArticle(it)
                }
            }
        }

        pagingLoadStateItem(
            loadState = newsItems.loadState,
            loadingContent = {

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp)
                ) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
            },
            errorContent = { message ->

                ErrorMessageWithButton(
                    message = message ?: stringResource(id = R.string.unknown_error),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp)
                ) {
                    onRetry()
                }
            }
        )
    }
}