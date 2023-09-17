package com.chichi289.week4.presentation.user_detail

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.chichi289.week4.R
import com.chichi289.week4.data.remote.model.UserDetail
import com.chichi289.week4.ui.components.CustomTopAppBar
import com.chichi289.week4.utils.log

@Composable
fun UserDetailScreen(
    userDetail: UserDetail,
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
        it.toString().log()
    }
}