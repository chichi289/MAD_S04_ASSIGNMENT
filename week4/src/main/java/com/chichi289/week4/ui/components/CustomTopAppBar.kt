package com.chichi289.week4.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopAppBar() {
    TopAppBar(
        title = {
            Text(text = "Week4")
        },
        navigationIcon = {

            IconButton(onClick = { }) {
                Icon(
                    Icons.Default.ArrowBack,
                    contentDescription = "back arrow"
                )
            }

        })
}