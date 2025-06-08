package com.example.vk

import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.unit.dp
import android.view.Surface
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource

@Composable
fun MainScreen(){
    val navigationItems = listOf(NavigationItem.Home,NavigationItem.Profile, NavigationItem.Favourite)
    Scaffold(
        bottomBar = {
            NavigationBar {
                navigationItems.forEach {
                    NavigationBarItem(
                        selected = true,
                        onClick = {},
                        icon = {
                            Icon(
                                imageVector = it.icon,
                                contentDescription = ""
                            )
                        },
                        label = { Text(text = stringResource( it.titleResId))}
                    )
                }
            }
        }
    ) { padding ->
        Surface(modifier = Modifier.padding(padding)) {
            Card()
        }
    }
    }