package com.theophiluskibet.coopbank.data.mappers

import com.theophiluskibet.coopbank.data.local.entities.AddressEntity
import com.theophiluskibet.coopbank.data.local.entities.CardEntity
import com.theophiluskibet.coopbank.data.local.entities.TransactionEntity
import com.theophiluskibet.coopbank.data.local.entities.UserEntity
import com.theophiluskibet.coopbank.data.local.entities.WalletEntity
import com.theophiluskibet.coopbank.data.remote.dtos.AddressResponse
import com.theophiluskibet.coopbank.data.remote.dtos.CardResponse
import com.theophiluskibet.coopbank.data.remote.dtos.TransactionResponse
import com.theophiluskibet.coopbank.data.remote.dtos.UserDto
import com.theophiluskibet.coopbank.data.remote.dtos.WalletResponse

fun TransactionResponse.toEntity() = TransactionEntity(
    id = id,
    cardId = cardId,
    currency = currency,
    date = date,
    merchant = merchant,
    type = type,
    amount = amount
)

fun CardResponse.toEntity() = CardEntity(
    balance,
    cardNumber,
    creditLimit,
    currency,
    currentSpend,
    dueDate,
    expiryDate,
    holderName,
    id,
    linkedAccountName,
    name,
    status,
    type,
    userId,
    wallets = wallets.map { it.toEntity() }
)

fun WalletResponse.toEntity() = WalletEntity(
    balance,
    currency, flag
)


fun UserDto.toEntity() = UserEntity(
    address = address.toEntity(), avatarUrl, email, firstName, id, lastName, phone
)

fun AddressResponse.toEntity() = AddressEntity(
    city, country, postalCode, street
)