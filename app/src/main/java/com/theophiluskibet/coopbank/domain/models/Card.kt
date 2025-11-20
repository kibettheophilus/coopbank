package com.theophiluskibet.coopbank.domain.models

data class Card(
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
//    val wallets: List<Wallet>
)

data class Wallet(
    val balance: Double,
    val currency: String,
    val flag: String
)