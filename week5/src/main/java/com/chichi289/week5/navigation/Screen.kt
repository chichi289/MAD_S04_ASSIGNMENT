package com.chichi289.week5.navigation

sealed class Screen(val route: String) {
    object Login : Screen("login_screen")
}