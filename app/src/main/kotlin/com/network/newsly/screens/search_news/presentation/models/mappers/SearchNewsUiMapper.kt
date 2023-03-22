package com.network.newsly.screens.search_news.presentation.models.mappers

import android.text.format.DateUtils
import com.network.newsly.news_api.headlines.domain.ArticleDomain
import com.network.newsly.screens.search_news.presentation.models.SearchNewsItemUi

fun ArticleDomain.asSearchItemUi(): SearchNewsItemUi {
    val relativeDateString = DateUtils.getRelativeTimeSpanString(
        date.time,
        System.currentTimeMillis(),
        DateUtils.DAY_IN_MILLIS
    )
    return SearchNewsItemUi(
        author = author,
        title = title,
        description = description,
        source = source,
        url = url,
        imageUrl = imageUrl,
        date = relativeDateString.toString(),
        content = content
    )
}