package com.chichi289.week5.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.chichi289.week5.data.remote.model.NetworkResult
import com.chichi289.week5.presentation.login.LoginScreen
import com.chichi289.week5.presentation.login.LoginViewModel

@Composable
fun MainGraph(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = Screen.Login.route) {
        composable(Screen.Login.route) {
            val viewModel: LoginViewModel = hiltViewModel()
            val user by remember {
                viewModel.userStateFlow
            }.collectAsState()

            if (user is NetworkResult.Success) {
                Log.e("CHIRAG", "UserId:${user.data?.userId}")
            }

            LoginScreen(
                onLoginClick = {
                    viewModel.getRandomUser()
                }
            )
        }
    }
}