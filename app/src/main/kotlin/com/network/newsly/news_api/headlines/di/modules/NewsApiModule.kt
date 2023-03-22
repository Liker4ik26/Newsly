package com.network.newsly.news_api.headlines.di.modules

import com.network.newsly.news_api.headlines.data.NewsRepository
import com.network.newsly.news_api.headlines.data.NewsRepositoryImpl
import com.network.newsly.news_api.headlines.data.remote.NewsApiDataSource
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@InstallIn(SingletonComponent::class)
@Module
interface NewsApiModule {

    companion object {
        @Provides
        fun provideApiDataSource(
            retrofit: Retrofit
        ): NewsApiDataSource = retrofit.create(NewsApiDataSource::class.java)
    }

    @Binds
    fun bindNewsRepository(impl: NewsRepositoryImpl): NewsRepository
}