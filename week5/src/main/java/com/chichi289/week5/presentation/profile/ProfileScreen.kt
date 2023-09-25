package com.chichi289.week5.presentation.profile

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.chichi289.week5.R
import com.chichi289.week5.data.remote.model.NetworkResult
import com.chichi289.week5.data.remote.model.post.Post
import com.chichi289.week5.ui.components.CountText
import com.chichi289.week5.ui.components.CustomText
import com.chichi289.week5.ui.components.CustomTopAppBar
import com.chichi289.week5.ui.components.LoadingIndicator
import com.chichi289.week5.ui.components.NetworkImage
import com.chichi289.week5.ui.components.NoInternet
import com.chichi289.week5.ui.components.VerticalSpacer
import com.chichi289.week5.utils.nullSafe

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel,
    onClickPost: (Post) -> Unit
) {
    val user by remember {
        viewModel.userStateFlow
    }.collectAsState()

    val userPostsStateFlow = remember {
        viewModel.userPostsStateFlow
    }

    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = {
                    CustomText(text = user?.username.nullSafe())
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .padding(top = 16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                NetworkImage(
                    modifier = Modifier
                        .size(120.dp)
                        .clip(CircleShape),
                    url = user?.profilePicUrl.nullSafe()
                )


                CountText(
                    count = user?.postsCount ?: 0,
                    text = stringResource(R.string.txt_posts)
                )

                CountText(
                    count = user?.followers ?: 0,
                    text = stringResource(R.string.txt_followers)
                )

                CountText(
                    count = user?.following ?: 0,
                    text = stringResource(R.string.txt_following)
                )
            }

            VerticalSpacer(height = 16.dp)
            CustomText(
                modifier = Modifier.padding(horizontal = 16.dp),
                text = user?.fullName.nullSafe()
            )
            VerticalSpacer(height = 4.dp)
            CustomText(
                modifier = Modifier.padding(horizontal = 16.dp),
                text = user?.biography.nullSafe()
            )

            Divider(
                modifier = Modifier.padding(vertical = 8.dp),
                color = Color.Black,
                thickness = 4.dp
            )
            // TODO("Recomposed when come from PostDetail")
            val userPosts by userPostsStateFlow.collectAsState()
            LaunchedEffect(user?.userId) {
                user?.userId?.let { userId ->
                    viewModel.getPosts(userId = userId)
                }
            }
            when (userPosts) {
                is NetworkResult.Loading -> {
                    LoadingIndicator(modifier = Modifier.fillMaxSize())
                }

                is NetworkResult.Success -> {
                    val list = (userPosts as NetworkResult.Success).data
                    PostList(
                        list = list,
                        onClickPost = onClickPost
                    )
                }

                is NetworkResult.Error -> {

                }

                is NetworkResult.NoInternetError -> {
                    NoInternet()
                }
            }
        }
    }
}

@Composable
private fun PostList(
    modifier: Modifier = Modifier,
    list: List<Post>,
    onClickPost: (Post) -> Unit
) {
    LazyVerticalGrid(
        modifier = modifier,
        contentPadding = PaddingValues(8.dp),
        columns = GridCells.Fixed(3),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(list) { post ->
            PostItem(
                post = post,
                onClickUser = onClickPost
            )
        }
    }
}

@Composable
private fun PostItem(post: Post, onClickUser: (Post) -> Unit) {
    NetworkImage(
        modifier = Modifier
            .size(120.dp)
            .clip(RoundedCornerShape(12.dp))
            .clickable { onClickUser.invoke(post) },
        url = post.url,
        contentScale = ContentScale.Crop,
    )
}
