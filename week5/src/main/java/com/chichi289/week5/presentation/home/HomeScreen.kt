package com.chichi289.week5.presentation.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.chichi289.week5.R
import com.chichi289.week5.data.remote.model.post.Post
import com.chichi289.week5.ui.components.CustomText
import com.chichi289.week5.ui.components.CustomTopAppBar
import com.chichi289.week5.ui.components.ErrorItem
import com.chichi289.week5.ui.components.ExpandableText
import com.chichi289.week5.ui.components.HorizontalSpacer
import com.chichi289.week5.ui.components.LoadingIndicator
import com.chichi289.week5.ui.components.NetworkImage
import com.chichi289.week5.ui.theme.PostItemBackground
import com.chichi289.week5.utils.items
import com.chichi289.week5.utils.nullSafe

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onClickPost: (Post) -> Unit
) {

    val postLazyPagingItems = remember { viewModel.postsPagingFlow }.collectAsLazyPagingItems()

    Scaffold(topBar = {
        CustomTopAppBar(title = {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.txt_home)
            )
        })
    }) {
        PostList(
            modifier = Modifier.padding(it),
            postsLazyPagingItems = postLazyPagingItems,
            onClickPost = onClickPost
        )
    }
}

@Composable
private fun PostList(
    modifier: Modifier = Modifier,
    postsLazyPagingItems: LazyPagingItems<Post>,
    onClickPost: (Post) -> Unit
) {

    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        items(postsLazyPagingItems) { post ->
            PostItem(post = post, onClickPost = onClickPost)
        }
        postsLazyPagingItems.apply {

            when (loadState.refresh) {
                is LoadState.Loading -> {
                    item {
                        LoadingIndicator(modifier = Modifier.fillParentMaxSize())
                    }
                }

                is LoadState.Error -> {
                    val error = loadState.refresh as LoadState.Error
                    item {
                        ErrorItem(
                            modifier = Modifier.fillParentMaxSize(),
                            text = error.error.message.nullSafe(),
                            onRetryClick = { retry() })
                    }
                }

                else -> {

                }
            }

            when (loadState.append) {
                is LoadState.Loading -> {
                    item {
                        LoadingIndicator(modifier = Modifier.fillParentMaxWidth())
                    }
                }

                is LoadState.Error -> {
                    val error = loadState.append as LoadState.Error
                    item {
                        ErrorItem(
                            modifier = Modifier.fillParentMaxWidth(),
                            text = error.error.message.nullSafe(),
                            onRetryClick = { retry() })
                    }
                }

                else -> {

                }
            }

        }
    }
}

@Composable
fun PostItem(post: Post, onClickPost: (Post) -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = PostItemBackground
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            NetworkImage(
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape),
                url = post.user.profilePicUrl,
                contentScale = ContentScale.Crop
            )
            HorizontalSpacer(width = 16.dp)
            CustomText(text = post.user.username)
        }

        NetworkImage(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .clickable { onClickPost.invoke(post) },
            url = post.url,
            contentScale = ContentScale.Crop
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Icon(
                Icons.Default.FavoriteBorder,
                contentDescription = stringResource(R.string.description_fav)
            )
            HorizontalSpacer(width = 8.dp)
            CustomText(text = post.likesCount.toString())

            HorizontalSpacer(width = 16.dp)

            Icon(
                painterResource(id = R.drawable.ic_comment),
                contentDescription = stringResource(R.string.description_comment)
            )
            HorizontalSpacer(width = 8.dp)

            CustomText(text = post.commentsCount.toString())
        }

        ExpandableText(
            modifier = Modifier.padding(8.dp),
            text = post.caption.repeat(10)
        )

    }

}
