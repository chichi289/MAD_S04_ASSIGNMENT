package com.chichi289.week3.presentation.welcome_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.chichi289.week3.R

@Composable
fun WelcomeScreen(
    modifier: Modifier,
    viewModel: WelcomeViewModel = hiltViewModel(),
    onUserAddedToDb: () -> Unit
) {

    val databaseUsers by viewModel.dbUsers.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (databaseUsers.isNotEmpty()) {
            onUserAddedToDb.invoke()
        }
        Button(onClick = {
            viewModel.saveUserToDatabase()
        }) {
            Text(text = stringResource(R.string.txt_add_users))
        }
    }
}