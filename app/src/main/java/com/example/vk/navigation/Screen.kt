package com.example.vk.navigation

sealed class Screen(
    val route : String
) {
    object News : Screen(ROUTE_NAVIGATION_NEWS)
    object Favourite : Screen(ROUTE_NAVIGATION_FAFOURITE)
    object Profile : Screen(ROUTE_NAVIGATION_PROFILE)
    companion object{
        val ROUTE_NAVIGATION_NEWS = "News"
        val ROUTE_NAVIGATION_FAFOURITE = "Favourite"
        val ROUTE_NAVIGATION_PROFILE = "Profile"
    }
}



