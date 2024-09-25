package com.example.news_app.presentation.navgraph

import androidx.navigation.NamedNavArgument

sealed class Route(
    val route: String,
    val arguments: List<NamedNavArgument> = emptyList()
) {
    object OnBoardingScreen : Route("onBoardingScreen")

    object HomeScreen : Route("homeScreen")

    object SearchScreen : Route("searchScreen")

    object BookmarkScreen : Route("bookmarkScreen")

    object DetailsScreen : Route("detailsScreen")

    object AppStartNavigation : Route("appStartNavigation")

    object NewsNavigation : Route("newsNavigation")

    object NewsNavigatorScreen : Route("newsNavigator")
}