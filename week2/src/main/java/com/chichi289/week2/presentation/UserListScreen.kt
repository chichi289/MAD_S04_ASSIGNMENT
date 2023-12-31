package com.chichi289.week2.presentation

import android.widget.Toast
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.chichi289.week2.R
import com.chichi289.week2.data.model.User

@Composable
fun UserListScreen(
    modifier: Modifier, viewModel: UserListViewModel = hiltViewModel()
) {

    val users by viewModel.users.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getUsers()
    }

    val context = LocalContext.current

    Column(
        modifier = modifier
    ) {
        if (users.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(), contentPadding = PaddingValues(8.dp)
            ) {
                itemsIndexed(
                    items = users,
                    key = { index, _ -> index }
                ) { index, user ->
                    UserItem(
                        index = index + 1, user = user
                    ) { selectedUser ->
                        Toast.makeText(
                            context, context.getString(
                                R.string.msg_you_have_clicked_on_s, selectedUser.userName
                            ), Toast.LENGTH_SHORT
                        ).show()
                    }
                }
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
