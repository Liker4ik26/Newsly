package com.network.newsly.navigation.models.mappers

import com.network.newsly.screens.news_details.presentation.NewsDetails
import com.network.newsly.screens.search_news.presentation.models.SearchNewsItemNavArg

fun SearchNewsItemNavArg.asArticle(): NewsDetails {
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