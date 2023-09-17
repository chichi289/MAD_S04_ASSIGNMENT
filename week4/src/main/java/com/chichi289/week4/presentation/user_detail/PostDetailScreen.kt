package com.chichi289.week4.presentation.user_detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.chichi289.week4.R
import com.chichi289.week4.data.remote.model.Post
import com.chichi289.week4.ui.components.CustomTopAppBar
import com.chichi289.week4.ui.components.KeyValueText
import com.chichi289.week4.ui.components.NetworkImage

@Composable
fun PostDetailScreen(
    post: Post,
    onBack: () -> Unit
) {
    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = stringResource(R.string.txt_post_details),
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = stringResource(R.string.description_back_arrow)
                        )
                    }
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .padding(start = 16.dp, end = 16.dp, top = 16.dp)
        ) {
            NetworkImage(
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp)),
                url = post.downloadUrl
            )

            KeyValueText(
                modifier = Modifier.padding(top = 12.dp),
                key = stringResource(R.string.txt_author),
                value = post.author,
                shouldValueBold = true
            )

            KeyValueText(
                modifier = Modifier.padding(top = 4.dp),
                key = stringResource(R.string.txt_width),
                value = post.width.toString(),
                shouldValueBold = true
            )

            KeyValueText(
                modifier = Modifier.padding(top = 4.dp),
                key = stringResource(R.string.txt_height),
                value = post.height.toString(),
                shouldValueBold = true
            )

            KeyValueText(
                modifier = Modifier.padding(top = 4.dp),
                key = stringResource(R.string.txt_url),
                value = post.downloadUrl,
                shouldValueBold = true
            )

        }
    }
}