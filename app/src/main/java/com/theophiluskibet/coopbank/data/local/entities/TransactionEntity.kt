package com.theophiluskibet.coopbank.data.local.entities

import androidx.room.Entity

@Entity(tableName = "transactions")
data class TransactionEntity(
    val amount: Double,
    val cardId: String,
    val currency: String,
    val date: String,
    val id: String,
    val merchant: String,
    val type: String
)