package com.network.newsly.news_api.headlines.data.remote.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.util.Date

@JsonClass(generateAdapter = true)
class NewsPageResponse(
    @Json(name = "articles")
    val articles: List<ArticleResponse>
)

@JsonClass(generateAdapter = true)
class ArticleResponse(
    @Json(name = "author")
    val author: String?,
    @Json(name = "title")
    val title: String,
    @Json(name = "description")
    val description: String?,
    @Json(name = "source")
    val source: ArticleSourceResponse,
    @Json(name = "url")
    val url: String,
    @Json(name = "urlToImage")
    val imageUrl: String?,
    @Json(name = "publishedAt")
    val date: Date,
    @Json(name = "content")
    val content: String?
)

@JsonClass(generateAdapter = true)
class ArticleSourceResponse(
    @Json(name = "name")
    val name: String
)