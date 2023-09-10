package com.chichi289.week3.presentation.user_detail_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.chichi289.week3.R
import com.chichi289.week3.data.model.User
import com.chichi289.week3.ui.components.CustomText

@Composable
fun UserDetailScreen(
    modifier: Modifier,
    user: User,
    viewModel: UserDetailViewModel = hiltViewModel(),
    onGoBack: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = stringResource(R.string.txt_welcome_to_user_detail_screen))

        Column(
            modifier = Modifier
                .padding(start = 16.dp, top = 24.dp, end = 16.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(Color.LightGray)
                .padding(8.dp)
        ) {
            CustomText(key = stringResource(id = R.string.txt_userid), value = user.userId.toString())
            Spacer(modifier = Modifier.height(8.dp))
            CustomText(key = stringResource(id = R.string.txt_username), value = user.userName)
            Spacer(modifier = Modifier.height(8.dp))
            CustomText(key = stringResource(id = R.string.txt_full_name), value = user.fullName)
            Spacer(modifier = Modifier.height(8.dp))
            CustomText(key = stringResource(id = R.string.txt_email), value = user.email)
        }

        Button(
            modifier = Modifier.padding(top = 24.dp),
            onClick = {
                viewModel.deleteUser(user)
                onGoBack.invoke()
            }
        ) {
            Text(text = stringResource(R.string.txt_delete_user))
        }

    }
}