package com.chichi289.week4.presentation.user_detail

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.chichi289.week4.data.remote.model.UserDetail

@Composable
fun UserDetailScreen(userDetail: UserDetail) {
    Text(text = userDetail.toString())
}