package com.network.newsly.news_api.headlines.data.remote.models

import com.network.newsly.core.entity.ErrorReason

class NewsLoadException(val reason: ErrorReason) : Exception()