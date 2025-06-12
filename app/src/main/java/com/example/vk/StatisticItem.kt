package com.example.vk

data class StatisticItem(
    val count : Int,
    val type : StatisticType
)

enum class StatisticType{
    VIEWS, LIKE, SHARES, COMMENTS
}