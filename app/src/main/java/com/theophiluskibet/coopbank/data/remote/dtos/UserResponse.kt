package com.theophiluskibet.coopbank.data.remote.dtos

data class UserResponse(
    val user: User
)

data class User(
    val address: Address,
    val avatarUrl: String,
    val email: String,
    val firstName: String,
    val id: String,
    val lastName: String,
    val phone: String
)

data class Address(
    val city: String,
    val country: String,
    val postalCode: String,
    val street: String
)