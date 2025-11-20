package com.theophiluskibet.coopbank.data.local.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

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
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val linkedAccountName: String,
    val name: String,
    val status: String,
    val type: String,
    val userId: String,
//    val wallets: List<WalletEntity> //todo: check reason failing
)

data class WalletEntity(
    val balance: Double,
    val currency: String,
    val flag: String
)