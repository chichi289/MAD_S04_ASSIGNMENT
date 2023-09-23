package com.chichi289.week5.navigation

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Main : Screen("main")
    object Home : Screen("home")
    object Profile : Screen("profile")
    object PostDetail : Screen("post_detail")
}