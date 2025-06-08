package com.example.vk

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavigationItem(val titleResId : Int, val icon : ImageVector ) {
    object Home : NavigationItem(titleResId = R.string.resTitleIdHome, icon = Icons.Outlined.Home)
    object Favourite: NavigationItem(titleResId = R.string.resTitleIdFavourite, icon = Icons.Outlined.Favorite)
    object Profile: NavigationItem(titleResId = R.string.resTitleIdProfile, icon =Icons.Outlined.Person)
}