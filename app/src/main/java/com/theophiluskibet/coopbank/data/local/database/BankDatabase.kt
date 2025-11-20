package com.theophiluskibet.coopbank.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.theophiluskibet.coopbank.data.local.daos.BankDao
import com.theophiluskibet.coopbank.data.local.entities.CardEntity
import com.theophiluskibet.coopbank.data.local.entities.TransactionEntity
import com.theophiluskibet.coopbank.data.local.entities.UserEntity
import com.theophiluskibet.coopbank.data.local.typeconverters.AddressConverter
import com.theophiluskibet.coopbank.data.local.typeconverters.WalletConverter

@Database(version = 1, entities = [CardEntity::class, TransactionEntity::class, UserEntity::class], exportSchema = false)
//@TypeConverters(WalletConverter::class, AddressConverter::class)
abstract class BankDatabase : RoomDatabase() {
    abstract fun bankDao(): BankDao
}