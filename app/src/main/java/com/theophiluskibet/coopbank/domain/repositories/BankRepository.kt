package com.theophiluskibet.coopbank.domain.repositories

import com.theophiluskibet.coopbank.domain.models.Card
import com.theophiluskibet.coopbank.domain.models.Transaction
import com.theophiluskibet.coopbank.domain.models.User
import kotlinx.coroutines.flow.Flow

interface BankRepository {
    suspend fun getCards(): Flow<Result<List<Card>>>
    suspend fun getTransactions(): Flow<Result<List<Transaction>>>
    suspend fun getUser(): Flow<Result<User>>

    suspend fun getCard(id: String): Flow<Result<Card>>
}