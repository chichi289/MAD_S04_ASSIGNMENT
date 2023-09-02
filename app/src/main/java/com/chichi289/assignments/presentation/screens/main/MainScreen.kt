package com.chichi289.assignments.presentation.screens.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.chichi289.assignments.navigation.Screen

@Composable
fun MainScreen(
    onWeek: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { onWeek.invoke(Screen.WEEK1.route) }) {
            Text(text = Screen.WEEK1.route)
        }
        Button(onClick = { onWeek.invoke(Screen.WEEK2.route) }) {
            Text(text = Screen.WEEK2.route)
        }
    }
}

@Preview
@Composable
fun MainScreenPreview() {
    MainScreen{}
}