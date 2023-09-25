package com.chichi289.week5.presentation.post

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.chichi289.week5.R
import com.chichi289.week5.data.remote.model.NetworkResult
import com.chichi289.week5.ui.components.CountText
import com.chichi289.week5.ui.components.CustomTopAppBar
import com.chichi289.week5.ui.components.HyperlinkText
import com.chichi289.week5.ui.components.KeyValueText
import com.chichi289.week5.ui.components.LoadingIndicator
import com.chichi289.week5.ui.components.MyAlertDialog
import com.chichi289.week5.ui.components.NetworkImage
import com.chichi289.week5.ui.components.NoInternet
import com.chichi289.week5.utils.nullSafe

@Composable
fun PostDetailScreen(
    postId: Long,
    viewModel: PostDetailViewModel = hiltViewModel(),
    onBack: () -> Unit
) {

    val posts by remember {
        viewModel.postStateFlow
    }.collectAsState()

    val deletePost by remember {
        viewModel.deletePostStateFlow
    }.collectAsState()

    LaunchedEffect(postId) {
        viewModel.getPostDetail(postId)
    }

    // Delete post confirmation dialog
    var showDeletePostDialog by remember {
        mutableStateOf(false)
    }

    // Show progress indicator while delete post api is being called
    var showLoadingIndicator by remember {
        mutableStateOf(false)
    }

    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = stringResource(R.string.txt_post_details),
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = stringResource(R.string.description_back_arrow)
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {
                        showDeletePostDialog = true
                    }) {
                        Icon(
                            Icons.Default.Delete,
                            contentDescription = stringResource(R.string.description_delete)
                        )
                    }
                }
            )
        }
    ) {
        Box(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            contentAlignment = Alignment.TopCenter
        ) {
            Column(
                modifier = Modifier
                    .padding(all = 16.dp)
            ) {

                when (posts) {
                    is NetworkResult.Loading -> {
                        LoadingIndicator(modifier = Modifier.fillMaxSize())
                    }

                    is NetworkResult.Success -> {
                        val post = (posts as NetworkResult.Success).data
                        NetworkImage(
                            modifier = Modifier
                                .fillMaxWidth()
                                .aspectRatio(1f),
                            url = post.url.nullSafe(),
                            contentScale = ContentScale.Crop
                        )

                        Row(
                            modifier = Modifier
                                .padding(top = 12.dp)
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            CountText(
                                count = post.likesCount.nullSafe(),
                                text = stringResource(R.string.txt_likes)
                            )

                            CountText(
                                count = post.commentsCount.nullSafe(),
                                text = stringResource(R.string.txt_comments)
                            )
                        }

                        KeyValueText(
                            modifier = Modifier.padding(top = 24.dp),
                            key = stringResource(R.string.txt_created_by),
                            value = post.user.fullName.nullSafe()
                        )

                        KeyValueText(
                            modifier = Modifier.padding(top = 8.dp),
                            key = stringResource(R.string.txt_created_at),
                            value = post.createdAt.nullSafe()
                        )

                        HyperlinkText(
                            modifier = Modifier.padding(top = 8.dp),
                            fullText = "${stringResource(R.string.txt_url)} ${post.url}",
                            linkText = listOf(post.url.nullSafe()),
                            hyperlinks = listOf(post.url.nullSafe())
                        )
                    }

                    is NetworkResult.Error -> {}
                    is NetworkResult.NoInternetError -> {
                        NoInternet()
                    }
                }

            }

            when (deletePost) {
                is NetworkResult.Success -> {
                    LaunchedEffect(Unit) {
                        onBack.invoke()
                    }
                }

                else -> {
                    // Ignore
                }
            }

            if (showLoadingIndicator) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    LoadingIndicator()
                }
            }

            if (showDeletePostDialog) {
                MyAlertDialog(
                    onDismissRequest = { showDeletePostDialog = false },
                    onConfirmation = {
                        showDeletePostDialog = false
                        showLoadingIndicator = true
                        viewModel.deletePost(
                            postId = postId,
                            userId = (posts as NetworkResult.Success).data.user.userId.nullSafe()
                                .toLong()
                        )
                    },
                    dialogTitle = stringResource(R.string.txt_delete_post),
                    dialogText = stringResource(R.string.msg_are_you_sure_you_want_to_delete_this_post),
                    icon = Icons.Default.Delete
                )
            }
        }
    }
}