package com.example.vk

import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.unit.dp
import android.view.Surface
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource

@Composable
fun MainScreen(){
   Scaffold(
        bottomBar = {
            NavigationBar (
                containerColor = MaterialTheme.colorScheme.background,

            ){
                val navigationItems = listOf(NavigationItem.Home,NavigationItem.Profile, NavigationItem.Favourite)
                val indexSelectedPosition = remember{
                    mutableStateOf(0)
                }
                navigationItems.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = indexSelectedPosition.value == index,
                        onClick = {indexSelectedPosition.value = index},
                        icon = {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = ""
                            )
                        },
                        label = { Text(text = stringResource( item.titleResId))},
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
        Surface(modifier = Modifier.padding(padding), color = MaterialTheme.colorScheme.background) {
            Card()
        }
    }
    }