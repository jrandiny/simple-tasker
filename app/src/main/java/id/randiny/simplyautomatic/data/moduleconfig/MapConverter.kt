package id.randiny.simplyautomatic.data.moduleconfig

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MapConverter {

    @TypeConverter
    fun toMap(value: String?): Map<String, Any>? {
        return Gson().fromJson(value, object: TypeToken<Map<String, Any>>() {}.type)
    }

    @TypeConverter
    fun fromMap(data: Map<String, Any>?): String? {
        return Gson().toJson(data)
    }

}