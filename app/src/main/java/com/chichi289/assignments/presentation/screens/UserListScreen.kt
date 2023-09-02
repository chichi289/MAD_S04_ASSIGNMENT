package com.chichi289.assignments.presentation.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chichi289.assignments.R
import com.chichi289.assignments.data.model.User
import com.chichi289.assignments.domain.UserRepository
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserListScreen(userRepository: UserRepository) {

    Log.e("CHIRAG", "UserListScreen")

    val scope = rememberCoroutineScope()

    val users = remember {
        mutableStateListOf<User>()
    }

    LaunchedEffect(Unit) {
        scope.launch {
            users.addAll(userRepository.getUsersNormal())
        }
    }

    val context = LocalContext.current

    Column {
        Log.e("CHIRAG", "Column")
        TopAppBar(title = {
            Text(text = stringResource(R.string.txt_users))
        })

        if (users.isEmpty()) {
            CircularProgressIndicator()
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(8.dp)
            ) {
                Log.e("CHIRAG", "LazyColumn")
                items(count = users.size,
                    key = { it },
                    itemContent = { index ->
                        UserItem(
                            index = index + 1, user = users[index]
                        ) { selectedUser ->
                            Toast.makeText(
                                context, context.getString(
                                    R.string.msg_you_have_clicked_on_s, selectedUser.userName
                                ), Toast.LENGTH_SHORT
                            ).show()
                        }
                    })

                /*itemsIndexed(
                    users
                ) { index, user ->
                    UserItem(
                        index = index + 1, user = user
                    ) { selectedUser ->
                        Toast.makeText(
                            context,
                            context.getString(
                                R.string.msg_you_have_clicked_on_s,
                                selectedUser.userName
                            ),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }*/
            }
        }
    }
}

@Composable
fun UserItem(index: Int, user: User, onClick: (User) -> Unit) {
    Log.e("CHIRAG", "UserItem")
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                onClick.invoke(user)
            },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.LightGray
        ),
    ) {
        Log.e("CHIRAG", "Card")
        Column(
            modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    CustomText(
                        key = stringResource(R.string.txt_userid), value = user.userId.toString()
                    )
                    CustomText(key = stringResource(R.string.txt_username), value = user.userName)
                }

                Column {
                    CustomText(key = stringResource(R.string.txt_fullname), value = user.fullName)
                    CustomText(key = stringResource(R.string.txt_email), value = user.email)
                }
            }
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                modifier = Modifier
                    .padding(10.dp)
                    .drawBehind {
                        drawCircle(
                            color = Color.Gray, radius = this.size.maxDimension
                        )
                    }, text = index.toString(), color = Color.White
            )
            Spacer(modifier = Modifier.height(6.dp))
        }
    }
}

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
        text = finalString, style = TextStyle(
            fontSize = 14.sp
        )
    )
}

@Preview
@Composable
fun UserItemPreview() {
    UserItem(
        1,
        User(
            userId = 12345678L,
            userName = "chichi289",
            fullName = "Chirag Prajapati",
            email = "chirag@example.com"
        )
    ) {}
}
