package com.example.vk.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation


@Composable
fun AppNavGraph(
    navController: NavHostController,
    newsScreenContent : @Composable () -> Unit,
    favouriteScreenContent : @Composable () -> Unit,
    profileScreenContent : @Composable () -> Unit,
    commentsScreenContent : @Composable () -> Unit
){
    NavHost(startDestination = Screen.Home.route, navController = navController){
        navigation(
            startDestination = Screen.ROUTE_NAVIGATION_NEWS,
            route = Screen.ROUTE_NAVIGATION_HOME,
        ){
            composable(Screen.ROUTE_NAVIGATION_NEWS){
                newsScreenContent()
            }
            composable(route = Screen.ROUTE_NAVIGATION_COMMENTS){
                commentsScreenContent()
            }
        }

        composable(Screen.Favourite.route){
            favouriteScreenContent()
        }
        composable(Screen.Profile.route){
            profileScreenContent()
        }
    }
}