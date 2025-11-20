package com.theophiluskibet.coopbank.data.local.typeconverters

import androidx.room.TypeConverter
import com.theophiluskibet.coopbank.data.local.entities.AddressEntity
import kotlinx.serialization.json.Json

class AddressConverter {
    @TypeConverter
    fun fromList(value: List<AddressEntity>): String {
        return Json.encodeToString(value)
    }

    @TypeConverter
    fun toList(value: String): List<AddressEntity> {
        return Json.decodeFromString(value)
    }
}
