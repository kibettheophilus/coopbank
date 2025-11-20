package com.theophiluskibet.coopbank.data.remote.dtos

import kotlinx.serialization.Serializable

@Serializable
data class TransactionsResponse(
    val transactions: List<TransactionResponse>
)

@Serializable
data class TransactionResponse(
    val amount: Double,
    val cardId: String,
    val currency: String,
    val date: String,
    val id: String,
    val merchant: String,
    val type: String
)