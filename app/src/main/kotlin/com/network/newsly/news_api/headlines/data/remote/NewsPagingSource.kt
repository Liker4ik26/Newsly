package com.network.newsly.news_api.headlines.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.network.newsly.core.entity.Either
import com.network.newsly.core.entity.ErrorReason
import com.network.newsly.core.entity.unpack
import com.network.newsly.news_api.headlines.data.remote.NewsApiDataSource.Companion.DEFAULT_PAGE_SIZE
import com.network.newsly.news_api.headlines.data.remote.NewsApiDataSource.Companion.START_PAGE
import com.network.newsly.news_api.headlines.data.remote.models.ArticleResponse
import com.network.newsly.news_api.headlines.data.remote.models.NewsLoadException
import com.network.newsly.news_api.headlines.data.remote.models.NewsPageResponse
import com.network.newsly.news_api.headlines.data.remote.models.mappers.toArticle
import com.network.newsly.news_api.headlines.domain.ArticleDomain
import javax.inject.Inject

class NewsPagingSource @Inject constructor(
    private val loadPage: suspend (page: Int, pageSize: Int) -> Either<ErrorReason, NewsPageResponse>
) : PagingSource<Int, ArticleDomain>() {

    override fun getRefreshKey(state: PagingState<Int, ArticleDomain>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ArticleDomain> {
        val page = params.key ?: START_PAGE
        return loadPage(page, DEFAULT_PAGE_SIZE).unpack(
            success = { news ->
                LoadResult.Page(
                    data = news.articles.map(ArticleResponse::toArticle),
                    prevKey = (page - 1).takeIf { it >= 1 },
                    nextKey = (page + 1).takeIf { news.articles.size == DEFAULT_PAGE_SIZE },
                )

            },
            error = { reason ->
                LoadResult.Error(NewsLoadException(reason))
            }
        )
    }
}