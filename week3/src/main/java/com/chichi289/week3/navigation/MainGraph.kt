package com.chichi289.week3.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.chichi289.week3.presentation.user_list_screen.UserListScreen
import com.chichi289.week3.presentation.welcome_screen.WelcomeScreen

@Composable
fun MainGraph(
    paddingValues: PaddingValues,
    startDestination: String,
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable(Screen.WELCOME_SCREEN.route) {
            WelcomeScreen(
                modifier = Modifier.padding(paddingValues),
                onUserAddedToDb = {
                    navController.navigate(Screen.USER_LIST_SCREEN.route)
                }
            )
        }
        composable(Screen.USER_LIST_SCREEN.route) {
            UserListScreen(Modifier.padding(paddingValues))
        }
        composable(Screen.USER_DETAIL_SCREEN.route) {

        }
    }
}