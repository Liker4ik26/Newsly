package com.network.newsly.screens.home_news.presentation

import com.network.newsly.screens.home_news.presentation.models.NewsDetailsNavArg

interface HomeScreenNavigation {
    fun openNewsDetails(newsDetailsNavArg: NewsDetailsNavArg)
}