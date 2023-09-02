package com.chichi289.week2.presentation.screens

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chichi289.week2.R
import com.chichi289.week2.data.model.User

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserListScreen(users: List<User>) {

    val context = LocalContext.current

    Column {
        TopAppBar(title = {
            Text(text = stringResource(R.string.txt_users))
        })

        LazyColumn(
            contentPadding = PaddingValues(8.dp)
        ) {
            itemsIndexed(
                users
            ) { index, user ->
                UserItem(
                    index = index + 1, user = user
                ) { selectedUser ->
                    Toast.makeText(
                        context, "You've clicked on ${selectedUser.userName}", Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}

@Composable
fun UserItem(index: Int, user: User, onClick: (User) -> Unit) {
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

            Text(
                modifier = Modifier
                    .padding(16.dp)
                    .drawBehind {
                        drawCircle(
                            color = Color.Gray, radius = this.size.maxDimension
                        )
                    }, text = index.toString(), color = Color.White
            )
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
        text = finalString,
        style = TextStyle(
            fontSize = 14.sp
        )
    )
}

/*
@Preview
@Composable
fun UserListScreenPreview() {
    UserListScreen(
        listOf(
            User(
                userId = 12345678L,
                userName = "chichi289",
                fullName = "Chirag Prajapati",
                email = "chirag@example.com"
            )
        )
    )
}*/
