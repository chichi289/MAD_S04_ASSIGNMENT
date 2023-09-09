package com.chichi289.week3.navigation

sealed class Screen(val route: String) {
    object WELCOME_SCREEN : Screen("welcome_screen")
    object USER_LIST_SCREEN : Screen("user_list_screen")
    object USER_DETAIL_SCREEN : Screen("user_detail_screen")
}