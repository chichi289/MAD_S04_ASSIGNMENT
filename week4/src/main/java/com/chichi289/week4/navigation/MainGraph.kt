package com.chichi289.week4.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.chichi289.week4.data.remote.model.UserDetail
import com.chichi289.week4.presentation.user_detail.UserDetailScreen
import com.chichi289.week4.presentation.user_profile.UserProfileScreen
import com.chichi289.week4.utils.KEY_USER

@Composable
fun MainGraph(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = Screen.UserProfileScreen.route) {
        composable(Screen.UserProfileScreen.route) {
            UserProfileScreen(
                onClickUser = { userDetail ->
                    navController.currentBackStackEntry?.savedStateHandle?.apply {
                        set(KEY_USER, userDetail)
                    }
                    navController.navigate(Screen.UserDetailScreen.route)
                }
            )
        }
        composable(Screen.UserDetailScreen.route) {
            navController.previousBackStackEntry?.savedStateHandle?.apply {
                val userDetail = get<UserDetail>(KEY_USER) ?: return@composable
                UserDetailScreen(userDetail = userDetail)
            }
        }
    }
}