package com.theophiluskibet.coopbank.data.remote.dtos

import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    val user: UserDto
)

@Serializable
data class UserDto(
    val address: AddressResponse,
    val avatarUrl: String,
    val email: String,
    val firstName: String,
    val id: String,
    val lastName: String,
    val phone: String
)

@Serializable
data class AddressResponse(
    val city: String,
    val country: String,
    val postalCode: String,
    val street: String
)