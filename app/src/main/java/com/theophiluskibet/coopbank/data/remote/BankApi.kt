package com.theophiluskibet.coopbank.data.remote

import com.theophiluskibet.coopbank.data.remote.dtos.CardResponse
import com.theophiluskibet.coopbank.data.remote.dtos.TransactionResponse
import com.theophiluskibet.coopbank.data.remote.dtos.UserResponse
import com.theophiluskibet.coopbank.helpers.safeApiCall
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

interface BankApi {
    suspend fun getCards(): Result<CardResponse>
    suspend fun getTransactions(): Result<List<TransactionResponse>>
    suspend fun getUser(): Result<UserResponse>
}

class BankApiImpl(private val httpClient: HttpClient) : BankApi {
    override suspend fun getCards(): Result<CardResponse> = safeApiCall {
        httpClient.get("cardTransactions").body()
    }

    override suspend fun getTransactions(): Result<List<TransactionResponse>> = safeApiCall {
        httpClient.get("getCards").body()
    }

    override suspend fun getUser(): Result<UserResponse> = safeApiCall {
        httpClient.get("getUser").body()
    }
}