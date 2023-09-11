package com.chichi289.week3.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp

@Composable
fun HeaderText(modifier: Modifier,value: String) {

    Text(
        modifier = modifier,
        text = value,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        style = TextStyle(
            fontSize = 20.sp,
            fontStyle = FontStyle.Normal,
            fontFamily = FontFamily.SansSerif
        )
    )
}