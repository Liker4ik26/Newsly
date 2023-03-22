package com.network.newsly.news_api.headlines.data

import android.content.Context
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.network.newsly.news_api.headlines.data.remote.NewsApiDataSource
import com.network.newsly.news_api.headlines.data.remote.NewsApiDataSource.Companion.DEFAULT_PAGE_SIZE
import com.network.newsly.news_api.headlines.data.remote.NewsPagingSource
import com.network.newsly.news_api.headlines.domain.ArticleDomain
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    @ApplicationContext context: Context,
    private val newsApiDataSource: NewsApiDataSource
) : NewsRepository {

    private val country = context.resources.configuration.locales[0].country

    override fun getHeadlinesPagedFlow(): Flow<PagingData<ArticleDomain>> {
        return Pager(
            config = PagingConfig(pageSize = DEFAULT_PAGE_SIZE, enablePlaceholders = true),
            pagingSourceFactory = {
                NewsPagingSource { page, pageSize ->
                    newsApiDataSource.getHeadlines(country, page, pageSize)
                }
            }
        ).flow
    }

    override fun getEverythingPagedFlow(searchQuery: String): Flow<PagingData<ArticleDomain>> {
        return Pager(
            config = PagingConfig(pageSize = DEFAULT_PAGE_SIZE, enablePlaceholders = true),
            pagingSourceFactory = {
                NewsPagingSource { page, pageSize ->
                    newsApiDataSource.getEverything(searchQuery, page, pageSize)
                }
            }
        ).flow
    }
}