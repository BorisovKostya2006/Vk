package com.example.vk

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun CardVk(modifier: Modifier){
    Card (modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        )){
        Column {
            Spacer(Modifier.height(12.dp))
            PostHeader()
            Text(stringResource(R.string.ipsum),Modifier.padding(8.dp), color = MaterialTheme.colorScheme.onPrimary)
            Image(painter = painterResource(R.drawable.kod), contentDescription = "", modifier = Modifier.fillMaxWidth())
            Spacer(Modifier.height(12.dp))
            Statistics()
            Spacer(Modifier.height(8.dp))
        }
    }

}


@Composable
fun PostHeader(){
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(start = 8.dp),
        verticalAlignment = Alignment.CenterVertically) {
        Image(painter = painterResource(R.drawable.computer), contentDescription = "", modifier =  Modifier.size(50.dp).clip(
            CircleShape
        ))
        Spacer(Modifier.width(8.dp))
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text("/dev/null",color = MaterialTheme.colorScheme.onPrimary)
            Spacer(modifier = Modifier.height(4.dp))
            Text("14:00",color = MaterialTheme.colorScheme.onSecondary)
        }
        Icon(imageVector = Icons.Rounded.MoreVert, contentDescription = "", tint = MaterialTheme.colorScheme.onSecondary)
    }
}
@Composable
fun Statistics(){
    Row {
        Row(Modifier.weight(1f)) {
            IconWitchText(R.drawable.view, text = "960")
        }
        Row(Modifier.weight(1f), horizontalArrangement = Arrangement.SpaceBetween) {
            IconWitchText(R.drawable.repost,text = "99")
            IconWitchText(R.drawable.kommentar,text = "21")
            IconWitchText(R.drawable.like, text = "23")
        }
    }

}
@Composable
fun IconWitchText(iconResId: Int, text : String){
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(painter = painterResource(iconResId), contentDescription = "", modifier = Modifier.size(15.dp), tint = MaterialTheme.colorScheme.onSecondary)
        Text(text=text, color = MaterialTheme.colorScheme.onSecondary)
    }

}