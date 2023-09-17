package com.chichi289.week4.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.chichi289.week4.presentation.user_detail_screen.UserDetailScreen
import com.chichi289.week4.presentation.user_list_screen.UserProfileScreen

@Composable
fun MainGraph(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = Screen.UserProfileScreen.route) {
        composable(Screen.UserProfileScreen.route) {
            UserProfileScreen()
        }
        composable(Screen.UserDetailScreen.route) {
            UserDetailScreen()
        }
    }
}