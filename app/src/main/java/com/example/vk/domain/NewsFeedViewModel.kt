package com.example.vk.domain

import androidx.lifecycle.ViewModel
import com.example.vk.StatisticItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class NewsFeedViewModel : ViewModel() {
    private val sourceList = mutableListOf<FeedPost>().apply {
        repeat(10){
            add(FeedPost(id = it))
        }
    }
    private val _feedPosts = MutableStateFlow<List<FeedPost>>(sourceList)
    var feedPosts = _feedPosts.asStateFlow()

    fun updateCount(feedPost: FeedPost, item: StatisticItem){
        val oldPosts = feedPosts.value.toMutableList()
        val oldStatistics = feedPost.statisticItem
        val newStatistics = oldStatistics.toMutableList().apply {
            replaceAll{oldItem ->
                if (oldItem.type == item.type){
                    oldItem.copy(count= oldItem.count+1)
                }else{
                    oldItem
                }
            }
        }
        val newFeedPost = feedPost.copy(statisticItem = newStatistics)
        _feedPosts.update { oldPosts.apply {
            replaceAll{
                if(it.id == newFeedPost.id){
                    newFeedPost
                }else{
                    it
                }
            }
        } }
    }


        fun removePost(feedPost: FeedPost) {
            val oldFeedPostList = feedPosts.value.toMutableList()
            val feedPostForDelete = oldFeedPostList.find { it.id == feedPost.id }
            oldFeedPostList.remove(feedPostForDelete)
            _feedPosts.value = oldFeedPostList
        }

}