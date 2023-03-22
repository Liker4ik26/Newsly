package com.network.newsly.core_ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import coil.request.ImageRequest
import com.network.newsly.R

@Composable
fun ImageWithPlaceHolder(
    imageUrl: String,
    modifier: Modifier = Modifier
) {
    SubcomposeAsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .crossfade(false)
            .build(),
        contentDescription = stringResource(id = R.string.article_image),
        modifier = modifier,
        contentScale = ContentScale.Crop
    ) {
        when (painter.state) {
            AsyncImagePainter.State.Empty -> {
                FadePlaceHolder(modifier.fillMaxSize())
            }
            is AsyncImagePainter.State.Loading -> {
                FadePlaceHolder(modifier = modifier.fillMaxSize())
            }
            is AsyncImagePainter.State.Success -> SubcomposeAsyncImageContent()
            is AsyncImagePainter.State.Error -> {
                ErrorImage(modifier = Modifier.fillMaxSize())
            }
        }
    }
}