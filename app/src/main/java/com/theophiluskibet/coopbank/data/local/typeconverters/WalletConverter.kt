package com.theophiluskibet.coopbank.data.local.typeconverters

import androidx.room.TypeConverter
import com.theophiluskibet.coopbank.data.local.entities.WalletEntity
import kotlinx.serialization.json.Json

class WalletConverter {
    @TypeConverter
    fun fromList(value: List<WalletEntity>): String {
        return Json.encodeToString(value)
    }

    @TypeConverter
    fun toList(value: String): List<WalletEntity> {
        return Json.decodeFromString(value)
    }
}
