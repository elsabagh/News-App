package com.example.news_app.presentation.news_navigator

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.news_app.R
import com.example.news_app.domain.model.Article
import com.example.news_app.presentation.bookmark.BookMarkScreen
import com.example.news_app.presentation.bookmark.BookMarkViewModel
import com.example.news_app.presentation.details.DetailsEvent
import com.example.news_app.presentation.details.DetailsScreen
import com.example.news_app.presentation.details.DetailsViewModel
import com.example.news_app.presentation.home.HomeScreen
import com.example.news_app.presentation.home.HomeViewModel
import com.example.news_app.presentation.navgraph.Route
import com.example.news_app.presentation.news_navigator.components.BottomNavigationItem
import com.example.news_app.presentation.news_navigator.components.NewsBottomNavigation
import com.example.news_app.presentation.search.SearchScreen
import com.example.news_app.presentation.search.SearchViewModel

@Composable
fun NewsNavigator(

) {

    val bottomNavigationItem = remember {
        listOf(
            BottomNavigationItem(
                icon = R.drawable.baseline_home_24,
                text = "Home"
            ),
            BottomNavigationItem(
                icon = R.drawable.baseline_search_24,
                text = "Search"
            ),
            BottomNavigationItem(
                icon = R.drawable.baseline_bookmarks_24,
                text = "Bookmark"
            )
        )
    }

    val navController = rememberNavController()
    val backStackState = navController.currentBackStackEntryAsState().value
    var selectedItem by rememberSaveable {
        mutableStateOf(0)
    }

    selectedItem =
        remember(key1 = backStackState) {
            when (backStackState?.destination?.route) {
                Route.HomeScreen.route -> 0
                Route.SearchScreen.route -> 1
                Route.BookmarkScreen.route -> 2
                else -> 0
            }
        }
    val isBottomBarVisible = when (backStackState?.destination?.route) {
        Route.DetailsScreen.route -> false
        else -> true
    }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            if (isBottomBarVisible) {
                NewsBottomNavigation(
                    items = bottomNavigationItem,
                    selected = selectedItem,
                    onItemClick = { index ->
                        when (index) {
                            0 -> navigateToTob(
                                navController = navController,
                                route = Route.HomeScreen.route
                            )

                            1 -> navigateToTob(
                                navController = navController,
                                route = Route.SearchScreen.route
                            )

                            2 -> navigateToTob(
                                navController = navController,
                                route = Route.BookmarkScreen.route
                            )

                        }
                    }
                )
            }
        }
    ) {
        val bottomPadding = it.calculateBottomPadding()
        NavHost(
            navController = navController,
            startDestination = Route.HomeScreen.route,
            modifier = Modifier.padding(bottom = bottomPadding)
        ) {
            composable(route = Route.HomeScreen.route) {
                val viewModel: HomeViewModel = hiltViewModel()
                val article = viewModel.news.collectAsLazyPagingItems()
                HomeScreen(
                    articles = article,
                    navigateToSearch = {
                        navigateToTob(
                            navController = navController,
                            route = Route.SearchScreen.route
                        )
                    },
                    navigateToDetails = { article ->
                        navigateToDetails(
                            navController = navController,
                            article = article,
                        )
                    }
                )
            }
            composable(route = Route.SearchScreen.route) {
                val viewModel: SearchViewModel = hiltViewModel()
                val state = viewModel.state.value
                SearchScreen(
                    state = state,
                    event = viewModel::onEvent,
                    navigateToDetails = { article ->
                        navigateToDetails(
                            navController = navController,
                            article = article,
                        )
                    }
                )
            }
            composable(route = Route.DetailsScreen.route) {
                val viewModel: DetailsViewModel = hiltViewModel()
                if(viewModel.sideEffect != null){
                    Toast.makeText(LocalContext.current, viewModel.sideEffect, Toast.LENGTH_SHORT).show()
                    viewModel.onEvent(DetailsEvent.RemoveSideEffect)
                }
                navController.previousBackStackEntry?.savedStateHandle?.get<Article>("article")
                    ?.let { article ->
                        DetailsScreen(
                            article = article,
                            event = viewModel::onEvent,
                            navigateUp = {
                                navController.navigateUp()
                            }

                        )
                    }

            }

            composable(route = Route.BookmarkScreen.route) {
                val viewModel: BookMarkViewModel = hiltViewModel()
                val state = viewModel.state.value
                BookMarkScreen(
                    state = state,
                    navigateToDetails = { article ->
                        navigateToDetails(
                            navController = navController,
                            article = article,
                        )
                    }
                )
            }
        }
    }
}

fun navigateToTob(
    navController: NavController,
    route: String,
) {
    navController.navigate(route) {
        navController.graph.startDestinationRoute?.let { homeScreen ->
            popUpTo(homeScreen) {
                saveState = true
            }
            restoreState = true
            launchSingleTop = true
        }
    }
}

private fun navigateToDetails(
    navController: NavController,
    article: Article,
) {
    navController.currentBackStackEntry?.savedStateHandle?.set("article", article)
    navController.navigate(Route.DetailsScreen.route)

}