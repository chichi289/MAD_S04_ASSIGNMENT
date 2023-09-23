package com.chichi289.week5.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun CountText(
    modifier: Modifier = Modifier,
    count: Int,
    text: String
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CustomText(
            text = count.toString(),
            textStyle = TextStyle(
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                fontSize = MaterialTheme.typography.titleLarge.fontSize,
            )
        )
        VerticalSpacer(height = 8.dp)
        CustomText(text = text)

    }
}