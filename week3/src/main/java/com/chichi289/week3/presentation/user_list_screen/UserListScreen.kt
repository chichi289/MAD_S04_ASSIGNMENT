package com.chichi289.week3.presentation.user_list_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.chichi289.week3.R
import com.chichi289.week3.data.model.User
import com.chichi289.week3.ui.components.CustomText
import com.chichi289.week3.ui.components.HeaderText
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

@Composable
fun UserItem(index: Int, user: User, onClick: (User) -> Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable {
                onClick.invoke(user)
            },
        elevation = CardDefaults.cardElevation(
            defaultElevation = 1.dp
        ),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.LightGray
        ),
    ) {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primaryContainer)
                .fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    Modifier.weight(0.5f)
                ) {
                    CustomText(
                        key = stringResource(R.string.txt_userid), value = user.userId.toString()
                    )
                    CustomText(key = stringResource(R.string.txt_username), value = user.userName)
                }

                Column(Modifier.weight(0.5f)) {
                    CustomText(key = stringResource(R.string.txt_fullname), value = user.fullName)
                    CustomText(key = stringResource(R.string.txt_email), value = user.email)
                }
            }
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                modifier = Modifier.drawBehind {
                    drawCircle(
                        color = Color.Gray,
                        radius = this.size.maxDimension / 1.5f,
                        style = Stroke(width = 4f)
                    )
                }, text = index.toString(), fontSize = 14.sp, color = Color.Black
            )
            Spacer(modifier = Modifier.height(12.dp))
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
