package com.chichi289.week4.navigation

sealed class Screen(val route: String) {
    object UserProfileScreen : Screen("user_profile_screen")
    object UserDetailScreen : Screen("user_detail_screen")
}