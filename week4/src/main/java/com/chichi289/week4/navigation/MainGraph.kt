package com.chichi289.week4.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.chichi289.week4.data.remote.model.Post
import com.chichi289.week4.presentation.post.PostDetailScreen
import com.chichi289.week4.presentation.user.UserProfileScreen
import com.chichi289.week4.utils.KEY_USER

@Composable
fun MainGraph(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = Screen.UserProfileScreen.route) {
        composable(Screen.UserProfileScreen.route) {
            UserProfileScreen(
                onClickPost = { userDetail ->
                    navController.currentBackStackEntry?.savedStateHandle?.apply {
                        set(KEY_USER, userDetail)
                    }
                    navController.navigate(Screen.UserDetailScreen.route)
                }
            )
        }
        composable(Screen.UserDetailScreen.route) {
            navController.previousBackStackEntry?.savedStateHandle?.apply {
                val userDetail = get<Post>(KEY_USER) ?: return@composable
                PostDetailScreen(
                    post = userDetail,
                    onBack = {
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}