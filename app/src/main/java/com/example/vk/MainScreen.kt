package com.example.vk

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import java.nio.file.Files.delete

@Composable
fun MainScreen(viewModel : MainViewModel){
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
        }
        val feedPost = viewModel.feedPosts.collectAsState()
       LazyColumn(contentPadding = PaddingValues(
           end = 8.dp,
           start = 8.dp,
           bottom = 72.dp,
           top = 16.dp
       ),
           verticalArrangement = Arrangement.spacedBy(8.dp)) {//Между элементами
           items(items = feedPost.value, key ={it.id})
           {feedPost ->
               val dismissState = rememberSwipeToDismissBoxState()
               if (dismissState.currentValue == SwipeToDismissBoxValue.EndToStart) {
                   viewModel.removePost(feedPost)
               }
               SwipeToDismissBox(
                   modifier = Modifier.animateItem(),
                   state = dismissState,
                   backgroundContent = {},
                   enableDismissFromStartToEnd = false,
                   enableDismissFromEndToStart = true
               ) {
                   PostCard(Modifier.padding(8.dp),feedPost= feedPost,
                       onItemStaticClickListener = {statistics ->
                           viewModel.updateCount(feedPost, statistics)}
                   )
               }
           }
       }

    }
    }

