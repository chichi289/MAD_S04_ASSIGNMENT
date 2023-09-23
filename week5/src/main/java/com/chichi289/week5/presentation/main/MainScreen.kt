package com.chichi289.week5.presentation.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.chichi289.week5.R
import com.chichi289.week5.navigation.Screen
import com.chichi289.week5.presentation.home.HomeScreen
import com.chichi289.week5.presentation.profile.ProfileScreen
import com.chichi289.week5.ui.components.CustomText

data class NavItem(
    val screen: Screen,
    val icon: ImageVector,
    val title: String
)

@Composable
fun MainScreen() {

    val items = listOf(
        NavItem(Screen.Main.Home, Icons.Filled.Home, stringResource(R.string.nav_item_home)),
        NavItem(
            Screen.Main.Profile, Icons.Filled.AccountCircle,
            stringResource(R.string.nav_item_profile)
        )
    )

    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination: NavDestination =
                    navBackStackEntry?.destination ?: NavDestination(Screen.Main.Home.route)
                items.forEach { navItem ->
                    NavigationBarItem(
                        selected = currentDestination.hierarchy.any { it.route == navItem.screen.route },
                        onClick = {
                            navController.navigate(navItem.screen.route) {
                                // Pop up to the start destination of the graph to
                                // avoid building up a large stack of destinations
                                // on the back stack as users select items
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                // Avoid multiple copies of the same destination when
                                // re-selecting the same item
                                launchSingleTop = true
                                // Restore state when re-selecting a previously selected item
                                restoreState = true
                            }

                        },
                        icon = {
                            Icon(
                                navItem.icon,
                                contentDescription = null
                            )
                        },
                        label = {
                            CustomText(text = navItem.title)
                        },
                        alwaysShowLabel = false
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Main.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Main.Home.route) {
                HomeScreen()
            }
            composable(Screen.Main.Profile.route) {
                ProfileScreen()
            }
        }
    }
}