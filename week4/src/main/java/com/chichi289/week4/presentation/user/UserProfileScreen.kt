package com.chichi289.week4.presentation.user

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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.chichi289.week4.R
import com.chichi289.week4.data.remote.model.Address
import com.chichi289.week4.data.remote.model.NetworkResult
import com.chichi289.week4.data.remote.model.Post
import com.chichi289.week4.data.remote.model.User
import com.chichi289.week4.ui.components.CustomText
import com.chichi289.week4.ui.components.CustomTopAppBar
import com.chichi289.week4.ui.components.ErrorItem
import com.chichi289.week4.ui.components.LoadingIndicator
import com.chichi289.week4.ui.components.NetworkImage
import com.chichi289.week4.ui.theme.DarkBackground
import com.chichi289.week4.utils.items
import com.chichi289.week4.utils.nullSafe

@Composable
fun UserProfileScreen(
    viewModel: UserProfileViewModel = hiltViewModel(),
    onClickUser: (Post) -> Unit
) {

    val userDataState: State<NetworkResult<User>> = remember {
        viewModel.userStateFlow
    }.collectAsState()

    // Initial TopAppBar title is loading after api response username is set
    val textLoading = stringResource(R.string.txt_loading)
    var userName: String by remember {
        mutableStateOf(textLoading)
    }
    val postLazyPagingItems = remember { viewModel.postsPagingFlow }

    /*when (userDataState.value) {
        is NetworkResult.Loading -> {
            LoadingIndicator(modifier = Modifier.fillMaxSize())
        }

        is NetworkResult.Success -> {
            val user = userDataState.value.data
            Scaffold(topBar = {
                CustomTopAppBar(
                    title = {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = user?.username.nullSafe(),
                            textAlign = TextAlign.Center
                        )
                    }
                )
            }) {
                Column(
                    modifier = Modifier
                        .padding(it)
                        .padding(top = 8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    UserProfile(
                        modifier = Modifier
                            .padding(horizontal = 8.dp)
                            .align(Alignment.Start),
                        user = user
                    )

                    UserAddressCard(
                        modifier = Modifier.padding(10.dp),
                        address = user?.address
                    )

                    CustomText(
                        modifier = Modifier.padding(top = 4.dp),
                        text = user?.subscription?.plan.nullSafe(),
                        textStyle = TextStyle(
                            color = Color.Black,
                            fontSize = MaterialTheme.typography.titleLarge.fontSize,
                        )
                    )

                    CustomText(
                        modifier = Modifier.padding(top = 4.dp),
                        text = user?.subscription?.status.nullSafe(),
                        textStyle = TextStyle(
                            color = Color.Black,
                            fontSize = MaterialTheme.typography.titleSmall.fontSize,
                        )
                    )

                    Divider(
                        modifier = Modifier.padding(top = 4.dp),
                        color = Color.Black,
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
            userDataState.value.message.log()
        }

        is NetworkResult.NoInternetError -> {
            userDataState.value.message.log()
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                val composition by rememberLottieComposition(LottieCompositionSpec.Asset("no_internet.json"))
                val progress by animateLottieCompositionAsState(composition)
                LottieAnimation(
                    modifier = Modifier.size(300.dp),
                    composition = composition,
                    progress = { progress },
                )
            }
        }
    }*/


    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = userName,
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
            if (userDataState.value is NetworkResult.Loading) {
                LoadingIndicator(modifier = Modifier.fillMaxSize())
            }

            if (userDataState.value is NetworkResult.Success) {
                val user = userDataState.value.data
                userName = user?.username.nullSafe()
                UserProfile(
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .align(Alignment.Start),
                    user = user
                )

                UserAddressCard(
                    modifier = Modifier.padding(10.dp),
                    address = user?.address
                )

                CustomText(
                    modifier = Modifier.padding(top = 4.dp),
                    text = user?.subscription?.plan.nullSafe(),
                    textStyle = TextStyle(
                        color = Color.Black,
                        fontSize = MaterialTheme.typography.titleLarge.fontSize,
                    )
                )

                CustomText(
                    modifier = Modifier.padding(top = 4.dp),
                    text = user?.subscription?.status.nullSafe(),
                    textStyle = TextStyle(
                        color = Color.Black,
                        fontSize = MaterialTheme.typography.titleSmall.fontSize,
                    )
                )

                Divider(
                    modifier = Modifier.padding(top = 4.dp),
                    color = Color.Black,
                    thickness = 4.dp
                )

                val pagingItems = postLazyPagingItems.collectAsLazyPagingItems()

                UserList(
                    modifier = Modifier.padding(top = 4.dp),
                    userDetailsPagingItems = pagingItems,
                    onClickUser = onClickUser
                )

            }

            if (userDataState.value is NetworkResult.NoInternetError) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    val composition by rememberLottieComposition(LottieCompositionSpec.Asset("no_internet.json"))
                    val progress by animateLottieCompositionAsState(composition)
                    LottieAnimation(
                        modifier = Modifier.size(300.dp),
                        composition = composition,
                        progress = { progress },
                    )
                }
            }

        }
    }

}

@Composable
fun UserProfile(
    modifier: Modifier,
    user: User?
) {
    Row(
        modifier = modifier,
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
}

@Composable
fun UserAddressCard(
    modifier: Modifier,
    address: Address?
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp)),
        colors = CardDefaults.cardColors(containerColor = DarkBackground)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {

                CustomText(text = address?.streetName.nullSafe())

                CustomText(
                    modifier = Modifier.padding(top = 8.dp),
                    text = address?.city.nullSafe()
                )

                CustomText(
                    modifier = Modifier.padding(top = 8.dp),
                    text = address?.country.nullSafe()
                )

            }

            Column {

                CustomText(
                    text = address?.streetAddress.nullSafe()
                )

                CustomText(
                    modifier = Modifier.padding(top = 8.dp),
                    text = address?.state.nullSafe()
                )

                CustomText(
                    modifier = Modifier.padding(top = 8.dp),
                    text = address?.zipCode.nullSafe()
                )
            }
        }
    }
}

@Composable
fun UserList(
    modifier: Modifier = Modifier,
    userDetailsPagingItems: LazyPagingItems<Post>,
    onClickUser: (Post) -> Unit
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
fun UserItem(userDetail: Post, onClickUser: (Post) -> Unit) {
    NetworkImage(
        modifier = Modifier
            .size(100.dp)
            .clip(RoundedCornerShape(12.dp))
            .clickable { onClickUser.invoke(userDetail) },
        url = userDetail.downloadUrl,
        contentScale = ContentScale.Crop,
    )
}


