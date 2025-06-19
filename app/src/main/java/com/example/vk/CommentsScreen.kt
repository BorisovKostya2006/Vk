package com.example.vk

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vk.domain.FeedPost
import com.example.vk.domain.ViewModelComments

@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun CommentsScreen(viewModelComments: ViewModelComments, feedPost: FeedPost) {
    val comments: MutableList<Comment> = mutableListOf()
    comments.apply {
        repeat(150) {
            comments.add(Comment(id = it))
        }
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Comments for FeedPost Id:${feedPost.id}") },
                navigationIcon = {
                    IconButton(onClick = {viewModelComments.closeComments()}) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = null
                        )
                    }
                    BackHandler {
                        viewModelComments.closeComments()
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(  // Используем colors
                    containerColor = MaterialTheme.colorScheme.background,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
                ),
                )

        },
        content = { paddingValues ->
            LazyColumn(
                modifier = Modifier.padding(paddingValues),
                contentPadding = PaddingValues(
                    end = 8.dp,
                    start = 8.dp,
                    bottom = 72.dp,
                    top = 16.dp
                )
            ) {
                items(
                    items = comments,
                    key = { it.id },
                ) { comment ->
                    CommentView(comment)
                }
            }
        }
    )
}
@Composable
private fun CommentView(comment: Comment){
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp,
            vertical = 4.dp)
    ) {
        Image(
            painterResource(comment.iconAuthor),
            contentDescription = null,
            Modifier.size(24.dp)
        )
        Spacer(Modifier.width(8.dp))
        Column {
            Text(text = "${comment.authorName} CommentId: ${comment.id}",
                color = MaterialTheme.colorScheme.onPrimary,
                fontSize = 12.sp)
            Spacer(Modifier.height(4.dp))
            Text(text = comment.commentText,color = MaterialTheme.colorScheme.onPrimary,
                fontSize = 14.sp)
            Spacer(Modifier.height(4.dp))
            Text(text = comment.publicationDate,color = MaterialTheme.colorScheme.onSecondary,
                fontSize = 12.sp)
        }
    }
}