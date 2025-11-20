package com.theophiluskibet.coopbank.data.local.entities

import androidx.room.Embedded
import androidx.room.Entity

@Entity(tableName = "cards")
data class CardEntity(
    val balance: Double,
    val cardNumber: String,
    val creditLimit: Double,
    val currency: String,
    val currentSpend: Double,
    val dueDate: String,
    val expiryDate: String,
    val holderName: String,
    val id: String,
    val linkedAccountName: String,
    val name: String,
    val status: String,
    val type: String,
    val userId: String,
    @Embedded
    val wallets: List<WalletEntity>
)

data class WalletEntity(
    val balance: Double,
    val currency: String,
    val flag: String
)