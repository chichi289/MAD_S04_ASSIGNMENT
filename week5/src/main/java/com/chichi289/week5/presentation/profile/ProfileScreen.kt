package com.chichi289.week5.presentation.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import com.chichi289.week5.ui.components.CustomText
import com.chichi289.week5.ui.components.LoadingIndicator
import com.chichi289.week5.utils.nullSafe

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel
) {
    val user by remember {
        viewModel.userStateFlow
    }.collectAsState()

    if (user == null) {
        LoadingIndicator()
    } else {
        Column {
            CustomText(text = user?.username.nullSafe())
            CustomText(text = user?.fullName.nullSafe())
        }
    }


}