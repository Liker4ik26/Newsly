package com.network.newsly.navigation

import com.network.newsly.screens.destinations.AuthorizationScreenDestination
import com.network.newsly.screens.destinations.EditProfileScreenDestination
import com.network.newsly.screens.destinations.HomeNewsScreenDestination
import com.network.newsly.screens.destinations.NewsDetailsScreenDestination
import com.network.newsly.screens.destinations.ProfileScreenDestination
import com.network.newsly.screens.destinations.RegistrationScreenDestination
import com.network.newsly.screens.destinations.SearchNewsScreenDestination
import com.ramcosta.composedestinations.dynamic.routedIn
import com.ramcosta.composedestinations.spec.DestinationSpec
import com.ramcosta.composedestinations.spec.NavGraphSpec

object NavGraphs {

    val search = object : NavGraphSpec {
        override val route = "search"
        override val startRoute = SearchNewsScreenDestination routedIn this
        override val destinationsByRoute = listOf<DestinationSpec<*>>(
            SearchNewsScreenDestination,
            NewsDetailsScreenDestination
        ).routedIn(this).associateBy { it.route }
    }

    val home = object : NavGraphSpec {
        override val route = "home"
        override val startRoute = HomeNewsScreenDestination routedIn this
        override val destinationsByRoute = listOf<DestinationSpec<*>>(
            HomeNewsScreenDestination,
            NewsDetailsScreenDestination
        ).routedIn(this).associateBy { it.route }
    }

    val profile = object : NavGraphSpec {
        override val route = "profile"
        override val startRoute = ProfileScreenDestination routedIn this
        override val destinationsByRoute = listOf<DestinationSpec<*>>(
            ProfileScreenDestination,
            EditProfileScreenDestination
        ).routedIn(this).associateBy { it.route }
    }

    val registration = object : NavGraphSpec {
        override val route = "registration"
        override val startRoute = RegistrationScreenDestination routedIn this
        override val destinationsByRoute = listOf<DestinationSpec<*>>(
            RegistrationScreenDestination
        ).routedIn(this).associateBy { it.route }
    }

    val authorization = object : NavGraphSpec {
        override val route = "authorization"
        override val startRoute = AuthorizationScreenDestination routedIn this
        override val destinationsByRoute = listOf<DestinationSpec<*>>(
            AuthorizationScreenDestination
        ).routedIn(this).associateBy { it.route }
    }

    val editProfile = object : NavGraphSpec {
        override val route = "editProfile"
        override val startRoute = EditProfileScreenDestination routedIn this
        override val destinationsByRoute = listOf<DestinationSpec<*>>(
            EditProfileScreenDestination
        ).routedIn(this).associateBy { it.route }
    }

    val root = object : NavGraphSpec {
        override val route = "root"
        override val startRoute = home
        override val destinationsByRoute = emptyMap<String, DestinationSpec<*>>()
        override val nestedNavGraphs = listOf(
            search, home, profile, registration,
            authorization, editProfile
        )
    }
}