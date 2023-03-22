package com.network.newsly.screens.news_details.presentation

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class NewsDetailsNavArgs(val newsDetails: NewsDetails)

@Parcelize
data class NewsDetails(
    val author: String?,
    val title: String,
    val description: String?,
    val source: String,
    val url: String,
    val imageUrl: String?,
    val date: String,
    val content: String?
) : Parcelable
