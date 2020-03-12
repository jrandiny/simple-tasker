package id.randiny.simplyautomatic.data

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import id.randiny.simplyautomatic.module.ModuleType

class Converter {

    @TypeConverter
    fun toMap(value: String?): Map<String, String>? {
        return Gson().fromJson(value, object : TypeToken<Map<String, String>>() {}.type)
    }

    @TypeConverter
    fun fromMap(data: Map<String, String>?): String? {
        return Gson().toJson(data)
    }

    @TypeConverter
    fun fromModuleType(value: ModuleType): Int? {
        return value.ordinal
    }

    @TypeConverter
    fun toModuleType(value: Int): ModuleType? {
        return ModuleType.valueOf(value)
    }
}