package com.chichi289.week3.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp

@Composable
fun CustomText(key: String, value: String) {

    val finalString = buildAnnotatedString {
        append(key)
        append(": ")
        withStyle(
            style = SpanStyle(
                color = Color.Black, fontWeight = FontWeight.Bold
            )
        ) {
            append(value)
        }
    }

    Text(
        text = finalString, fontSize = 12.sp, maxLines = 1, overflow = TextOverflow.Ellipsis
    )
}