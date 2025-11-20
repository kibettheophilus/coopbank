package com.theophiluskibet.coopbank.data.local.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserEntity(
//    val address: AddressEntity,//todo: check reason failing
    val avatarUrl: String,
    val email: String,
    val firstName: String,
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val lastName: String,
    val phone: String
)

data class AddressEntity(
    val city: String,
    val country: String,
    val postalCode: String,
    val street: String
)