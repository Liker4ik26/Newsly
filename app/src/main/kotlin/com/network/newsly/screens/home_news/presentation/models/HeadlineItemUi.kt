package com.network.newsly.screens.home_news.presentation.models


class HeadlineItemUi(
    val author: String?,
    val title: String,
    val description: String?,
    val source: String,
    val url: String,
    val imageUrl: String?,
    val date: String,
    val content: String?
)

fun HeadlineItemUi.asNavArg(): NewsDetailsNavArg {
    return NewsDetailsNavArg(author, title, description, source, url, imageUrl, date, content)
}
