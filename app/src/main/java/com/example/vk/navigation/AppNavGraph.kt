package com.example.vk.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable


@Composable
fun AppNavGraph(
    navController: NavHostController,
    newsScreenContent : @Composable () -> Unit,
    favouriteScreenContent : @Composable () -> Unit,
    profileScreenContent : @Composable () -> Unit,
){
    NavHost(startDestination = Screen.News.route, navController = navController){
        composable(Screen.ROUTE_NAVIGATION_NEWS){
            newsScreenContent()
        }
        composable(Screen.Favourite.route){
            favouriteScreenContent()
        }
        composable(Screen.Profile.route){
            profileScreenContent()
        }
    }
}