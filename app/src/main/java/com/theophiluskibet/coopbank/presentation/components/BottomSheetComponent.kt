package com.theophiluskibet.coopbank.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.theophiluskibet.coopbank.R

// Simple model for transaction items
data class TransactionItem(
    val id: String,
    val title: String,
    val date: String,
    val amount: String,
    val logoRes: Int // In a real app, this might be a URL
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionsBottomSheet(
    showSheet: Boolean,
    isCardBlocked: Boolean = false,
    transactions: List<TransactionItem> = emptyList(),
    onDismissRequest: () -> Unit,
    sheetState: SheetState = rememberModalBottomSheetState()
) {
    if (showSheet) {
        ModalBottomSheet(
            onDismissRequest = onDismissRequest,
            sheetState = sheetState,
            containerColor = Color.White,
            shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
            dragHandle = { BottomSheetDragHandle() }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
                    .padding(bottom = 40.dp), // Add bottom padding for safe area
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Header Title
                Text(
                    text = "Recent Transfers",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF004D40), // Deep Green
                    modifier = Modifier.padding(bottom = 24.dp)
                )

                if (isCardBlocked) {
                    BlockedStateContent()
                } else {
                    TransactionListContent(transactions)
                }
            }
        }
    }
}

// --- Helper Components ---

@Composable
private fun BottomSheetDragHandle() {
    Box(
        modifier = Modifier
            .padding(vertical = 12.dp)
            .width(40.dp)
            .height(4.dp)
            .clip(RoundedCornerShape(2.dp))
            .background(Color.LightGray)
    )
}

@Composable
private fun BlockedStateContent() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.padding(vertical = 32.dp)
    ) {
        // Illustration
        Image(
            // You'll need to add a placeholder or specific vector for the lock/card icon
            painter = painterResource(id = R.drawable.ic_launcher_foreground), // Replace with actual illustration
            contentDescription = "Card Blocked",
            modifier = Modifier.size(120.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Unblock Card to view recent transactions",
            textAlign = TextAlign.Center,
            color = Color.Gray,
            fontSize = 14.sp
        )

        Spacer(modifier = Modifier.height(32.dp))
    }
}

@Composable
private fun TransactionListContent(transactions: List<TransactionItem>) {
    // "Today" Header
    Row(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Today",
            color = Color.Gray,
            fontSize = 12.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )
    }

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(transactions) { transaction ->
            TransactionRow(transaction)
        }
    }
}

@Composable
fun TransactionRow(item: TransactionItem) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            // Logo Circle
            Image(
                painter = painterResource(id = item.logoRes),
                contentDescription = item.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFF5F5F5))
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column {
                Text(
                    text = item.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = Color(0xFF004D40)
                )
                Text(
                    text = item.date,
                    fontSize = 10.sp,
                    color = Color.Gray
                )
            }
        }

        Text(
            text = item.amount,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            color = Color(0xFF004D40)
        )
    }
}
