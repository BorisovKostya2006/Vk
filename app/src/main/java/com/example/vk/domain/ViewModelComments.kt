package com.example.vk.domain

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.vk.CommentsScreen
import com.example.vk.MainActivity

class ViewModelComments : ViewModel() {
    private val _screenComments = mutableStateOf(false)
    val screenComments = _screenComments
    private val _feedPost = mutableStateOf(FeedPost())
    val feedPost = _feedPost
    fun showComments(feedPost: FeedPost){
        _screenComments.value = true
        _feedPost.value = feedPost
    }
    fun closeComments (){
        _screenComments.value = false
    }
}