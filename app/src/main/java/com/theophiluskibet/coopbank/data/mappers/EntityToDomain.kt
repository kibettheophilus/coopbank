package com.theophiluskibet.coopbank.data.mappers

import com.theophiluskibet.coopbank.data.local.entities.AddressEntity
import com.theophiluskibet.coopbank.data.local.entities.CardEntity
import com.theophiluskibet.coopbank.data.local.entities.TransactionEntity
import com.theophiluskibet.coopbank.data.local.entities.UserEntity
import com.theophiluskibet.coopbank.data.local.entities.WalletEntity
import com.theophiluskibet.coopbank.domain.models.Address
import com.theophiluskibet.coopbank.domain.models.Card
import com.theophiluskibet.coopbank.domain.models.Transaction
import com.theophiluskibet.coopbank.domain.models.User
import com.theophiluskibet.coopbank.domain.models.Wallet

fun TransactionEntity.toDomain() = Transaction(
    id = id,
    cardId = cardId,
    currency = currency,
    date = date,
    merchant = merchant,
    type = type,
    amount = amount
)

fun CardEntity.toDomain() = Card(
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
    wallets = wallets.map { it.toDomain() }
)

fun WalletEntity.toDomain() = Wallet(
    balance,
    currency, flag
)


fun UserEntity.toDomain() = User(
    address = address.toDomain(), avatarUrl, email, firstName, id, lastName, phone
)

fun AddressEntity.toDomain() = Address(
    city, country, postalCode, street
)