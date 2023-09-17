package com.chichi289.week4.presentation.user_profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.chichi289.week4.R
import com.chichi289.week4.data.remote.model.NetworkResult
import com.chichi289.week4.data.remote.model.User
import com.chichi289.week4.ui.components.CustomTopAppBar
import com.chichi289.week4.ui.components.UserAddressCard
import com.chichi289.week4.utils.log
import com.chichi289.week4.utils.nullSafe

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserProfileScreen(
    viewModel: UserProfileViewModel = hiltViewModel()
) {

    val userDataState: State<NetworkResult<User>> = remember {
        viewModel.userStateFlow
    }.collectAsState()

    when (userDataState.value) {
        is NetworkResult.Loading -> {
            "Loading".log()
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
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
                        AsyncImage(
                            modifier = Modifier
                                .size(120.dp)
                                .clip(CircleShape)
                                .background(Color.Cyan),
                            model = user?.avatar.nullSafe(),
                            /*model = "https://robohash.org/praesentiumsitimpedit.png?size=300x300&set=set1",*/
                            contentDescription = stringResource(R.string.description_user_profile_picture),
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
                        modifier = Modifier.padding(8.dp),
                        streetName = address?.streetName.nullSafe(),
                        city = address?.city.nullSafe(),
                        country = address?.country.nullSafe(),
                        streetAddress = address?.streetAddress.nullSafe(),
                        state = address?.state.nullSafe(),
                        zipCode = address?.zipCode.nullSafe()
                    )

                    Text(
                        modifier = Modifier.padding(top = 16.dp),
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

