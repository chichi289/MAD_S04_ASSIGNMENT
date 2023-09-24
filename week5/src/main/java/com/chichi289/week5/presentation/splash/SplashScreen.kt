package com.chichi289.week5.presentation.splash

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.chichi289.week5.navigation.Screen
import com.chichi289.week5.ui.components.LoadingIndicator
import com.chichi289.week5.ui.components.NetworkImage
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    onNavigate: (String) -> Unit,
    viewModel: SplashViewModel = hiltViewModel()
) {

    val users by remember {
        viewModel.userFlow
    }.collectAsState(emptyList())

    LaunchedEffect(Unit) {
        // Hold splash screen for 2 seconds
        delay(2000)
        if (users.isEmpty()) {
            onNavigate.invoke(Screen.Login.route)
        } else {
            onNavigate.invoke(Screen.Main.route)
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        NetworkImage(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f),
            backgroundColor = Color.White,
            url = "https://static1.anpoimages.com/wordpress/wp-content/uploads/2023/09/android-logo-redesign-2023-hero.jpg"
        )
        LoadingIndicator(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        )
    }
}
