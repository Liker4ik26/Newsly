package com.network.newsly.news_api.headlines.data.remote

import com.network.newsly.core.entity.Either
import com.network.newsly.core.entity.ErrorReason
import com.network.newsly.news_api.headlines.data.remote.models.NewsPageResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiDataSource {

    companion object {
        const val DEFAULT_PAGE_SIZE = 20
        const val START_PAGE = 1
    }

    @GET("top-headlines")
    suspend fun getHeadlines(
        @Query("country") country: String,
        @Query("page") page: Int = START_PAGE,
        @Query("page_size") pageSize: Int = DEFAULT_PAGE_SIZE
    ): Either<ErrorReason, NewsPageResponse>

    @GET("everything")
    suspend fun getEverything(
        @Query("q") query: String,
        @Query("page") page: Int = START_PAGE,
        @Query("pageSize") pageSize: Int = DEFAULT_PAGE_SIZE,
        @Query("sortBy") sortBY: String = "popularity"
    ): Either<ErrorReason, NewsPageResponse>
}