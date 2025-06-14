package com.example.vk

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp


@Composable
fun PostCard(
    modifier: Modifier,
    onItemStaticClickListener: (StatisticItem) -> Unit,
    feedPost: FeedPost
){
    Card (modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        )){
        Column {
            Spacer(Modifier.height(12.dp))
            PostHeader(feedPost)
            Text(text = feedPost.contentText,Modifier.padding(8.dp), color = MaterialTheme.colorScheme.onPrimary)
            Image(painter = painterResource(feedPost.contentImage), contentDescription = "", modifier = Modifier.fillMaxWidth())
            Spacer(Modifier.height(12.dp))
            Statistics(feedPost.statisticItem,onItemClickListener = onItemStaticClickListener)
            Spacer(Modifier.height(8.dp))
        }
    }

}


@Composable
fun PostHeader(feedPost : FeedPost){
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(start = 8.dp),
        verticalAlignment = Alignment.CenterVertically) {
        Image(painter = painterResource(feedPost.iconGroup), contentDescription = "", modifier =  Modifier
            .size(50.dp)
            .clip(
                CircleShape
            ))
        Spacer(Modifier.width(8.dp))
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(feedPost.groupName,color = MaterialTheme.colorScheme.onPrimary)
            Spacer(modifier = Modifier.height(4.dp))
            Text(feedPost.publicationTime,color = MaterialTheme.colorScheme.onSecondary)
        }
        Icon(imageVector = Icons.Rounded.MoreVert, contentDescription = "", tint = MaterialTheme.colorScheme.onSecondary)
    }
}
@Composable
fun Statistics(
    statisticItem: List<StatisticItem>,
    onItemClickListener: (StatisticItem) -> Unit
){
    Row {
        val viewsItem = statisticItem.getItemType(StatisticType.VIEWS)
        Row(Modifier.weight(1f)) {
            IconWitchText(R.drawable.view, text = viewsItem.count.toString(),
                onItemClickListener = {
                    onItemClickListener(viewsItem)
                }
            )
        }
        val commentsItem = statisticItem.getItemType(StatisticType.COMMENTS)
        val sharesItem = statisticItem.getItemType(StatisticType.SHARES)
        val lakeItem = statisticItem.getItemType(StatisticType.LIKE)
        Row(Modifier.weight(1f), horizontalArrangement = Arrangement.SpaceBetween) {
            IconWitchText(R.drawable.repost,text = sharesItem.count.toString(),
                onItemClickListener = {
                    onItemClickListener(sharesItem)
                })
            IconWitchText(R.drawable.kommentar,text = commentsItem.count.toString(),
                onItemClickListener = {
                    onItemClickListener(commentsItem)
                })
            IconWitchText(R.drawable.like, text = lakeItem.count.toString(),
                onItemClickListener = {
                    onItemClickListener(lakeItem)
                })
        }
    }

}
private fun List<StatisticItem>.getItemType (type : StatisticType): StatisticItem{
    return this.find{ it.type == type } ?: throw IllegalArgumentException()
}
@Composable
fun IconWitchText(iconResId: Int, text : String, onItemClickListener: () -> Unit){
    Row(verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable{
            onItemClickListener()
        }) {
        Icon(painter = painterResource(iconResId), contentDescription = "", modifier = Modifier.size(15.dp), tint = MaterialTheme.colorScheme.onSecondary)
        Text(text=text, color = MaterialTheme.colorScheme.onSecondary)
    }

}