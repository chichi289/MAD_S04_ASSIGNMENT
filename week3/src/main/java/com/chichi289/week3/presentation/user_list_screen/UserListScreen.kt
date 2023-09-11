package com.chichi289.week3.presentation.user_list_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.chichi289.week3.R
import com.chichi289.week3.data.model.User
import com.chichi289.week3.ui.components.HeaderText
import com.chichi289.week3.ui.components.UserItem
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun UserListScreen(
    modifier: Modifier,
    onUserClicked: (User) -> Unit,
    onWelcomeScreen: () -> Unit,
    viewModel: UserListViewModel = hiltViewModel()
) {

    val users by viewModel.users.collectAsState(emptyList())

    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(users) {
        // Flow's initial value will be empty list
        // To ignore that used delay
        delay(1500)
        if (users.isEmpty()) {
            viewModel.resetUsersAddedToDb()
            onWelcomeScreen.invoke()
        }
    }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (users.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else {
            HeaderText(
                modifier = Modifier.padding(top = 8.dp),
                stringResource(R.string.txt_welcome_to_user_list_screen)
            )
            LazyColumn(
                modifier = Modifier
                    .padding(top = 8.dp)
                    .fillMaxSize()
                    .weight(1f),
                contentPadding = PaddingValues(8.dp),
                state = listState
            ) {
                itemsIndexed(
                    items = users,
                    key = { index, _ -> index }
                ) { index, user ->
                    UserItem(
                        index = index + 1, user = user
                    ) { selectedUser ->
                        onUserClicked.invoke(selectedUser)
                    }
                }
            }
            Button(
                modifier = Modifier.padding(vertical = 8.dp),
                onClick = {
                    viewModel.addOneUserToDb()
                    coroutineScope.launch {
                        listState.animateScrollToItem(index = users.size - 1)
                    }
                }) {
                Text(text = stringResource(R.string.txt_add_user))
            }
        }
    }
}

@Preview
@Composable
fun UserItemPreview() {
    UserItem(
        1, User(
            userId = 12345678L,
            userName = "chichi289",
            fullName = "Chirag Prajapati",
            email = "chiragrprajapati28@gmail.com"
        )
    ) {}
}
