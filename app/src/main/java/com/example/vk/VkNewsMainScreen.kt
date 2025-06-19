package com.example.vk

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.vk.domain.NewsFeedViewModel
import com.example.vk.domain.NavigationItem
import com.example.vk.domain.ViewModelComments
import com.example.vk.navigation.AppNavGraph
import com.example.vk.navigation.NavigationState

@Composable
fun MainScreen() {
    val navigationState = NavigationState(rememberNavController())
    val viewModelComments : ViewModelComments = viewModel()
    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = MaterialTheme.colorScheme.background,
            ) {
                val navBackStackEntry =
                    navigationState.navHostController.currentBackStackEntryAsState()
                val currentRout = navBackStackEntry.value?.destination?.route
                val navigationItems =
                    listOf(NavigationItem.Home, NavigationItem.Profile, NavigationItem.Favourite)
                navigationItems.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = currentRout == item.screen.route,
                        onClick = {
                            navigationState.navigationTo(item.screen.route)
                        },
                        icon = {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = ""
                            )
                        },
                        label = { Text(text = stringResource(item.titleResId)) },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = MaterialTheme.colorScheme.onPrimary,
                            selectedTextColor = MaterialTheme.colorScheme.onPrimary,
                            unselectedIconColor = MaterialTheme.colorScheme.onSecondary,
                            unselectedTextColor = MaterialTheme.colorScheme.onSecondary,
                            indicatorColor = MaterialTheme.colorScheme.background
                        )
                    )
                }
            }
        }
    ) { padding ->
        AppNavGraph(
            navController = navigationState.navHostController as NavHostController,
            newsScreenContent = { HomeScreen(viewModelComments) },
            favouriteScreenContent = { Text(text = "Favourite") },
            profileScreenContent = { Text(text = "Profile") }
        )
        Surface(
            modifier = Modifier.padding(padding),
            color = MaterialTheme.colorScheme.background
        ) {
            if (viewModelComments.screenComments.value){
                CommentsScreen(viewModelComments,viewModelComments.feedPost.value)
            }
        }

    }

}

@Composable
fun HomeScreen(viewModelComments : ViewModelComments) {
    val viewModelNewsFeed : NewsFeedViewModel = viewModel()
    val feedPost = viewModelNewsFeed.feedPosts.collectAsState()
    LazyColumn(
        contentPadding = PaddingValues(
            end = 8.dp,
            start = 8.dp,
            bottom = 72.dp,
            top = 16.dp
        ),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {//Между элементами
        items(items = feedPost.value, key = { it.id })
        { feedPost ->
            val dismissState = rememberSwipeToDismissBoxState()
            if (dismissState.currentValue == SwipeToDismissBoxValue.EndToStart) {
                viewModelNewsFeed.removePost(feedPost)
            }
            SwipeToDismissBox(
                modifier = Modifier.animateItem(),
                state = dismissState,
                backgroundContent = {},
                enableDismissFromStartToEnd = false,
                enableDismissFromEndToStart = true
            ) {
                PostCard(
                    Modifier.padding(8.dp), feedPost = feedPost,
                    onItemStaticClickListener = { statistics ->
                        if (statistics.type == StatisticType.COMMENTS){
                            viewModelComments.showComments(feedPost)
                        }else{
                            viewModelNewsFeed.updateCount(feedPost, statistics)
                        }

                    }
                )
            }
        }

    }
}

