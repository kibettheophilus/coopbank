package com.theophiluskibet.coopbank.presentation.screens.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.theophiluskibet.coopbank.R
import com.theophiluskibet.coopbank.presentation.components.ActionButton
import com.theophiluskibet.coopbank.presentation.components.CardComponent
import com.theophiluskibet.coopbank.presentation.components.LoadingComponent
import com.theophiluskibet.coopbank.presentation.components.TransactionListItem
import com.theophiluskibet.coopbank.presentation.ui.theme.CoopDarkGreen
import com.theophiluskibet.coopbank.presentation.ui.theme.CoopGreen
import com.theophiluskibet.coopbank.presentation.ui.theme.LightText
import org.koin.androidx.compose.koinViewModel

@Composable
fun CardDetailScreen(
    id: String,
    onBackClick: () -> Unit = {},
    cardDetailsViewModel: CardDetailsViewModel = koinViewModel()
) {

    val uiState by cardDetailsViewModel.cardDetailsState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = true) {
        cardDetailsViewModel.getCard(id = id)
    }
    CardDetailScreenContent(uiState = uiState, onBackClick = onBackClick)

}

@Composable
fun CardDetailScreenContent(uiState: CardDetailsUiState, onBackClick: () -> Unit) {
    when {
        uiState.isLoading -> {
            LoadingComponent()
        }

        uiState.error.isNotEmpty() -> {
            // show error
        }

        uiState.data != null -> {
            Scaffold(
                containerColor = CoopGreen
            ) { paddingValues ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(CoopDarkGreen, CoopGreen)
                            )
                        )
                        .padding(paddingValues)
                ) {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        item {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                TopBarSection(onBackClick)

                                Spacer(modifier = Modifier.height(20.dp))

                                BalanceSection()

                                Spacer(modifier = Modifier.height(24.dp))

                                CardComponent(card = uiState.data)

                                Spacer(modifier = Modifier.height(32.dp))

                                ActionButtonsRow()

                                Spacer(modifier = Modifier.height(32.dp))
                            }
                        }

                        item {
                            TransactionListHeader()
                        }

                        items(uiState.transactions) { transaction ->
                            TransactionListItem(transaction)
                        }

                        item {
                            Spacer(
                                modifier = Modifier
                                    .height(32.dp)
                                    .background(Color.White)
                            )
                        }
                    }
                }
            }
        }

    }
}

@Composable
fun TopBarSection(onBackClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(onClick = onBackClick) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back",
                tint = Color.White
            )
        }

        Text(
            text = "",// TODO: add users name
            color = Color.White,
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium
        )

        IconButton(onClick = { }) {
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .background(Color.White.copy(alpha = 0.2f), RoundedCornerShape(4.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Menu",
                    tint = Color.White,
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}

@Composable
fun BalanceSection() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Card Balance",
            color = LightText,
            fontSize = 14.sp
        )
        Spacer(modifier = Modifier.height(4.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "KES 85,000.20",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.width(8.dp))
            Icon(
                painter = painterResource(id = R.drawable.hide),
                contentDescription = "Hide Balance",
                tint = LightText,
                modifier = Modifier.size(18.dp)
            )
        }
    }
}

@Composable
fun ActionButtonsRow() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        ActionButton(
            painter = painterResource(id = R.drawable.new_card),
            "New Card",
            Color(0xFF00ACC1)
        )
        ActionButton(
            painter = painterResource(id = R.drawable.deposit),
            "Deposit",
            Color(0xFF1E88E5)
        )
        ActionButton(
            painter = painterResource(id = R.drawable.withdraw),
            "Withdraw",
            Color(0xFF7CB342)
        )
        ActionButton(
            painter = painterResource(id = R.drawable.block),
            "Block Card",
            Color(0xFFC0CA33)
        )
    }
}

@Composable
fun TransactionListHeader() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                Color.White,
                shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
            )
            .padding(top = 16.dp, bottom = 8.dp)
    ) {
        Box(
            modifier = Modifier
                .width(40.dp)
                .height(4.dp)
                .clip(RoundedCornerShape(2.dp))
                .background(Color.LightGray)
                .align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Recent Transfers",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = CoopGreen,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Today",
            fontSize = 12.sp,
            color = Color.Gray,
            modifier = Modifier.padding(horizontal = 24.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CardDetailScreenPreview() {
    CardDetailScreen(id = "1")
}
