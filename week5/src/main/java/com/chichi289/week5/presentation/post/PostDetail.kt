package com.chichi289.week5.presentation.post

import androidx.compose.runtime.Composable
import com.chichi289.week5.ui.components.CustomText

@Composable
fun PostDetail(
    postId: Int
) {
    CustomText(text = postId.toString())
}