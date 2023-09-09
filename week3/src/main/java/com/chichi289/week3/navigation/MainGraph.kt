package com.chichi289.week3.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun MainGraph(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = Screen.WELCOME_SCREEN.route) {
        composable(Screen.WELCOME_SCREEN.route) {

        }
        composable(Screen.USER_LIST_SCREEN.route) {

        }
        composable(Screen.USER_DETAIL_SCREEN.route) {

        }
    }
}