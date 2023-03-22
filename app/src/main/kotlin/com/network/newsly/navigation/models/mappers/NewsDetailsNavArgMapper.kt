package com.network.newsly.navigation.models.mappers

import com.network.newsly.screens.home_news.presentation.models.NewsDetailsNavArg
import com.network.newsly.screens.news_details.presentation.NewsDetails

fun NewsDetailsNavArg.asArticle(): NewsDetails {
    return NewsDetails(
        title = title,
        source = source,
        url = url,
        imageUrl = imageUrl,
        author = author,
        date = date,
        content = content,
        description = description
    )
}