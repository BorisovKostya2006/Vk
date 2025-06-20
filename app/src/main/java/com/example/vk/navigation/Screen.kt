package com.example.vk.navigation

sealed class Screen(
    val route : String
) {
    object NewsFeed : Screen(ROUTE_NAVIGATION_NEWS)
    object Favourite : Screen(ROUTE_NAVIGATION_FAFOURITE)
    object Profile : Screen(ROUTE_NAVIGATION_PROFILE)
    object Comments : Screen(ROUTE_NAVIGATION_COMMENTS)
    object Home : Screen(ROUTE_NAVIGATION_HOME)
    companion object{
         val ROUTE_NAVIGATION_NEWS = "News"
         val ROUTE_NAVIGATION_FAFOURITE = "Favourite"
         val ROUTE_NAVIGATION_PROFILE = "Profile"
         val ROUTE_NAVIGATION_COMMENTS = "Comments"
         val ROUTE_NAVIGATION_HOME = "Home"
    }
}



