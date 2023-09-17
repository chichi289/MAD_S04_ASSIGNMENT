package com.chichi289.week4.presentation.user_profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import coil.compose.AsyncImage
import com.chichi289.week4.R
import com.chichi289.week4.ui.components.CustomTopAppBar
import com.chichi289.week4.ui.components.UserAddressCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserProfileScreen(
    viewModel: UserProfileViewModel = hiltViewModel()
) {

    /*val userDataState: State<NetworkResult<User>> = remember {
        viewModel.userStateFlow
    }.collectAsState()

    when (userDataState.value) {
        is NetworkResult.Loading -> {
            "Loading".log()
        }

        is NetworkResult.Success -> {
            "Success".log()
            userDataState.value.data?.let {
                "Id:${it.id},Password:${it.password}".log()
            }
        }

        is NetworkResult.Error -> {
            "Error".log()
            userDataState.value.message.log()
        }

        is NetworkResult.NoInternetError -> {
            "NoInternetError".log()
            userDataState.value.message.log()
        }
    }*/

    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "Username",
                        textAlign = TextAlign.Center
                    )
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .padding(top = 8.dp)
        ) {

            Row(
                modifier = Modifier.padding(horizontal = 8.dp),
                verticalAlignment = Alignment.Top,
            ) {
                AsyncImage(
                    model = "https://robohash.org/praesentiumsitimpedit.png?size=300x300&set=set1",
                    contentDescription = stringResource(R.string.description_user_profile_picture),
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(Color.Blue),
                    contentScale = ContentScale.Crop,
                )

                Column(
                    modifier = Modifier.padding(start = 16.dp)
                ) {
                    Text(
                        text = "Chirag Prajapati",
                        style = TextStyle(
                            color = Color.Black,
                            fontSize = MaterialTheme.typography.titleLarge.fontSize,
                        )
                    )

                    Text(
                        modifier = Modifier.padding(top = 4.dp),
                        text = "Android Engineer",
                        style = TextStyle(
                            color = Color.Black,
                            fontSize = MaterialTheme.typography.titleMedium.fontSize,
                        )
                    )

                    Text(
                        modifier = Modifier.padding(top = 2.dp),
                        text = "chiragrprajapati28@gmail.com",
                        style = TextStyle(
                            color = Color.Black,
                            fontSize = MaterialTheme.typography.titleSmall.fontSize,
                        )
                    )
                }
            }

            UserAddressCard(
                modifier = Modifier.padding(8.dp),
                streetName = "Strosin Shores",
                city = "South Taylor",
                country = "United States",
                streetAddress = "652 Sang Road",
                state = "Kansas",
                zipCode = "71733"
            )
        }
    }
}

