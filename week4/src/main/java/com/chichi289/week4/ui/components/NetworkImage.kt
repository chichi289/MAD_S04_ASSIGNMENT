package com.chichi289.week4.ui.components

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import coil.compose.AsyncImage
import com.chichi289.week4.R
import com.chichi289.week4.ui.theme.LightBackground

@Composable
fun NetworkImage(
    modifier: Modifier,
    url: String,
    contentScale: ContentScale = ContentScale.Fit,
) {
    AsyncImage(
        modifier = modifier.background(LightBackground),
        model = url,
        contentScale = contentScale,
        contentDescription = stringResource(R.string.description_user_profile_picture),
    )
}