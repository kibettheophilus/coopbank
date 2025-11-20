package com.theophiluskibet.coopbank.data.local.entities

import androidx.room.Embedded
import androidx.room.Entity

@Entity(tableName = "user")
data class UserEntity(
    @Embedded
    val address: AddressEntity,
    val avatarUrl: String,
    val email: String,
    val firstName: String,
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