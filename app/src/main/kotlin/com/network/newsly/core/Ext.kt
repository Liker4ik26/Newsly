package com.network.newsly.core

import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.grid.LazyGridItemScope
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.runtime.Composable
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState

fun LazyListScope.pagingLoadStateItem(
    loadState: CombinedLoadStates,
    loadingContent: (@Composable LazyItemScope.() -> Unit),
    errorContent: (@Composable LazyItemScope.(String?) -> Unit),
) {
    when (loadState.append) {
        LoadState.Loading -> {
            item(key = "loadingFooter", content = loadingContent)
        }
        is LoadState.Error -> {
            val message = (loadState.append as LoadState.Error).error.localizedMessage
            item(key = "errorFooter", content = { errorContent(message) })
        }
        is LoadState.NotLoading -> {}
    }
    when (loadState.prepend) {
        LoadState.Loading -> {
            item(key = "loadingHeader", content = loadingContent)
        }
        is LoadState.Error -> {
            val message = (loadState.append as LoadState.Error).error.localizedMessage
            item(key = "errorHeader", content = { errorContent(message) })
        }
        is LoadState.NotLoading -> {}
    }
}

fun LazyGridScope.pagingLoadStateItem(
    loadState: CombinedLoadStates,
    loadingContent: (@Composable LazyGridItemScope.() -> Unit),
    errorContent: (@Composable LazyGridItemScope.(String?) -> Unit),
) {
    when (loadState.append) {
        LoadState.Loading -> {
            item(key = "loadingFooter", content = loadingContent)
        }
        is LoadState.Error -> {
            val message = (loadState.append as LoadState.Error).error.localizedMessage
            item(key = "errorFooter", content = { errorContent(message) })
        }
        is LoadState.NotLoading -> {}
    }
    when (loadState.prepend) {
        LoadState.Loading -> {
            item(key = "loadingHeader", content = loadingContent)
        }
        is LoadState.Error -> {
            val message = (loadState.append as LoadState.Error).error.localizedMessage
            item(key = "errorHeader", content = { errorContent(message) })
        }
        is LoadState.NotLoading -> {}
    }
}