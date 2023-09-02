package com.chichi289.assignments.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.chichi289.assignments.presentation.screens.main.MainScreen
import com.chichi289.assignments.presentation.screens.week1.CounterScreen
import com.chichi289.assignments.presentation.screens.week2.UserListScreen

@Composable
fun MainGraph(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = Screen.MAIN.route) {
        composable(Screen.MAIN.route) {
            MainScreen(onWeek = { route ->
                navController.navigate(route)
            })
        }
        composable(Screen.WEEK1.route) {
            CounterScreen()
        }
        composable(Screen.WEEK2.route) {
            UserListScreen()
        }
    }
}