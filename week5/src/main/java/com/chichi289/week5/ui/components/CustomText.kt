package com.chichi289.week5.ui.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow

@Composable
fun CustomText(
    modifier: Modifier = Modifier,
    text: String,
    textStyle: TextStyle = TextStyle(
        color = Color.Black,
        fontSize = MaterialTheme.typography.bodyLarge.fontSize,
    )
) {
    Text(
        modifier = modifier,
        text = text,
        style = textStyle,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
    )
}