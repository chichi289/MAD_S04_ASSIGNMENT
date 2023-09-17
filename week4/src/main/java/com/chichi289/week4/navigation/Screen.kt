package com.chichi289.week4.navigation

sealed class Screen(val route: String) {
    object WelcomeScreen : Screen("welcome_screen")
    object UserListScreen : Screen("user_list_screen")
    object UserDetailScreen : Screen("user_detail_screen")
}