package com.chichi289.week5.navigation

import com.chichi289.week5.utils.KEY_POST_ID

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Main : Screen("main") {
        object Home : Screen("home")
        object Profile : Screen("profile")
    }

    object PostDetail : Screen("post_detail/{$KEY_POST_ID}") {
        fun postId(postId: Int) = "post_detail/$postId"
    }
}