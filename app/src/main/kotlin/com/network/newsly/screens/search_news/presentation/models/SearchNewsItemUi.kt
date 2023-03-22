package com.network.newsly.screens.search_news.presentation.models

class SearchNewsItemUi(
    val author: String?,
    val title: String,
    val description: String?,
    val source: String,
    val url: String,
    val imageUrl: String?,
    val date: String,
    val content: String?
)
fun SearchNewsItemUi.asNavArg(): SearchNewsItemNavArg {
    return SearchNewsItemNavArg(author, title, description, source, url, imageUrl, date, content)
}