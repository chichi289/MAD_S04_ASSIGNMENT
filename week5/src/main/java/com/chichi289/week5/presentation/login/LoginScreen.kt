package com.chichi289.week5.presentation.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.chichi289.week5.R
import com.chichi289.week5.ui.components.CustomText
import com.chichi289.week5.ui.components.LoadingIndicator
import com.chichi289.week5.ui.components.VerticalSpacer

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    goToHome: () -> Unit
) {
    val users by remember {
        viewModel.userFlow
    }.collectAsState(emptyList())

    var showProgress by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(users) {
        if (users.isNotEmpty()) {
            goToHome.invoke()
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CustomText(
            text = stringResource(R.string.txt_hey_there),
            textStyle = TextStyle(
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = MaterialTheme.typography.headlineLarge.fontSize,
            )
        )
        VerticalSpacer(height = 12.dp)
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            if (showProgress) {
                LoadingIndicator(Modifier.fillMaxWidth())
            } else {
                Button(onClick = {
                    showProgress = true
                    viewModel.getRandomUser()
                }) {
                    Text(text = stringResource(R.string.txt_login))
                }
            }
        }
    }
}