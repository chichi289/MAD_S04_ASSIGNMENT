package com.chichi289.week4.ui.components

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
import androidx.compose.ui.unit.sp

@Composable
fun KeyValueText(
    modifier: Modifier = Modifier,
    key: String,
    value: String,
    shouldValueBold: Boolean = true,
    clickable: Boolean = false
) {

    val finalString = buildAnnotatedString {
        if (shouldValueBold) {
            append(key)
        } else {
            withStyle(
                style = SpanStyle(
                    color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 12.sp
                ),
            ) {
                append(key)
            }
        }
        append(": ")
        if (shouldValueBold) {
            withStyle(
                style = SpanStyle(
                    color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 12.sp
                ),
            ) {
                append(value)
            }
        } else {
            append(value)
        }
    }

    Text(
        modifier = modifier,
        text = finalString,
        style = TextStyle(
            color = Color.Black,
            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
        ),
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
    )
}