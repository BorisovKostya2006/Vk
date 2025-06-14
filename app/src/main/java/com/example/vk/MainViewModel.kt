package com.example.vk

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MainViewModel : ViewModel() {
    private val _feedPost = MutableStateFlow(FeedPost())
    var feedPost = _feedPost.asStateFlow()

    fun updateCount(item: StatisticItem){
        val oldFeedPost = feedPost.value.statisticItem
        val newFeedPost = oldFeedPost.toMutableList().apply {
            replaceAll{oldItem ->
                if (oldItem.type == item.type){
                    oldItem.copy(count= oldItem.count+1)
                }else{
                    oldItem
                }
            }
        }
        _feedPost.update { feedPost.value.copy(statisticItem = newFeedPost) }
    }
}