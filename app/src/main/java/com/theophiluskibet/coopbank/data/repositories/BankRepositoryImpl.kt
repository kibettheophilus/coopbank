package com.theophiluskibet.coopbank.data.repositories

import com.theophiluskibet.coopbank.data.local.daos.BankDao
import com.theophiluskibet.coopbank.data.mappers.toDomain
import com.theophiluskibet.coopbank.data.mappers.toEntity
import com.theophiluskibet.coopbank.data.remote.BankApi
import com.theophiluskibet.coopbank.domain.models.Card
import com.theophiluskibet.coopbank.domain.models.Transaction
import com.theophiluskibet.coopbank.domain.models.User
import com.theophiluskibet.coopbank.domain.repositories.BankRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf

class BankRepositoryImpl(
    private val bankApi: BankApi,
    private val bankDao: BankDao
) :
    BankRepository {
    override suspend fun getCards(): Flow<Result<List<Card>>> {
        val localCards = bankDao.getCards().first()
        return if (localCards.isNotEmpty()) {
            flowOf(Result.success(localCards.map { it.toDomain() }))
        } else {
            val response = bankApi.getCards()
            when {
                response.isSuccess -> {
                    val result = response.getOrThrow().map { it.toEntity() }
                    bankDao.saveCards(result)
                    flowOf(Result.success(result.map { it.toDomain() }))
                }

                else -> {
                    flowOf(Result.failure(response.exceptionOrNull() ?: Throwable("Unknown error")))
                }
            }
        }
    }

    override suspend fun getTransactions(): Flow<Result<List<Transaction>>> {
        val localTransactions = bankDao.getTransactions().first()
        return if (localTransactions.isNotEmpty()) {
            flowOf(Result.success(localTransactions.map { it.toDomain() }))
        } else {
            val response = bankApi.getTransactions()
            when {
                response.isSuccess -> {
                    val result = response.getOrThrow().transactions.map { it.toEntity() }
                    bankDao.saveTransactions(result)
                    flowOf(Result.success(result.map { it.toDomain() }))
                }

                else -> {
                    flowOf(Result.failure(response.exceptionOrNull() ?: Throwable("Unknown error")))
                }
            }
        }
    }

    override suspend fun getUser(): Flow<Result<User>> {
        val localUser = bankDao.getUser().first()
        return if (localUser != null) {
            flowOf(Result.success(localUser.toDomain()))
        } else {
            val response = bankApi.getUser()
            when {
                response.isSuccess -> {
                    val result = response.getOrThrow().user.toEntity()
                    bankDao.saveUser(result)
                    flowOf(Result.success(result.toDomain()))
                }

                else -> {
                    flowOf(Result.failure(response.exceptionOrNull() ?: Throwable("Unknown error")))
                }
            }
        }
    }

    override suspend fun getCard(id: String): Flow<Result<Card>> {
        return try {
            val result = bankDao.getCardById(id = id)
            flowOf(Result.success(result.first().toDomain()))
        } catch (e: Exception) {
            flowOf(Result.failure(e))
        }
    }
}