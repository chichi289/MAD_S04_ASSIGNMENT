package com.chichi289.assignments.navigation

sealed class Screen(val route: String) {
    object MAIN : Screen("main")
    object WEEK1 : Screen("week1")
    object WEEK2 : Screen("week2")
}