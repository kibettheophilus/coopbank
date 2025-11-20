package com.theophiluskibet.coopbank.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.theophiluskibet.coopbank.data.local.daos.BankDao

@Database(version = 1, entities = [], exportSchema = false)
abstract class BankDatabase : RoomDatabase() {
    abstract fun bankDao(): BankDao
}