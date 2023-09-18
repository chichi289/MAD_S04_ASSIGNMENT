package com.chichi289.week4.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.chichi289.week4.ui.theme.DarkBackground

@Composable
fun UserAddressCard(
    modifier: Modifier,
    streetName: String,
    city: String,
    country: String,
    streetAddress: String,
    state: String,
    zipCode: String
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp)),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = DarkBackground)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {

                CustomText(text = streetName)

                CustomText(
                    modifier = Modifier.padding(top = 8.dp),
                    text = city
                )

                CustomText(
                    modifier = Modifier.padding(top = 8.dp),
                    text = country
                )

            }

            Column {

                CustomText(
                    text = streetAddress
                )

                CustomText(
                    modifier = Modifier.padding(top = 8.dp),
                    text = state
                )

                CustomText(
                    modifier = Modifier.padding(top = 8.dp),
                    text = zipCode
                )
            }
        }
    }
}