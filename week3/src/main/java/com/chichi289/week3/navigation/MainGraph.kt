package com.chichi289.week3.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.chichi289.week3.data.model.User
import com.chichi289.week3.presentation.user_detail_screen.UserDetailScreen
import com.chichi289.week3.presentation.user_list_screen.UserListScreen
import com.chichi289.week3.presentation.welcome_screen.WelcomeScreen
import com.chichi289.week3.utils.KEY_USER

@Composable
fun MainGraph(
    modifier: Modifier,
    startDestination: String,
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable(Screen.WelcomeScreen.route) {
            WelcomeScreen(
                modifier = modifier,
                onUserAddedToDb = {
                    navController.navigate(Screen.UserListScreen.route) {
                        popUpTo(Screen.WelcomeScreen.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }
        composable(
            route = Screen.UserListScreen.route,
            arguments = listOf(
                navArgument(name = "user") {
                    type = NavType.ParcelableType(User::class.java)
                    nullable = false
                }
            )
        ) {
            UserListScreen(
                modifier = modifier,
                onUserClicked = { user ->
                    navController.currentBackStackEntry?.savedStateHandle?.apply {
                        set(KEY_USER, user)
                    }
                    navController.navigate(Screen.UserDetailScreen.route)
                }
            )
        }
        composable(Screen.UserDetailScreen.route) {
            navController.previousBackStackEntry?.savedStateHandle?.apply {
                val user = get<User>(KEY_USER) ?: return@composable
                UserDetailScreen(
                    modifier = modifier,
                    user = user,
                    onGoBack = {
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}