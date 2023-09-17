package com.chichi289.week4.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chichi289.week4.R
import com.chichi289.week4.data.model.User

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