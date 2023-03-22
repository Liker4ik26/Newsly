package com.network.newsly.presentation

import androidx.annotation.DrawableRes
import com.network.newsly.R
import com.network.newsly.navigation.NavGraphs.home
import com.network.newsly.navigation.NavGraphs.profile
import com.network.newsly.navigation.NavGraphs.search
import com.ramcosta.composedestinations.spec.NavGraphSpec

enum class BottomBarDestination(
    val direction: NavGraphSpec,
    @DrawableRes val icon: Int
) {
    Search(
        direction = search,
        icon = R.drawable.search_icon
    ),
    Home(
        direction = home,
        icon = R.drawable.home_icon
    ),
    Profile(
        direction = profile,
        icon = R.drawable.profile_icon
    )
}