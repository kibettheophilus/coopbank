package com.theophiluskibet.coopbank.presentation.screens.allcards

import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.theophiluskibet.coopbank.domain.models.Card
import com.theophiluskibet.coopbank.presentation.components.CardComponent
import com.theophiluskibet.coopbank.presentation.components.LoadingComponent
import com.theophiluskibet.coopbank.presentation.ui.theme.CoopDarkGreen
import com.theophiluskibet.coopbank.presentation.ui.theme.CoopGreen
import org.koin.androidx.compose.koinViewModel

@Composable
fun AllCardsScreen(
    modifier: Modifier = Modifier,
    allCardsViewModel: AllCardsViewModel = koinViewModel()
) {
    val uiState by allCardsViewModel.allCardsState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = true) {
        allCardsViewModel.getAllCards()
    }

    AllCardsScreenContent(uiState = uiState)
}

@Composable
fun AllCardsScreenContent(uiState: AllCardsUiSate) {
    Scaffold(
        containerColor = Color(0xFFF5F5F5),
        topBar = {
            AllCardsHeader()
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Text(
                text = "View All Cards",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                textAlign = TextAlign.Center,
                color = Color.Gray,
                fontSize = 16.sp
            )

            when {
                uiState.isLoading -> {
                    LoadingComponent()
                }

                uiState.error.isNotEmpty() -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(text = uiState.error, color = Color.Red)
                    }
                }

                uiState.cards.isNotEmpty() -> {
                    LazyContent(cards = uiState.cards)
                }
            }
        }
    }
}

@Composable
fun AllCardsHeader() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(CoopDarkGreen, CoopGreen)
                )
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data("") // TODO: add user profile
                    .crossfade(true)
                    .build(),
                contentDescription = "Profile Picture",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(Color.White)
            )

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = "Hi",// TODO: add usesname
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.weight(1f))


            Spacer(modifier = Modifier.size(40.dp))
        }
    }
}

@Composable
fun LazyContent(modifier: Modifier = Modifier, cards: List<Card>) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(items = cards) { card ->
            CardComponent(card = card)
        }
        item {
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Preview
@Composable
private fun AllCardsScreenPreview() {
    AllCardsScreenContent(
        uiState = AllCardsUiSate(
            isLoading = false,
            error = "",
            cards = listOf(
                Card(
                    id = "1",
                    name = "Debit Card",
                    cardNumber = "0441 XXXX XXXX 4567",
                    type = "Debit",
                    status = "Active",
                    balance = 85000.20,
                    holderName = "WANJIKU KIMANI",
                    expiryDate = "12/25",
                    currency = "KES",
                    linkedAccountName = "Everyday Checking",
                    creditLimit = 0.0,
                    currentSpend = 0.0,
                    dueDate = "",
                    userId = "user123"
                ),
                Card(
                    id = "2",
                    name = "Multi-Currency",
                    cardNumber = "0441 XXXX XXXX 4567",
                    type = "Prepaid",
                    status = "Blocked",
                    balance = 120.50,
                    holderName = "WANJIKU KIMANI",
                    expiryDate = "10/24",
                    currency = "USD",
                    linkedAccountName = "Wanjiku Kimani",
                    creditLimit = 0.0,
                    currentSpend = 0.0,
                    dueDate = "",
                    userId = "user123"
                ),
                Card(
                    id = "3",
                    name = "Prepaid Card",
                    cardNumber = "0441 XXXX XXXX 4567",
                    type = "Prepaid",
                    status = "Active",
                    balance = 4500.00,
                    holderName = "WANJIKU KIMANI",
                    expiryDate = "01/26",
                    currency = "KES",
                    linkedAccountName = "Safari Travel Card",
                    creditLimit = 0.0,
                    currentSpend = 0.0,
                    dueDate = "",
                    userId = "user123"
                ),
                Card(
                    id = "4",
                    name = "Credit Card",
                    cardNumber = "0441 XXXX XXXX 4567",
                    type = "Credit",
                    status = "Active",
                    balance = -15000.00,
                    holderName = "WANJIKU KIMANI",
                    expiryDate = "05/27",
                    currency = "KES",
                    linkedAccountName = "Platinum Credit",
                    creditLimit = 200000.0,
                    currentSpend = 15000.0,
                    dueDate = "2023-02-15",
                    userId = "user123"
                )
            )
        )
    )
}

