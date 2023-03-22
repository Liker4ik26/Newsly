package com.network.newsly.screens.search_news.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.network.newsly.core_ui.ImageWithPlaceHolder
import com.network.newsly.screens.search_news.presentation.models.SearchNewsItemUi

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsCard(
    news: SearchNewsItemUi,
    modifier: Modifier = Modifier,
    openDetails: (SearchNewsItemUi) -> Unit
) {
    Card(
        onClick = { openDetails(news) },
        modifier = modifier
    ) {
        if (news.imageUrl != null) {
            ImageWithPlaceHolder(
                imageUrl = news.imageUrl,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .clip(RoundedCornerShape(12.dp))
            )
        }
        Text(
            text = news.title,
            modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.headlineMedium
        )
    }
}