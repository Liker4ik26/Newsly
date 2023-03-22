package com.network.newsly.screens.search_news.presentation

import com.network.newsly.screens.search_news.presentation.models.SearchNewsItemNavArg

interface SearchNewsNavigation {

    fun openNewsDetails(searchNewsNavArg: SearchNewsItemNavArg)
}