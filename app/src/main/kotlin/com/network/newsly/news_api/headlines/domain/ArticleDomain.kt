package com.network.newsly.news_api.headlines.domain

import java.util.Date


class ArticleDomain(
    val author: String?,
    val title: String,
    val description: String?,
    val source: String,
    val url: String,
    val imageUrl: String?,
    val date: Date,
    val content: String?
)