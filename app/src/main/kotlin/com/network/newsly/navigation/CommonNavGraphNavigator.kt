package com.network.newsly.navigation

import android.content.Context
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.network.newsly.navigation.models.mappers.asArticle
import com.network.newsly.screens.authentication.authorization.presentation.AuthorizationScreenNavigation
import com.network.newsly.screens.authentication.registration.presentation.RegistrationScreenNavigation
import com.network.newsly.screens.destinations.NewsDetailsScreenDestination
import com.network.newsly.screens.edit_profile.presentation.EditProfileNavigation
import com.network.newsly.screens.home_news.presentation.HomeScreenNavigation
import com.network.newsly.screens.home_news.presentation.models.NewsDetailsNavArg
import com.network.newsly.screens.news_details.presentation.NewsDetailsScreenNavigation
import com.network.newsly.screens.profile.presentation.ProfileScreenNavigation
import com.network.newsly.screens.search_news.presentation.models.SearchNewsItemNavArg
import com.network.newsly.screens.search_news.presentation.SearchNewsNavigation
import com.ramcosta.composedestinations.dynamic.within
import com.ramcosta.composedestinations.navigation.navigate
import com.ramcosta.composedestinations.spec.NavGraphSpec

class CommonNavGraphNavigator(
    private val context: Context,
    private val navGraph: NavGraphSpec,
    private val navController: NavController
) : HomeScreenNavigation,
    NewsDetailsScreenNavigation,
    SearchNewsNavigation,
    RegistrationScreenNavigation,
    AuthorizationScreenNavigation,
    ProfileScreenNavigation,
    EditProfileNavigation {

    override fun openNewsDetails(newsDetailsNavArg: NewsDetailsNavArg) {
        navController.navigate(
            NewsDetailsScreenDestination
                (newsDetails = newsDetailsNavArg.asArticle()) within navGraph
        )
    }

    override fun navigationToHomeScreen() {
        navController.navigate(NavGraphs.home) {
            popUpTo(navController.graph.findStartDestination().id) { inclusive = true }
        }
    }

    override fun navigateUp() {
        navController.navigateUp()
    }

    override fun openNewsDetails(searchNewsNavArg: SearchNewsItemNavArg) {
        navController.navigate(NewsDetailsScreenDestination(newsDetails = searchNewsNavArg.asArticle()) within navGraph)
    }

    override fun navigateToRegistrationScreen() {
        navController.navigate(NavGraphs.registration)
    }

    override fun navigateToHomeScreen() {
        navController.navigate(NavGraphs.home)
    }

    override fun onNavigateBack() {
        navController.navigate(NavGraphs.authorization)
    }

    override fun onNavigateToProfileEdit() {
        navController.navigate(NavGraphs.editProfile)
    }

    override fun navigateToProfile() {
        navController.navigate(NavGraphs.profile)
    }

    override fun navigateBack() {
        navController.navigateUp()
    }
}