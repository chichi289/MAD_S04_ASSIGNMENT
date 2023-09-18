package com.chichi289.week4.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import com.chichi289.week4.R

@Composable
fun ErrorItem(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.error,
    text: String,
    onRetryClick: () -> Unit
) {
    Box(modifier = modifier) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CustomText(
                text = text,
                textStyle = TextStyle(
                    color = color
                )
            )
            Button(onClick = { onRetryClick() }) {
                Text(text = stringResource(R.string.txt_retry))
            }
        }
    }
}