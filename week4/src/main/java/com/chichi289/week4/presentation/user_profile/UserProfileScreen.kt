package com.chichi289.week4.presentation.user_profile

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import com.chichi289.week4.utils.log

@Composable
fun UserProfileScreen(
    viewModel: UserProfileViewModel = hiltViewModel()
) {

    val userDataState = remember {
        viewModel.userStateFlow
    }.collectAsState()

    userDataState.value.data?.let {
        "Id:${it.id},Password:${it.password}".log()
    }

}

