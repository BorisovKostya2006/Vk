package com.example.vk.domain

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.vk.R
import com.example.vk.navigation.Screen

sealed class NavigationItem(val titleResId : Int, val icon : ImageVector, val screen : Screen ) {
    object Home : NavigationItem(titleResId = R.string.resTitleIdHome, icon = Icons.Outlined.Home, screen = Screen.News)
    object Favourite: NavigationItem(titleResId = R.string.resTitleIdFavourite, icon = Icons.Outlined.Favorite,screen = Screen.Favourite)
    object Profile: NavigationItem(titleResId = R.string.resTitleIdProfile, icon =Icons.Outlined.Person,screen = Screen.Profile)
}