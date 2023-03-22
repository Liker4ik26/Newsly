package com.network.newsly.screens.home_news.presentation.models.mappers

import android.text.format.DateUtils
import com.network.newsly.news_api.headlines.domain.ArticleDomain
import com.network.newsly.screens.home_news.presentation.models.HeadlineItemUi

fun ArticleDomain.asHeadlineItemUi(): HeadlineItemUi {

    val relativeDateString = DateUtils.getRelativeTimeSpanString(
        date.time,
        System.currentTimeMillis(),
        DateUtils.DAY_IN_MILLIS
    )
    return HeadlineItemUi(
        title = title,
        source = source,
        url = url,
        imageUrl = imageUrl,
        author = author,
        date = relativeDateString.toString(),
        content = content,
        description = description
    )
}