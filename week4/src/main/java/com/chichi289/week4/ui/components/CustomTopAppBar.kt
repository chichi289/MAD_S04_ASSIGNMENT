package com.chichi289.week4.ui.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopAppBar(
    title: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    navigationIcon: @Composable () -> Unit = {}
) {

    /*{
        IconButton(onClick = { }) {
            Icon(
                Icons.Default.ArrowBack,
                contentDescription = "back arrow"
            )
        }
    }*/

    TopAppBar(
        title = title,
        modifier = modifier,
        navigationIcon = navigationIcon
    )
}