package com.chichi289.week5.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.chichi289.week5.presentation.login.LoginScreen
import com.chichi289.week5.presentation.main.MainScreen
import com.chichi289.week5.presentation.post.PostDetailScreen
import com.chichi289.week5.utils.KEY_POST_ID

@Composable
fun MainGraph(
    navController: NavHostController
) {

    NavHost(navController = navController, startDestination = Screen.Login.route) {

        composable(Screen.Login.route) {
            LoginScreen(
                goToHome = {
                    navController.navigate(Screen.Main.route) {
                        // Clear backstack
                        popUpTo(0)
                    }
                }
            )
        }

        composable(Screen.Main.route) {
            MainScreen(
                /*navController = navController,*/
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
            PostDetailScreen(
                postId = postId,
                onBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}