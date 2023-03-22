package com.network.newsly.news_api.headlines.data

import androidx.paging.PagingData
import com.network.newsly.news_api.headlines.domain.ArticleDomain
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    fun getHeadlinesPagedFlow(): Flow<PagingData<ArticleDomain>>

    fun getEverythingPagedFlow(searchQuery: String): Flow<PagingData<ArticleDomain>>

}