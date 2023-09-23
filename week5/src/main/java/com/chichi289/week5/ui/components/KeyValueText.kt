package com.chichi289.week5.ui.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle

@Composable
fun KeyValueText(
    modifier: Modifier = Modifier,
    key: String,
    value: String
) {
    val finalString = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = MaterialTheme.typography.titleMedium.fontSize
            ),
        ) {
            append(key)
        }

        append(": ")
        append(value)
    }

    Text(
        modifier = modifier,
        text = finalString,
        style = TextStyle(
            color = Color.Black,
            fontSize = MaterialTheme.typography.titleMedium.fontSize,
        ),
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
    )
}