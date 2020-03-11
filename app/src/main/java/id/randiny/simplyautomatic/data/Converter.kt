package id.randiny.simplyautomatic.data

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import id.randiny.simplyautomatic.module.ModuleType

class Converter {

    @TypeConverter
    fun toMap(value: String?): Map<String, Any>? {
        return Gson().fromJson(value, object : TypeToken<Map<String, Any>>() {}.type)
    }

    @TypeConverter
    fun fromMap(data: Map<String, Any>?): String? {
        return Gson().toJson(data)
    }

    @TypeConverter
    fun fromHealth(value: ModuleType): Int? {
        return value.ordinal
    }

    @TypeConverter
    fun toHealth(value: Int): ModuleType? {
        return ModuleType.valueOf(value)
    }
}