package com.example.vk

data class FeedPost (
    val id : Int = 0,
    val publicationTime : String = "14:00",
    val contentText : String = "Lorem ipsum dolor is amet, ipsum dolor",
    val groupName : String = "/dev/null",
    val iconGroup : Int = R.drawable.computer,
    val contentImage : Int = R.drawable.kod,
    val statisticItem: List<StatisticItem> = listOf(StatisticItem(count = 960, type = StatisticType.VIEWS),
        StatisticItem(count = 99, type = StatisticType.SHARES),
        StatisticItem(count = 21, type = StatisticType.COMMENTS),
        StatisticItem(count = 23, type = StatisticType.LIKE))
)