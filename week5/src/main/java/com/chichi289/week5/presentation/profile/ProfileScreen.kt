package com.chichi289.week5.presentation.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.chichi289.week5.ui.components.CountText
import com.chichi289.week5.ui.components.CustomText
import com.chichi289.week5.ui.components.CustomTopAppBar
import com.chichi289.week5.ui.components.NetworkImage
import com.chichi289.week5.ui.components.VerticalSpacer
import com.chichi289.week5.utils.nullSafe

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel
) {
    val user by remember {
        viewModel.userStateFlow
    }.collectAsState()

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
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
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
                    text = "Posts"
                )

                CountText(
                    count = user?.followers ?: 0,
                    text = "Followers"
                )

                CountText(
                    count = user?.following ?: 0,
                    text = "Following"
                )
            }

            VerticalSpacer(height = 16.dp)
            CustomText(text = user?.fullName.nullSafe())
            VerticalSpacer(height = 4.dp)
            CustomText(text = user?.biography.nullSafe())

        }
    }

}