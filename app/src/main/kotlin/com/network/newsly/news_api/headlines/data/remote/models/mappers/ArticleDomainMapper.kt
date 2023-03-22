package com.network.newsly.news_api.headlines.data.remote.models.mappers

import com.network.newsly.news_api.headlines.data.remote.models.ArticleResponse
import com.network.newsly.news_api.headlines.domain.ArticleDomain

fun ArticleResponse.toArticle(): ArticleDomain {
    return ArticleDomain(
        author = author,
        title = title,
        description = description,
        source = source.name,
        url = url,
        imageUrl = imageUrl,
        date = date,
        content = content
    )
}