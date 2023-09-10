package com.chichi289.week3.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.chichi289.week3.presentation.user_list_screen.UserListScreen

@Composable
fun MainGraph(
    paddingValues: PaddingValues,
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = Screen.USER_LIST_SCREEN.route) {
        composable(Screen.WELCOME_SCREEN.route) {

        }
        composable(Screen.USER_LIST_SCREEN.route) {
            UserListScreen(Modifier.padding(paddingValues))
        }
        composable(Screen.USER_DETAIL_SCREEN.route) {

        }
    }
}