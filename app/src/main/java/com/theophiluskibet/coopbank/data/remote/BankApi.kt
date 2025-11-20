package com.theophiluskibet.coopbank.data.remote

import com.theophiluskibet.coopbank.data.remote.dtos.CardsResponse
import com.theophiluskibet.coopbank.data.remote.dtos.TransactionsResponse
import com.theophiluskibet.coopbank.data.remote.dtos.UserResponse
import com.theophiluskibet.coopbank.helpers.safeApiCall
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

interface BankApi {
    suspend fun getCards(): Result<CardsResponse>
    suspend fun getTransactions(): Result<TransactionsResponse>
    suspend fun getUser(): Result<UserResponse>
}

class BankApiImpl(private val httpClient: HttpClient) : BankApi {
    override suspend fun getCards(): Result<CardsResponse> = safeApiCall {
        httpClient.get("getCards").body()
    }

    override suspend fun getTransactions(): Result<TransactionsResponse> = safeApiCall {
        httpClient.get("cardTransactions").body()
    }

    override suspend fun getUser(): Result<UserResponse> = safeApiCall {
        httpClient.get("getUser").body()
    }
}