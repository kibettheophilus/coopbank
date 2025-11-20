package com.theophiluskibet.coopbank.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.theophiluskibet.coopbank.R
import com.theophiluskibet.coopbank.domain.models.Card
import com.theophiluskibet.coopbank.domain.models.CardType

fun formatCardNumber(number: String): String {
    return number.chunked(4).joinToString(" ")
}

fun provideDrawable(card: Card): Int {
    return when (card.type) {
        CardType.CREDIT.name -> R.drawable.card
        else -> R.drawable.card
    }
}

@Composable
fun CardComponent(modifier: Modifier = Modifier, card: Card) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(1.58f)
    ) {
        Image(
            painter = painterResource(id = provideDrawable(card = card)),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 24.dp, end = 24.dp, top = 20.dp, bottom = 16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = card.type,
                        color = Color.White,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.sp
                    )
                    Text(
                        text = card.status,
                        color = Color(0xFFCDDC39),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = formatCardNumber(card.cardNumber),
                color = Color.White,
                fontSize = 22.sp,
                fontWeight = FontWeight.Medium,
                letterSpacing = 3.sp,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                Spacer(modifier = Modifier.fillMaxWidth(0.25f))

                Column {
                    Text(
                        text = "MONTH/YEAR",
                        color = Color.White.copy(alpha = 0.7f),
                        fontSize = 7.sp
                    )
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "CARD\nEXPIRES",
                            color = Color.White.copy(alpha = 0.7f),
                            fontSize = 5.sp,
                            lineHeight = 6.sp,
                            modifier = Modifier.padding(end = 4.dp)
                        )
                        Text(
                            text = "XX-XX",
                            color = Color.White,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }

                Spacer(modifier = Modifier.width(24.dp))

                Column {
                    Text(
                        text = "CARD\nCVV",
                        color = Color.White.copy(alpha = 0.7f),
                        fontSize = 5.sp,
                        lineHeight = 6.sp
                    )
                    Text(
                        text = "XXX",
                        color = Color.White,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = "EVERYDAY CHECKING",
                color = Color.Gray,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                letterSpacing = 1.sp,
                modifier = Modifier.padding(bottom = 4.dp)
            )
        }
    }
}

@Preview
@Composable
fun CardComponentPreview(modifier: Modifier = Modifier) {
}
