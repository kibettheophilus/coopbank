package com.theophiluskibet.coopbank.data.local.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import com.theophiluskibet.coopbank.data.local.entities.CardEntity
import com.theophiluskibet.coopbank.data.local.entities.TransactionEntity
import com.theophiluskibet.coopbank.data.local.entities.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BankDao {
    @Insert(onConflict = REPLACE)
    fun saveCards(cardEntity: List<CardEntity>)

    @Query("SELECT * FROM cards")
    fun getCards(): Flow<List<CardEntity>>

    @Query("SELECT * FROM cards WHERE id = :id")
    fun getCardById(id: String): Flow<CardEntity>

    @Insert(onConflict = REPLACE)
    fun saveTransactions(transactions: List<TransactionEntity>)

    @Query("SELECT * FROM transactions")
    fun getTransactions(): Flow<List<TransactionEntity>>

    @Insert(onConflict = REPLACE)
    fun saveUser(userEntity: UserEntity)

    @Query("SELECT * FROM user")
    fun getUser(): Flow<UserEntity?>
}