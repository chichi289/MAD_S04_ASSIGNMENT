package com.chichi289.week5.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.chichi289.week5.presentation.login.LoginScreen
import com.chichi289.week5.presentation.login.LoginViewModel
import com.chichi289.week5.presentation.main.MainScreen
import com.chichi289.week5.presentation.post.PostDetail
import com.chichi289.week5.utils.KEY_POST_ID

@Composable
fun MainGraph(
    navController: NavHostController,
    startDestination: String
) {
    NavHost(navController = navController, startDestination = startDestination) {

        composable(Screen.Login.route) {
            val viewModel: LoginViewModel = hiltViewModel()
            val users by remember {
                viewModel.userFlow
            }.collectAsState(emptyList())

            if (users.isNotEmpty()) {
                LaunchedEffect(users) {
                    navController.navigate(Screen.Main.route) {
                        // Clear backstack
                        popUpTo(0)
                    }
                }
            }

            LoginScreen(
                onLoginClick = {
                    viewModel.getRandomUser()
                }
            )
        }

        composable(Screen.Main.route) {
            MainScreen(
                onClickPost = { postId ->
                    navController.navigate(Screen.PostDetail.postId(postId))
                }
            )
        }
        composable(
            route = Screen.PostDetail.route,
            arguments = listOf(
                navArgument(KEY_POST_ID) {
                    type = NavType.LongType
                }
            )
        ) {
            val postId = it.arguments?.getLong(KEY_POST_ID) ?: return@composable
            PostDetail(
                postId = postId,
                onBack = {},
                onDeletePost = {}
            )
        }

    }
}