package com.network.newsly.screens.search_news.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
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
import com.network.newsly.core_ui.ErrorMessageWithButton
import com.network.newsly.screens.search_news.presentation.models.SearchNewsItemUi

@Composable
fun NewsList(
    news: LazyPagingItems<SearchNewsItemUi>,
    listState: LazyListState,
    modifier: Modifier = Modifier,
    openDetailsNews: (SearchNewsItemUi) -> Unit,
    onRetry: () -> Unit
) {
    LazyColumn(
        modifier = modifier,
        state = listState,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item {
            Spacer(
                modifier = Modifier
                    .statusBarsPadding()
                    .padding(16.dp)
            )
        }
        itemsIndexed(items = news) { _, item ->
            item?.let {
                Spacer(modifier = Modifier.height(8.dp))
                NewsCard(news = item, modifier = modifier.fillMaxWidth()) {
                    openDetailsNews(it)
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
        pagingLoadStateItem(
            loadState = news.loadState,
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