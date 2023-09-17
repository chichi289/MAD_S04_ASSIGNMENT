package com.chichi289.week4.presentation.user_profile

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import com.chichi289.week4.data.remote.model.NetworkResult
import com.chichi289.week4.data.remote.model.User
import com.chichi289.week4.utils.log

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
    }


}

