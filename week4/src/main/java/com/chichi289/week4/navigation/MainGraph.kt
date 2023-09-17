package com.chichi289.week4.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.chichi289.week4.data.model.User
import com.chichi289.week4.presentation.user_detail_screen.UserDetailScreen
import com.chichi289.week4.presentation.user_list_screen.UserListScreen
import com.chichi289.week4.presentation.welcome_screen.WelcomeScreen
import com.chichi289.week4.utils.KEY_USER

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
        composable(Screen.UserListScreen.route) {
            UserListScreen(
                modifier = modifier,
                onUserClicked = { user ->
                    // If you go back after setting data to currentBackStackEntry
                    // then It won't be available. Use SharedViewModel for the same
                    navController.currentBackStackEntry?.savedStateHandle?.apply {
                        set(KEY_USER, user)
                    }
                    navController.navigate(Screen.UserDetailScreen.route)
                },
                onWelcomeScreen = {
                    navController.navigate(Screen.WelcomeScreen.route){
                        // Clear backstack
                        popUpTo(0)
                    }
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