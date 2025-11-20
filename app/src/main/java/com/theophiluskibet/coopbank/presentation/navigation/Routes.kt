package com.theophiluskibet.coopbank.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
object AllCards

@Serializable
object Profile


@Serializable
data class CardDetails(val id: String)