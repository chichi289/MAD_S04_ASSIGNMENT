package com.chichi289.week4.presentation.user_profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.chichi289.week4.R
import com.chichi289.week4.data.remote.model.NetworkResult
import com.chichi289.week4.data.remote.model.User
import com.chichi289.week4.data.remote.model.UserDetail
import com.chichi289.week4.ui.components.CustomTopAppBar
import com.chichi289.week4.ui.components.ErrorItem
import com.chichi289.week4.ui.components.LoadingIndicator
import com.chichi289.week4.ui.components.UserAddressCard
import com.chichi289.week4.ui.theme.DarkBackground
import com.chichi289.week4.ui.theme.LightBackground
import com.chichi289.week4.utils.items
import com.chichi289.week4.utils.log
import com.chichi289.week4.utils.nullSafe

@Composable
fun UserProfileScreen(
    viewModel: UserProfileViewModel = hiltViewModel(),
    onClickUser: (UserDetail) -> Unit
) {

    val userDataState: State<NetworkResult<User>> = remember {
        viewModel.userStateFlow
    }.collectAsState()

    when (userDataState.value) {
        is NetworkResult.Loading -> {
            "Loading".log()
            LoadingIndicator(modifier = Modifier.fillMaxSize())
        }

        is NetworkResult.Success -> {
            val user = userDataState.value.data
            "Success".log()
            Scaffold(
                topBar = {
                    CustomTopAppBar(
                        title = {
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = user?.username.nullSafe(),
                                textAlign = TextAlign.Center
                            )
                        }
                    )
                }
            ) {
                Column(
                    modifier = Modifier
                        .padding(it)
                        .padding(top = 8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Row(
                        modifier = Modifier
                            .padding(horizontal = 8.dp)
                            .align(Alignment.Start),
                        verticalAlignment = Alignment.Top,
                    ) {

                        NetworkImage(
                            modifier = Modifier
                                .size(120.dp)
                                .clip(CircleShape),
                            url = user?.avatar.nullSafe(),

                            )

                        Column(
                            modifier = Modifier.padding(start = 16.dp)
                        ) {
                            Text(
                                text = "${user?.firstName} ${user?.lastName}",
                                style = TextStyle(
                                    color = Color.Black,
                                    fontSize = MaterialTheme.typography.titleLarge.fontSize,
                                )
                            )

                            Text(
                                modifier = Modifier.padding(top = 4.dp),
                                text = user?.employment?.title.nullSafe(),
                                style = TextStyle(
                                    color = Color.Black,
                                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
                                )
                            )

                            Text(
                                modifier = Modifier.padding(top = 2.dp),
                                text = user?.email.nullSafe(),
                                style = TextStyle(
                                    color = Color.Black,
                                    fontSize = MaterialTheme.typography.titleSmall.fontSize,
                                )
                            )
                        }
                    }

                    val address = user?.address
                    UserAddressCard(
                        modifier = Modifier.padding(10.dp),
                        streetName = address?.streetName.nullSafe(),
                        city = address?.city.nullSafe(),
                        country = address?.country.nullSafe(),
                        streetAddress = address?.streetAddress.nullSafe(),
                        state = address?.state.nullSafe(),
                        zipCode = address?.zipCode.nullSafe()
                    )

                    Text(
                        modifier = Modifier.padding(top = 4.dp),
                        text = user?.subscription?.plan.nullSafe(),
                        style = TextStyle(
                            color = Color.Black,
                            fontSize = MaterialTheme.typography.titleLarge.fontSize,
                        )
                    )

                    Text(
                        modifier = Modifier.padding(top = 4.dp),
                        text = user?.subscription?.status.nullSafe(),
                        style = TextStyle(
                            color = Color.Black,
                            fontSize = MaterialTheme.typography.titleSmall.fontSize,
                        )
                    )

                    Divider(
                        modifier = Modifier.padding(top = 4.dp),
                        color = DarkBackground,
                        thickness = 4.dp
                    )

                    val userDetailsPagingItems =
                        remember { viewModel.usersPagingFlow }.collectAsLazyPagingItems()
                    UserList(
                        modifier = Modifier.padding(top = 4.dp),
                        userDetailsPagingItems = userDetailsPagingItems,
                        onClickUser = onClickUser
                    )
                }
            }
        }

        is NetworkResult.Error -> {
            "Error".log()
            userDataState.value.message.log()
        }

        is NetworkResult.NoInternetError -> {
            "NoInternetError".log()
            userDataState.value.message.log()
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                val composition by rememberLottieComposition(LottieCompositionSpec.Asset("no_internet.json"))
                val progress by animateLottieCompositionAsState(composition)
                LottieAnimation(
                    composition = composition,
                    progress = { progress },
                )
            }
        }
    }
}

@Composable
fun UserList(
    modifier: Modifier = Modifier,
    userDetailsPagingItems: LazyPagingItems<UserDetail>,
    onClickUser: (UserDetail) -> Unit
) {
    LazyVerticalGrid(
        modifier = modifier,
        contentPadding = PaddingValues(8.dp),
        columns = GridCells.Fixed(3),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(userDetailsPagingItems) { userDetail ->
            UserItem(
                userDetail = userDetail,
                onClickUser = onClickUser
            )
        }
        userDetailsPagingItems.apply {

            when (loadState.refresh) {
                is LoadState.Loading -> {
                    item {
                        LoadingIndicator(modifier = Modifier.fillMaxSize())
                    }
                }

                is LoadState.Error -> {
                    item {
                        ErrorItem(
                            modifier = Modifier.fillMaxSize(),
                            onRetryClick = { refresh() })
                    }
                }

                else -> {

                }
            }

            when (loadState.append) {
                is LoadState.Loading -> {
                    item {
                        LoadingIndicator(modifier = Modifier.fillMaxSize())
                    }
                }

                is LoadState.Error -> {
                    item {
                        ErrorItem(
                            modifier = Modifier.fillMaxSize(),
                            onRetryClick = { retry() })
                    }
                }

                else -> {

                }
            }

        }
    }

    /*LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(userDetailsPagingItems) { repository ->
            UserItem(profilePictureUrl = repository.url)
        }
        userDetailsPagingItems.apply {

            when (loadState.refresh) {
                is LoadState.Loading -> {
                    item {
                        LoadingIndicator(modifier = Modifier.fillParentMaxSize())
                    }
                }

                is LoadState.Error -> {
                    item {
                        ErrorItem(
                            modifier = Modifier.fillParentMaxSize(),
                            onRetryClick = { refresh() })
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
                    item {
                        ErrorItem(
                            modifier = Modifier.fillParentMaxWidth(),
                            onRetryClick = { retry() })
                    }
                }

                else -> {

                }
            }

        }
    }*/
}

@Composable
fun UserItem(userDetail: UserDetail, onClickUser: (UserDetail) -> Unit) {
    NetworkImage(
        modifier = Modifier
            .size(100.dp)
            .clip(RoundedCornerShape(12.dp))
            .clickable { onClickUser.invoke(userDetail) },
        url = userDetail.downloadUrl,
        contentScale = ContentScale.Crop,
    )
}

@Composable
fun NetworkImage(
    modifier: Modifier,
    url: String,
    contentScale: ContentScale = ContentScale.Fit,
) {
    AsyncImage(
        modifier = modifier.background(LightBackground),
        model = url,
        contentScale = contentScale,
        contentDescription = stringResource(R.string.description_user_profile_picture),
    )
}


