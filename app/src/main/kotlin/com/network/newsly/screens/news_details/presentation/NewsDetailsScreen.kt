package com.network.newsly.screens.news_details.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.network.newsly.R
import com.network.newsly.screens.news_details.presentation.components.NewsDetailsContent
import com.ramcosta.composedestinations.annotation.Destination

@Destination(navArgsDelegate = NewsDetailsNavArgs::class)
@Composable
fun NewsDetailsScreen(navigator: NewsDetailsScreenNavigation) {
    NewsDetailsScreen(
        onBack = navigator::navigateUp
    )
}

@Composable
private fun NewsDetailsScreen(
    viewModel: NewsDetailsViewModel = hiltViewModel(),
    onBack: () -> Unit
) {

    val uriHandler = LocalUriHandler.current
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.effect.collect() { effect ->
            when (effect) {
                NewsDetailsUiEffect.NavigateBack -> onBack()
                is NewsDetailsUiEffect.OpenLinkInBrowser -> uriHandler.openUri(effect.url)
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {
            NewsDetailsContent(
                imageUrl = state.imageUrl,
                title = state.title,
                description = state.description?.asString(),
                content = state.content?.asString(),
                date = state.date,
                author = state.author?.asString(),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(15.dp)
            ) {
                viewModel.sendEvent(NewsDetailsUiEvent.OnOpenLink(state.url))
            }
        }
        IconButton(
            onClick = { viewModel.sendEvent(NewsDetailsUiEvent.OnBack) },
            colors = IconButtonDefaults.iconButtonColors(containerColor = MaterialTheme.colorScheme.surface),
            modifier = Modifier.padding(15.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.chevron_left),
                contentDescription = stringResource(id = R.string.back_nav_icon)
            )
        }
    }
}
