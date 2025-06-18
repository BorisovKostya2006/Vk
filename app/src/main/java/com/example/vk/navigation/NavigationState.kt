package com.example.vk.navigation

import androidx.navigation.NavController

class NavigationState(val navHostController : NavController) {
    fun navigationTo(route : String){
        navHostController.navigate(route) {
            launchSingleTop = true
            popUpTo(Screen.ROUTE_NAVIGATION_NEWS) {
                saveState = true
            }
            restoreState = true
        }
    }
}